package com.cas.message;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/30 12:22 下午
 * @desc
 */
public class GroupCreateResponseMessage extends AbstractResponseMessage{

    private String from;
    private String content;

    public GroupCreateResponseMessage(String from, String content) {
        this.from = from;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GroupCreateResponseMessage(boolean success, String reason) {
        super(success, reason);
    }

    @Override
    public int getMessageType() {
        return GroupCreateResponseMessage;
    }
}
