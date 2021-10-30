package com.cas.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/30 12:19 上午
 * @desc
 */
public class ProcotolFrameDecoder extends LengthFieldBasedFrameDecoder {

    public ProcotolFrameDecoder() {
        this(1024, 12,4,0,0);
    }

    public ProcotolFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }
}
