package com.cas.netty.c1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/12 10:00 上午
 * @desc
 */
public class EventLoopClient {

    public static void main(String[] args) throws InterruptedException {
        bVoid();
    }

    private static void bVoid() throws InterruptedException {
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                // 1、连接服务器
                // connect 是异步非阻塞， main 发起了调用，真正执行connect 是 nio线程
                .connect("127.0.0.1", 9011);
        // 2.1 使用 sync 方法同步处理结果，真正等连接建立了，才会向下进行
//        channelFuture.sync(); // 阻塞住当前线程，直到nio线程连接建立完毕
//        Channel channel = channelFuture.channel();
//        channel.writeAndFlush("hello world");


        // 2.2 使用addListener 方法异步处理结果
        channelFuture.addListener((ChannelFutureListener) future -> {
            // 在nio线程连接建立好之后，会调用operationComplete
            Channel channel = future.channel();
            System.out.println("channel: " + channel);
            channel.writeAndFlush("hello world");
        });

    }

}
