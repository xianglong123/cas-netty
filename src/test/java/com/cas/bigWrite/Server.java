package com.cas.bigWrite;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/26 5:37 下午
 * @desc 大量数据写入
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        Selector selector = Selector.open();
        ssc.bind(new InetSocketAddress(9000));
        ssc.register(selector, SelectionKey.OP_ACCEPT, null);
        System.out.println("开始....");
        while (true) {
            selector.select();
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while(iter.hasNext()) {
                SelectionKey key = iter.next();
                // 处理key后，要从集合中删除
                iter.remove();
                System.out.println("start ing ...");
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 3000000; i ++) {
                        sb.append("a");
                    }

                    ByteBuffer buffer = StandardCharsets.UTF_8.encode(sb.toString());
                    while (buffer.hasRemaining()) {
                        // 返回值代表实际写入的字节数
                        int write = sc.write(buffer);
                        System.out.println(write);
                    }
                }
            }
        }
    }

}
