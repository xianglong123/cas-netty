package com.cas.netty.c8;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/29 10:23 下午
 * @desc
 */
public class LoginRequestMessage extends Message{

    private String username;
    private String password;
    private String nickname;

    public LoginRequestMessage(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public int getMessageType() {
        return 0;
    }
}
