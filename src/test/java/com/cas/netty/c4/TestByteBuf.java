package com.cas.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;
import static io.netty.util.internal.StringUtil.NEWLINE;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/15 8:25 上午
 * @desc
 */
public class TestByteBuf {

    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(); // 默认256，支持自动扩容
        buffer.writeBoolean(false);
        /**
         * class io.netty.buffer.PooledUnsafeDirectByteBuf
         * 池化 直接内存
         * 可以通过系统变量设置池化：-Dio.netty.allocator.type=[unpooled|pooled]
         *
         *
         * 非池化：
         * class io.netty.buffer.UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeNoCleanerDirectByteBuf
         */
        System.out.println(buffer.getClass());
        log(buffer);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            sb.append("a");
        }
        buffer.writeBytes(sb.toString().getBytes());
        log(buffer);

    }

    /**
     * @param byteBuf
     */
    public static void log(ByteBuf byteBuf) {
        int length = byteBuf.readableBytes();
        int rows = length / 16 + (length % 5 == 0 ? 0 : 1) + 4;
        StringBuilder buf = new StringBuilder(rows * 80 * 2)
                .append("read index:").append(byteBuf.readerIndex())
                .append(" write index:").append(byteBuf.writerIndex())
                .append(" capacity:").append(byteBuf.capacity())
                .append(NEWLINE);
        appendPrettyHexDump(buf, byteBuf);
        System.out.println(buf.toString());
    }

}
