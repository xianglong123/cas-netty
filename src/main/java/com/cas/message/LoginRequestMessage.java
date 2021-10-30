package com.cas.message;

import lombok.Data;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/29 10:23 下午
 * @desc
 */
public class LoginRequestMessage extends Message {

    private String username;
    private String password;

    @Override
    public String toString() {
        return "LoginRequestMessage{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginRequestMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public int getMessageType() {
        return LoginRequestMessage;
    }
}
