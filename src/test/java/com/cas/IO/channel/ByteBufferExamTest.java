package com.cas.IO.channel;

import java.nio.ByteBuffer;

import static com.cas.IO.channel.ByteBufferUtil.debugAll;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/25 11:04 上午
 * @desc 毡包
 */
public class ByteBufferExamTest {

    public static void main(String[] args) {

        ByteBuffer source = ByteBuffer.allocate(32);

        source.put("hello world\nI'm zhangsan\nHo".getBytes());
        spilt(source);

        source.put("w are you\n".getBytes());
        spilt(source);
    }

    private static void spilt(ByteBuffer source) {
        source.flip();

        for (int i = 0; i < source.limit(); i++) {

            if (source.get(i) == '\n') {
                int length = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(length);

                for (int j = 0; j < length; j++) {
                    target.put(source.get());
                }
                debugAll(target);
            }
        }
        source.compact();
    }

}
