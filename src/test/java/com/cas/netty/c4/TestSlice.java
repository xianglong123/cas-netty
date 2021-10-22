package com.cas.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import static com.cas.netty.c4.TestByteBuf.log;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/21 3:08 下午
 * @desc
 */
public class TestSlice {

    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        buf.writeBytes(new byte[]{'a','b','c','d','e','f','g','h','i','j'});
        log(buf);

        // 在切片过程中，没有发生数据复制
        ByteBuf f1 = buf.slice(0, 5);
        f1.retain();

        ByteBuf f2 = buf.slice(5, 5);
        f2.retain();
        log(f1);
        log(f2);

        // 切片对最大容量做了限制
//        f1.writeBytes("a".getBytes());
        System.out.println("释放缘由内存");
        buf.release();

        log(f1);

        f1.setByte(0, 'b');
        log(f1);
        log(buf);


        f1.release();
        f2.release();
    }

}
