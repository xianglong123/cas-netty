package com.cas.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketServer {
    public static void main(String[] args) throws Exception {
        serverSocket();
    }

    private static void serverSocket() throws Exception {
        // 1、创建一个服务器端socket，即ServerSocket，指定绑定的端口，并监听此端口
        ServerSocket serverSocket = new ServerSocket(12345);

        // 2、连接后获取输入流。读取客户端信息
        System.out.println("等待客户端连接");
        Socket socket = serverSocket.accept();

        // 3、
        InputStream is;
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream os = null;
        PrintWriter pw = null;
        is = socket.getInputStream();     //获取输入流
        isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        br = new BufferedReader(isr);
        String info = null;
        while ((info = br.readLine()) != null) {//循环读取客户端的信息
            System.out.println("客户端发送过来的信息" + info);
        }
        socket.shutdownInput();
        socket.close();
    }

}