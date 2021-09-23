package com.cas.IO;

import java.io.*;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/17 10:51 下午
 * @desc InputStreamReader：是字节转换为字符的桥梁，它使指定的charset读取字节并将其解码为字符。（解码：把看不懂的字节变成可以看懂的字符）
 */
public class InputStreamReaderTest {

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("/Users/xianglong/IdeaProjects/cas-netty/src/test/java/com/cas/IO/a.txt"), "UTF-8");
        int len;

        while ((len = isr.read()) != -1) {
            System.out.println((char) len);
        }

        isr.close();
    }

}
