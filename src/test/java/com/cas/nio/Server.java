package com.cas.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static com.cas.IO.channel.ByteBufferUtil.debugRead;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/25 12:24 下午
 * @desc 利用socket写一个服务端
 */
public class Server {

    private static final Logger log = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(32);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9000));
        ssc.configureBlocking(false);
        List<SocketChannel> list = new ArrayList<>();

        while (true) {

            SocketChannel channel = ssc.accept(); // 会阻塞
            if (channel != null) {
                channel.configureBlocking(false);
                list.add(channel);
            }
            for (SocketChannel ch : list) {
                int read = ch.read(buffer); // 会阻塞
                if (read > 0) {
                    buffer.flip();
                    debugRead(buffer);
                    buffer.clear();
                }
            }
        }
    }

}
