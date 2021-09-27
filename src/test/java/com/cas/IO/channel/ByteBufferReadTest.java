package com.cas.IO.channel;

import java.nio.ByteBuffer;

import static com.cas.IO.channel.ByteBufferUtil.debugAll;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/25 10:24 上午
 * @desc
 */
public class ByteBufferReadTest {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        buffer.put(new byte[] {'a','b','c','d','e'});

        buffer.flip();

        buffer.get(new byte[4]);
        debugAll(buffer);

        // 从头开始读
        buffer.rewind();

        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
        buffer.mark(); // 将当前位置保存到mark
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
        buffer.reset(); // 将mark 保存的值赋值给 position
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
    }

}
