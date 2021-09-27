package com.cas.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/25 12:27 下午
 * @desc
 */
public class Client {

    public static void main(String[] args) throws IOException {
        SocketChannel open = SocketChannel.open();
        boolean connect = open.connect(new InetSocketAddress("127.0.0.1", 9000));
        if (!connect) {
            System.out.println("连接未创建成功");
        }
        open.write(StandardCharsets.UTF_8.encode("hello"));
        System.out.println("aaaa");
    }

}
