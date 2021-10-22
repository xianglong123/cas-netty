package com.cas.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

import static com.cas.netty.c4.TestByteBuf.log;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/21 4:24 下午
 * @desc 组合小的ByteBuf 成为一个大的ByteBuf
 */
public class TestCompositeByteBuf {

    public static void main(String[] args) {
        ByteBuf buf1 = ByteBufAllocator.DEFAULT.buffer();
        buf1.writeBytes(new byte[]{1,2,3,4,5});
        buf1.retain();

        ByteBuf buf2 = ByteBufAllocator.DEFAULT.buffer();
        buf2.writeBytes(new byte[]{6,7,8,9,10});
        buf2.retain();

        CompositeByteBuf byteBufs = ByteBufAllocator.DEFAULT.compositeBuffer();
        byteBufs.addComponents(true, buf1, buf2);

        log(byteBufs);
        buf1.release();
        buf2.release();
    }

}
