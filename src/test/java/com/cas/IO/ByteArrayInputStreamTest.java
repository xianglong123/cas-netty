package com.cas.IO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/17 9:28 下午
 * @desc ByteArrayInputStream 字节数组输入流
 *
 * 字节数组输入流在内存中创建一个字节数组缓冲区、从输入流读取的数据保存在该字节数组缓冲区中。
 * 创建字节数组输入流对象有以下集中方式。
 *
 * 只能一个字节一个字节的读、字节流输出字符流会乱码
 *
 */
public class ByteArrayInputStreamTest {

    /**
     * Ç
     * 
     * ±
     * Ä
     * ½
     *  
     * 我
     * 爱
     * 你
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String content = "我爱你";
        int c;
        ByteArrayInputStream bis = new ByteArrayInputStream(content.getBytes(UTF_8));
        for (int y = 0; y < 1; y ++) {
            while ((c = bis.read()) != -1) {
                System.out.println(Character.toUpperCase((char)c));
            }
            bis.reset();
        }

        // 字节流转字符流输出即可不乱码
        InputStreamReader isr = new InputStreamReader(bis);
        int len;

        while ((len = isr.read()) != -1) {
            System.out.println((char) len);
        }
        isr.close();
        bis.close();
    }

}
