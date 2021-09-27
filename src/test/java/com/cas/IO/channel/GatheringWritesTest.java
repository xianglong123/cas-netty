package com.cas.IO.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/25 10:53 上午
 * @desc 集中写
 */
public class GatheringWritesTest {


    public static void main(String[] args) {

        ByteBuffer b1 = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer b2 = StandardCharsets.UTF_8.encode("world");
        ByteBuffer b3 = StandardCharsets.UTF_8.encode("你好");

        try (FileChannel channel = new RandomAccessFile("/Users/xianglong/IdeaProjects/cas-netty/src/test/java/com/cas/IO/channel/words2.txt", "rw").getChannel()) {
            channel.write(new ByteBuffer[] {b1, b2, b3});
        } catch (IOException e) {
        }
    }

}
