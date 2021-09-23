package com.cas.IO;

import java.io.*;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/22 2:30 下午
 * @desc
 */
public class BufferedOutputStreamTest {

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

    public static void main(String[] args) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(out1)) {
            bos.write("wo 爱 你".getBytes());
        }
    }

}
