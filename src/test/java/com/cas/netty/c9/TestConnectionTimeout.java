package com.cas.netty.c9;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/11/1 3:02 下午
 * @desc 测试连接超时: CONNECT_TIMEOUT_MILLIS
 *
 * 客户端通过 Bootstrap.option 函数来配置参数，配置参数作用于 SocketChannel
 * 服务器通过 ServerBootstrap来配置参数，但是对于不同的 Channel 需要选择不同的方法
 * 通过 option 来配置 ServerSocketChannel 上的参数
 * 通过 childOption 来配置 SocketChannel 上的参数
 */
public class TestConnectionTimeout {

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30)
                    .channel(NioSocketChannel.class)
                    .handler(new LoggingHandler());
            ChannelFuture future = bootstrap.connect("127.0.0.1", 9015);
            future.sync().channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }


    }

}
