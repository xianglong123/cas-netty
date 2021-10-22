package com.cas.netty.c5;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/23 5:02 下午
 * @desc echo 回显
 */
public class EchoClient {

    private static final Logger log = LoggerFactory.getLogger(EchoClient.class);

    private String host;
    private int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {

        EventLoopGroup work = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(work)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            // 得到pipeline
                            ChannelPipeline pipeline = ch.pipeline();
                            // 加入相关的handler
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                            // 加入自定义的handler
                            pipeline.addLast(new ChannelInboundHandlerAdapter(){
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    ByteBuf byteBuf = msg instanceof ByteBuf ? ((ByteBuf) msg) : null;
                                    assert byteBuf != null;
                                    System.out.println("=================" + byteBuf.toString(Charset.defaultCharset()));
                                    log.info("回显: {}", byteBuf.toString(Charset.defaultCharset()));
                                }
                            });
                        }
                    });
            ChannelFuture sync = bootstrap.connect(host, port).sync();
            // 得到channel
            Channel channel = sync.channel();
            log.info("-----" + channel.localAddress() + "-----");
            // 客户端需要输入信息，创建一个扫描器
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                // 通过channel 发送消息给服务器端
                ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(); // 默认256，支持自动扩容
                buffer.writeBytes((msg + "\r\t").getBytes());
                channel.writeAndFlush(buffer);
            }
        } finally {
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoClient("127.0.0.1", 9013).run();
    }

}
