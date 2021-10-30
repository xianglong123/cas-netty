package com.cas.message;

import io.netty.channel.ChannelHandler;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/30 3:32 下午
 * @desc
 */
public class ChatRequestMessage extends Message{

    private String from;
    private String to;
    private String content;

    public ChatRequestMessage(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getMessageType() {
        return 0;
    }
}
