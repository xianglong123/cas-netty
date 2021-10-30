package com.cas.message;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/30 12:22 下午
 * @desc
 */
public class LoginResponseMessage extends AbstractResponseMessage{

    public LoginResponseMessage(boolean success, String reason) {
        super(success, reason);
    }

    @Override
    public int getMessageType() {
        return LoginResponseMessage;
    }
}
