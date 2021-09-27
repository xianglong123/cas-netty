package com.cas.IO.channel;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/24 5:24 下午
 * @desc
 */
public class ByteBufferTest {

    public static void main(String[] args) throws IOException {
        try(FileChannel fis = new FileInputStream("/Users/xianglong/IdeaProjects/cas-netty/src/test/java/com/cas/IO/channel/a.txt").getChannel();) {
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (fis.read(buffer) != -1) {
                // 切换为读模式
                buffer.flip();
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    System.out.println((char)b);
                }

                // 切换为写模式
                buffer.clear();
            }
        }
    }

}
