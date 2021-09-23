package com.cas.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/22 1:46 下午
 * @desc
 */
public class PrintStreamTest {

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

    public static void main(String[] args) {
        try (PrintStream ps = new PrintStream(out1)) {
            ps.print("wc");
        }
    }

}
