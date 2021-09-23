package com.cas.IO;

import java.io.*;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/17 11:07 下午
 * @desc OutputStreamWriter : 字符流转字节流
 */
public class OutputStreamWriterTest {

    public static void main(String[] args) throws IOException {
        OutputStream os = new FileOutputStream("/Users/xianglong/IdeaProjects/cas-netty/src/test/java/com/cas/IO/a.txt");
        OutputStreamWriter osw = new OutputStreamWriter(os);
        osw.write("我爱你");
        os.flush();
        osw.flush();
        os.close();
        osw.close();
    }


}
