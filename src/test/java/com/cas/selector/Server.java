package com.cas.selector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import static com.cas.IO.channel.ByteBufferUtil.debugRead;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/26 2:22 下午
 * @desc socket + selector
 */
public class Server {
    private static final Logger log = LoggerFactory.getLogger(Server.class);


    public static void main(String[] args) throws IOException {
        // 1.创建 selector，管理多个 channel
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);


        SelectionKey sscKey = ssc.register(selector, SelectionKey.OP_ACCEPT, null);
        log.debug("register key:{}", sscKey);
        // 2.key 只关注 accept 事件
        ssc.bind(new InetSocketAddress(9000));

        while (true) {
            // 3.有事件才会运行
            // 事件要不处理，要不取消，不然会CPU空转
            selector.select();

            // 4.关注事件
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                // 处理key后，要从集合中删除
                iter.remove();
                if (key.isAcceptable()) {
                    log.debug("key:{}", key);
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    // 这里接受的才是最新的通道
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    log.debug("{}", sc);
                    // 绑定 selector
                    SelectionKey key1 = sc.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(16));
                } else if (key.isReadable()) {
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        int read = channel.read(buffer);
                        if (read == -1) {
                            key.cancel();
                        } else {
                            if (buffer.position() == buffer.limit()) {
                                ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                buffer.flip();
                                newBuffer.put(buffer);
                                key.attach(newBuffer);
                            } else {
                                buffer.flip();
                            }
                            debugRead(buffer);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel(); // 因为key已经断开了，因此需要将key取消
                    }
                }
                // key.cancel();
            }

        }
    }

}
