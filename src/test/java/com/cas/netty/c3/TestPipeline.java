package com.cas.netty.c3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/14 4:55 下午
 * @desc
 */
public class TestPipeline {
    private static final Logger log = LoggerFactory.getLogger(TestPipeline.class);

    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        /**
                         * head -> 1 -> 2 -> 4 -> 3 -> 5 -> 6 -> tail [ch.writeAndFlush会从tail开始找]
                         *
                         * 17:31:04.607 [nioEventLoopGroup-2-2] DEBUG com.cas.netty.c3.TestPipeline - 1
                         * 17:31:04.607 [nioEventLoopGroup-2-2] DEBUG com.cas.netty.c3.TestPipeline - 2
                         * 17:31:04.607 [nioEventLoopGroup-2-2] DEBUG com.cas.netty.c3.TestPipeline - 3
                         * 17:31:04.609 [nioEventLoopGroup-2-2] DEBUG com.cas.netty.c3.TestPipeline - 6
                         * 17:31:04.609 [nioEventLoopGroup-2-2] DEBUG com.cas.netty.c3.TestPipeline - 5
                         * 17:31:04.609 [nioEventLoopGroup-2-2] DEBUG com.cas.netty.c3.TestPipeline - 4
                         *
                         * head -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> tail [ctx.writeAndFlush会从最后一个in往前找]
                         * 17:35:30.907 [nioEventLoopGroup-2-2] DEBUG com.cas.netty.c3.TestPipeline - 1
                         * 17:35:30.907 [nioEventLoopGroup-2-2] DEBUG com.cas.netty.c3.TestPipeline - 2
                         * 17:35:30.907 [nioEventLoopGroup-2-2] DEBUG com.cas.netty.c3.TestPipeline - 3
                         * 17:35:30.908 [nioEventLoopGroup-2-2] DEBUG com.cas.netty.c3.TestPipeline - 4
                         */
                        pipeline.addLast("h1", new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("1");
                                super.channelRead(ctx, msg);
                            }
                        });
                        pipeline.addLast("h2", new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("2");
                                super.channelRead(ctx, msg);
                            }
                        });

                        pipeline.addLast("h4", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("4");
                                super.write(ctx, msg, promise);
                            }
                        });

                        pipeline.addLast("h3", new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("3");
                                ctx.writeAndFlush(ctx.alloc().buffer().writeBytes("hello".getBytes()));
                                super.channelRead(ctx, msg);
                            }
                        });
                        pipeline.addLast("h5", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("5");
                                super.write(ctx, msg, promise);
                            }
                        });
                        pipeline.addLast("h6", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("6");
                                super.write(ctx, msg, promise);
                            }
                        });

                    }
                }).bind(9011);

    }

}
