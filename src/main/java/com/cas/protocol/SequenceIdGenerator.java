package com.cas.protocol;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/11/1 8:40 下午
 * @desc
 */
public abstract class SequenceIdGenerator {

    private static final AtomicInteger id = new AtomicInteger();

    public static int nextId(){
        return id.incrementAndGet();
    }



}
