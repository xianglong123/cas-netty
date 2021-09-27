package com.cas.IO.channel;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static com.cas.IO.channel.ByteBufferUtil.debugAll;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/25 10:33 上午
 * @desc
 */
public class ByteBufferStringTest {

    public static void main(String[] args) {
        // 1.字符串转为 ByteBuffer

        ByteBuffer buffer1 = ByteBuffer.allocate(16);
        buffer1.put("hello".getBytes());
        debugAll(buffer1);

        // 2.Charset
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");
        debugAll(buffer2);

        // 3.wrap
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());
        debugAll(buffer3);

        // 转为字符串
        String str1 = StandardCharsets.UTF_8.decode(buffer2).toString();
        System.out.println(str1);

        buffer1.flip();
        String str2 = StandardCharsets.UTF_8.decode(buffer1).toString();
        System.out.println(str2);


    }

}
