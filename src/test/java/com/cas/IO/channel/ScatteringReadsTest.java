package com.cas.IO.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static com.cas.IO.channel.ByteBufferUtil.debugAll;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/25 10:47 上午
 * @desc 分散读
 */
public class ScatteringReadsTest {

    public static void main(String[] args) {
        try (FileChannel channel = new RandomAccessFile("/Users/xianglong/IdeaProjects/cas-netty/src/test/java/com/cas/IO/channel/words.txt", "r").getChannel()) {
            ByteBuffer buffer1 = ByteBuffer.allocate(3);
            ByteBuffer buffer2 = ByteBuffer.allocate(3);
            ByteBuffer buffer3 = ByteBuffer.allocate(5);

            channel.read(new ByteBuffer[] {buffer1, buffer2, buffer3});
            buffer1.flip();
            buffer2.flip();
            buffer3.flip();

            debugAll(buffer1);
            debugAll(buffer2);
            debugAll(buffer3);

        } catch (IOException e) {
        }

    }


}
