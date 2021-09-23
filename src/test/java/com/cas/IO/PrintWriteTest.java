package com.cas.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;


/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/22 12:11 下午
 * @desc 可以输入打印流
 * 特点： 可以一行一行的输出，自动帮你换行
 */
public class PrintWriteTest {

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
        PrintWriter pw = new PrintWriter(out1);
        pw.print("woaini");
        pw.flush();
        pw.close();
    }

}
