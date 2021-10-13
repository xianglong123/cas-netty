package com.cas.netty;

import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;

import java.util.concurrent.TimeUnit;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/11 4:53 下午
 * @desc
 */
public class EventLoopTest {

    public static void main(String[] args) {
        // 1、IO事件，普通任务，定时任务
        EventLoopGroup group = new NioEventLoopGroup(2);
        System.out.println(NettyRuntime.availableProcessors());

        // 普通任务，定时任务
        DefaultEventLoop del = new DefaultEventLoop();

        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());

        del.next().scheduleAtFixedRate(() -> {
            System.out.println("ok");
        }, 0, 1, TimeUnit.SECONDS);
    }

}
