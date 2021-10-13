package com.cas.netty.c2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/13 3:40 下午
 * @desc
 */
public class CloseFutureClient {

    private static final Logger log = LoggerFactory.getLogger(CloseFutureClient.class);

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(new StringEncoder());
                    }
                }).connect(new InetSocketAddress("localhost", 9011));
        Channel channel = channelFuture.sync().channel();
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String line = scanner.nextLine();
                if ("q".equals(line)) {
                    channel.close(); // 异步处理
//                    log.debug("处理关闭之后的操作"); // 不能在这里善后
                    break;
                }
                channel.writeAndFlush(line);
            }

        }, "input").start();

        // 获取 closeFuture 对象， 1）同步处理关闭  2）异步处理关闭
        ChannelFuture closeFuture = channel.closeFuture();
        /*System.out.println("waiting close ...");
        closeFuture.sync();
        System.out.println("处理关闭之后的操作");*/
        closeFuture.addListener((ChannelFutureListener) future -> {
            System.out.println("处理关闭之后的操作");
            group.shutdownGracefully(); // 优雅的停止线程组
        });


    }

}
