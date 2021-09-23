package com.cas.IO.data;

import java.io.*;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/22 9:57 上午
 * @desc 将test.txt 中的数据 转换到 test1.txt 中，实现方式
 */
public class DataOutputStreamTest {


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

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        test2Test1ByData();
//        test2Test1ByWriter();
//        test2Test1ByByteArray();
//        test2Test1ByObj();
        test2Test1ByBuffer();
    }

    /**
     * 通过缓冲流
     *
     * 特点：可以从缓冲数组获取数据，效率高，不会直接从磁盘获取
     * @throws IOException
     */
    private static void test2Test1ByBuffer() throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(in1);BufferedOutputStream bos = new BufferedOutputStream(out1)) {
            byte[] bi = new byte[1024];
            bis.read(bi);
            bos.write(bi);
        }
    }

    /**
     * 对象也可以通过对象流，复制粘贴
     * 读取和写入的对象要实现序列化，且要加入序列版本号
     *
     * 特点：可以对对象进行序列化、和反序列化
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static void test2Test1ByObj() throws IOException, ClassNotFoundException {
        createOutStream();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/xianglong/IdeaProjects/cas-netty/src/test/java/com/cas/IO/data/test1.txt"));
        User o = (User)ois.readObject();
        System.out.println(o.getAge() + " - " + o.getName());
        ois.close();
    }

    private static void createOutStream() throws IOException {
        User user = new User("xl", "24");
        ObjectOutputStream ous = new ObjectOutputStream(out1);
        ous.writeObject(user);
        ous.flush();
        ous.close();
    }


    /**
     * 通过 byteArray进行读入写出
     *
     * 特点： 字节流转换为字节数组
     * @throws IOException
     */
    private static void test2Test1ByByteArray() throws IOException {
        byte[] bty = new byte[in1.available()];
        in1.read(bty);
        ByteArrayInputStream bais = new ByteArrayInputStream(bty);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int c;
            while ((c = bais.read()) != -1) {
                baos.write(Character.toUpperCase((char) c));
            }
            // 上面的数据类似在缓存中，下面一次性写入
            baos.writeTo(out1);
            baos.flush();
        }
    }

    /**
     * 通过字节流 转 字符流
     *
     * 特点：可以将字节流转换为字符流，字符流转换为字节流
     * @throws IOException
     */
    private static void test2Test1ByWriter() throws IOException {
        InputStreamReader isr = new InputStreamReader(in1);
        OutputStreamWriter osw = new OutputStreamWriter(out1);
        int res;
        while ((res = isr.read()) != -1) {
            osw.write((char) res);
        }
        osw.flush();
        isr.close();
        osw.close();
    }

    /**
     * 通过 DataInputStream BufferedReader InputStreamReader 实现
     * 数据输入流 -> 字符输入流 -> 缓存字符输入流
     * 特点：用来装饰其它输入流，它“允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型"
     * @throws IOException
     */
    private static void test2Test1ByData() throws IOException {
        // 文件流输入流 -> 数据流输入流
        DataInputStream in = new DataInputStream(in1);
        // 文件流输出流 -> 数据流输出流
        DataOutputStream out = new DataOutputStream(out1);
        // 数据输入流 -> 字符输入流 -> 缓存字符输入流
        BufferedReader d = new BufferedReader(new InputStreamReader(in));
        //
        String count;
        while ((count = d.readLine()) != null) {
            String u = count.toUpperCase();
            System.out.println(u);
            out.writeBytes(u + "  ,");
        }
        d.close();
        out.close();
    }
}
