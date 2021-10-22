package com.cas.netty.c1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/14 2:38 下午
 * @desc
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务注册[" + ctx.channel().remoteAddress() + "]");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务下线[" + ctx.channel().remoteAddress() + "]");
//        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务["+ ctx.channel().remoteAddress() +"], 发送了：" + msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务["+ ctx.channel().remoteAddress() +"], 发送了：" + msg);
    }

    // 表示channel 处于活动状态，提示XX上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(ctx.channel().remoteAddress() + "上线了");
    }

    // 表示channel 处于不活动状态，提示XX离线了
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println(ctx.channel().remoteAddress() + " 离线了");
    }

    // 异常处理。关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 异常就关闭通道
        ctx.close();
    }




}
