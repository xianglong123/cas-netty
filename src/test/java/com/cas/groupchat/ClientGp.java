package com.cas.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/23 5:02 下午
 * @desc
 */
public class ClientGp {

    private static final Logger log = LoggerFactory.getLogger(ClientGp.class);

    private String host;
    private int port;

    public ClientGp(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {

        EventLoopGroup work = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(work)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            // 得到pipeline
                            ChannelPipeline pipeline = ch.pipeline();
                            // 加入相关的handler
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            // 加入自定义的handler
                            pipeline.addLast(new GpChatClientHandler());
                        }
                    });
            ChannelFuture sync = bootstrap.connect(host, port).sync();
            // 得到channel
            Channel channel = sync.channel();
            log.info("-----" + channel.localAddress() + "-----");
            // 客户端需要输入信息，创建一个扫描器
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                // 通过channel 发送消息给服务器端
                channel.writeAndFlush(msg + "\r\t");
            }
        } finally {
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new ClientGp("127.0.0.1", 7000).run();
    }

}
