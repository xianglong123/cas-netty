package com.cas.message;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/30 9:15 下午
 * @desc
 */
public class PongMessage extends Message{

    @Override
    public int getMessageType() {
        return PongMessage;
    }
}
