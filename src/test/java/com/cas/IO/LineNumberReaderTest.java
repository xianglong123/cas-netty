package com.cas.IO;

import java.io.*;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/22 11:38 上午
 * @desc
 */
public class LineNumberReaderTest {

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
     * number: 1  test1
     * number: 2  test2
     * number: 3  test3
     * number: 4  test4
     * number: 5  test5
     * @param args
     */
    public static void main(String[] args) {
        try (LineNumberReader lnr = new LineNumberReader(new InputStreamReader(in1))) {
            lnr.setLineNumber(0);
            String line;
            while ((line = lnr.readLine()) != null) {
                System.out.println("number: " + lnr.getLineNumber() + "  " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
