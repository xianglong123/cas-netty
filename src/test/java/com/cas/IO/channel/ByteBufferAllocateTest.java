package com.cas.IO.channel;

import java.nio.ByteBuffer;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/25 10:19 上午
 * @desc
 */
public class ByteBufferAllocateTest {

    public static void main(String[] args) {
        System.out.println(ByteBuffer.allocate(10).getClass()); // 堆内存、读写效率低
        System.out.println(ByteBuffer.allocateDirect(10).getClass()); // 直接内存，读写效率高，不会受GC影响，系统调用，分配效率低，会内存益处
        /**
         * class java.nio.HeapByteBuffer
         * class java.nio.DirectByteBuffer
         */
    }

}
