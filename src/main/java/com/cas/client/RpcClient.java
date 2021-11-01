package com.cas.client;

import com.cas.message.RpcRequestMessage;
import com.cas.protocol.MessageCodecSharable;
import com.cas.protocol.ProcotolFrameDecoder;
import com.cas.protocol.SequenceIdGenerator;
import com.cas.server.handler.RpcResponseMessageHandler;
import com.cas.server.service.HelloService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;


/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/11/1 6:43 下午
 * @desc
 */
public class RpcClient {
    private static final Logger log = LoggerFactory.getLogger(RpcClient.class);
    private static Channel channel = null;
    private static final Object obj = new Object();

    public static void main(String[] args) {
        HelloService helloService = getProxyService(HelloService.class);
        System.out.println("=====================");
        System.out.println(helloService.sayHello("zhangsan"));
//        System.out.println(helloService.sayHello("lisi"));
    }

    // 创建代理类
    public static <T> T getProxyService(Class<T> serviceClass) {
        Class<?>[] interfaces = new Class[]{serviceClass};
        Object o = Proxy.newProxyInstance(serviceClass.getClassLoader(), interfaces, ((proxy, method, args) -> {
                    // 1.将方法调用转换为 消息对象
                    int sequenceId = SequenceIdGenerator.nextId();
                    RpcRequestMessage message = new RpcRequestMessage(sequenceId,
                            serviceClass.getName(),
                            method.getName(),
                            method.getReturnType(),
                            method.getParameterTypes(),
                            args);
                    // 2.将消息发送出去
                    getChannel().writeAndFlush(message);

                    // 3.准备一个空 Promise 对象，来接收结果  指定 promise 对象异步接收结果线程
                    DefaultPromise<Object> promise = new DefaultPromise<>(getChannel().eventLoop());
                    RpcResponseMessageHandler.PROMISES.put(sequenceId, promise);
                    //3.等待promise结果
                    promise.await();
                    if (promise.isSuccess()) {
                        return promise.getNow();
                    } else {
                        throw new RuntimeException(promise.cause());
                    }
                })
        );
        return (T) o;
    }

    public static Channel getChannel() {
        if (channel != null) {
            return channel;
        }
        synchronized (obj) {
            if (channel != null) {
                return channel;
            }
            initChannel();
            return channel;
        }
    }

    private static void initChannel() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        RpcResponseMessageHandler RPC_HANDLER = new RpcResponseMessageHandler();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(group);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ProcotolFrameDecoder());
                ch.pipeline().addLast(LOGGING_HANDLER);
                ch.pipeline().addLast(MESSAGE_CODEC);
                ch.pipeline().addLast(RPC_HANDLER);
            }
        });
        try {
            channel = bootstrap.connect("127.0.0.1", 9017).sync().channel();
            channel.closeFuture().addListener(future -> group.shutdownGracefully());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
