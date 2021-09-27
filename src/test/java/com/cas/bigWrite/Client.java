package com.cas.bigWrite;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/26 5:54 下午
 * @desc 大量数据数据读出
 */
public class Client {

    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();

        sc.connect(new InetSocketAddress("127.0.0.1", 9000));

        int count = 0;

        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);

        while (true) {
            count += sc.read(buffer);
            System.out.println(count);
            buffer.clear();
        }
    }
}
