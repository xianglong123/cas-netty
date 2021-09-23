package com.cas.IO;

import java.io.*;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/22 2:17 下午
 * @desc 带缓冲区输出流
 * 提供缓冲输入流功能。缓冲输入流相对于普通输入流的优势是，它提供了一个缓冲数组，每次调用read方法的时候，
 * 它首先尝试从缓冲区里读取数据，若读取失败（缓冲区无可读数据），则选择从物理数据源（譬如文件）读取新数据（这里会尝试尽可能读取多的字节）放入到缓冲区中，
 * 最后再将缓冲区中的内容部分或全部返回给用户.由于从缓冲区里读取数据远比直接从物理数据源（譬如文件）读取速度快。
 *
 */
public class BufferedInputStreamTest{

    protected static FileInputStream in1;

    protected static FileOutputStream out1;

    static {
        try {
            in1 = new FileInputStream("/Users/xianglong/IdeaProjects/cas-netty/src/test/java/com/cas/IO/data/test.txt");
            out1 = new FileOutputStream("/Users/xianglong/IdeaProjects/cas-netty/src/test/java/com/cas/IO/data/test1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这个流逼，能将数据读到缓存区，然后我们从缓冲区内读取数据，效率高
     * @param args
     */
    public static void main(String[] args) {
        try (BufferedInputStream bis = new BufferedInputStream(in1);) {
            byte[] buffer = new byte[1024];
            int c;
            while ((c = bis.read(buffer)) != -1) {
                String chunk = new String(buffer, 0, c);
                System.out.println(chunk);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
