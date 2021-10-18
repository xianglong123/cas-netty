package com.cas.netty.c3;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/14 4:12 下午
 * @desc
 */
public class TestNettyPromise {
    private static final Logger log = LoggerFactory.getLogger(TestNettyPromise.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1、准备 EventLoop 对象
        EventLoop eventLoop = new NioEventLoopGroup().next();

        // 2、可以主动创建 promise， 结果容器
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);

        new Thread(() -> {
            // 3、任意一个线程执行计算，计算完毕后向 promise 填充结果
            System.out.println("开始计算...");
            try {
                // int i = 1 / 0;
                Thread.sleep(1000);
                promise.setSuccess(90);
            } catch (InterruptedException e) {
                promise.setFailure(e);
            }
        }).start();

        // 4、接受结果的线程
        log.debug("等待结果...");
        log.debug("结果是：{}", promise.get());

    }

}
