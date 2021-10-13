package com.cas.nio.multiThread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/11 10:24 上午
 * @desc
 */
public class ClientTest {

    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("127.0.0.1", 9011));
        sc.write(Charset.defaultCharset().encode("1234567890abcdef"));
        System.in.read();
        sc.close();
    }

}
