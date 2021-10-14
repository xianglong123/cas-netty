package com.cas.netty.c3;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/14 3:44 下午
 * @desc
 */
public class TestNettyFuture {
    private static final Logger log = LoggerFactory.getLogger(TestNettyFuture.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup executors = new NioEventLoopGroup();
        EventLoop next = executors.next();
        Future<Integer> submit = next.submit(() -> {
            log.info("执行结果");
            Thread.sleep(1000);
            return 70;
        });
        /**
         * 16:00:26.615 [main] INFO com.cas.netty.c3.TestNettyFuture - 等待结果
         * 16:00:26.618 [nioEventLoopGroup-2-1] INFO com.cas.netty.c3.TestNettyFuture - 执行结果
         * 16:00:27.625 [main] INFO com.cas.netty.c3.TestNettyFuture - 结果：70
         */
//        log.info("等待结果");
//        log.info("结果：" + submit.get());

        /**
         * 16:04:44.927 [nioEventLoopGroup-2-1] INFO com.cas.netty.c3.TestNettyFuture - 执行结果
         * 16:04:45.934 [nioEventLoopGroup-2-1] INFO com.cas.netty.c3.TestNettyFuture - 接受结果【70】
         */
        submit.addListener(future -> {
            log.info("接受结果【{}】", future.getNow());
            executors.shutdownGracefully();
        });

    }

}
