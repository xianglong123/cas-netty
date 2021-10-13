package com.cas.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/23 5:02 下午
 * @desc
 */
public class ServerGp {
    private static final Logger log = LoggerFactory.getLogger(ServerGp.class);

    private int port;

    public ServerGp(int port) {
        this.port = port;
    }

    public void run() throws Exception{

        // 领导阶层
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        // 工薪阶层
        NioEventLoopGroup work = new NioEventLoopGroup();

        try {
            // 1、启动器，负责组装 netty 组建， 启动服务器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 2、BossEventLoop, WorkerEventLoop(selector,thread), group 组
            serverBootstrap.group(boss, work)
                    // 3、选择 服务器的 ServerSocketChannel 实现
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 124)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 4、boss 负责处理连接 worker(child) 负责处理读写，决定了 worker(child) 能执行的内容
                    .childHandler(
                            // 5、channel 代表和客户端进行数据读写的通道 Initializer 初始化
                            new ChannelInitializer<SocketChannel>() {
                                // 建立连接后连接后，调用初始化方法
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            // 获取到pipeline
                            ChannelPipeline pipeline = ch.pipeline();
                            // 向pipeline 加入解码器
                            pipeline.addLast("decoder", new StringDecoder());
                            // 向pipeline 加入编码器
                            pipeline.addLast("encoder", new StringEncoder());

                            /**
                             * 说明
                             * 1. IdleStateHandler 是netty提供的处理空闲状态的处理器
                             * 2. Long readerIdleTime: 表示多长时间没有读， 就会发送一个心跳检测包检测是否连接
                             * 3. Long writerIdleTime: 表示多长时间没有写， 就会发送一个心跳检测包检测是否连接
                             * 4. Long allIdleTime: 表示多长时间没有读写， 就会发送一个心跳检测包检测是否连接
                             * 5. 当 IdleStateHandler 触发后， 就会传递给管道的下一个handler去处理
                             * 通过调用下一个handler的 userEventTiggered,
                             * 在该方法中去处理IdleStateHandler（读空闲， 写空闲， 读写空闲）
                             */
                            pipeline.addLast(new IdleStateHandler(3, 5, 7, TimeUnit.SECONDS));
                            // 加入自己的业务处理handler
                            pipeline.addLast(new GpChatServerHandler());
                        }
                    });
            log.info(" netty 服务器启动");

            ChannelFuture sync = serverBootstrap.bind(port).sync();
            // 监听关闭
            sync.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new ServerGp(7000).run();
    }


}
