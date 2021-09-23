package com.cas.socket;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity  {

    public static void main(String[] args) throws Exception{
//        //1.创建客户端Socket，指定服务器地址和端口
//        Socket socket = new Socket("127.0.0.1", 12345);
//        //2.获取输出流，向服务器端发送信息
//        OutputStream os = socket.getOutputStream();//字节输出流
//        PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
//        //获取客户端的IP地址
//        InetAddress address = InetAddress.getLocalHost();
//        String ip = address.getHostAddress();
//        System.out.println("开始");
//        pw.write("客户端：~" + ip + "~ 接入服务器！！");
//        pw.flush();
//        socket.shutdownOutput();//关闭输出流
//        socket.close();
        clientSocket();
    }


    private static void clientSocket() throws Exception{
        // 1、创建socket，指定服务器地址和端口
        Socket socket = new Socket("127.0.0.1", 12345);
        // 2、获取输出流，向服务器端发送消息
        OutputStream out = socket.getOutputStream();

        out.write("wo".getBytes());

        out.flush();

        socket.close();





    }

}