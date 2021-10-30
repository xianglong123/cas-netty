package com.cas.netty.c8;

import java.io.Serializable;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/29 9:40 下午
 * @desc
 */
public abstract class Message implements Serializable {

    public static Class<?> getMessageClass(int messageType) {
        return null;
    }

    private int sequenceId;
    private int messageType;

    public abstract int getMessageType();

    public static final int LoginRequestMessage = 0;
    public static final int LoginResponseMessage = 1;
//    public static final int LoginRequestMessage = 0;
//    public static final int LoginRequestMessage = 0;
//    public static final int LoginRequestMessage = 0;
//    public static final int LoginRequestMessage = 0;
//    public static final int LoginRequestMessage = 0;
//    public static final int LoginRequestMessage = 0;
//    public static final int LoginRequestMessage = 0;
//    public static final int LoginRequestMessage = 0;


}
