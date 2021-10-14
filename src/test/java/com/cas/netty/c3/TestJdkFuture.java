package com.cas.netty.c3;

import java.util.concurrent.*;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/14 3:27 下午
 * @desc
 */
public class TestJdkFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> submit = service.submit(() -> {
            Thread.sleep(1000);
            return 50;
        });
        System.out.println("等待结果");
        System.out.println("结果：" + submit.get());
    }

}
