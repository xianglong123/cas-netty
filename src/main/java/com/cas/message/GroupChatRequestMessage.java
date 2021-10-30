package com.cas.message;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/30 3:55 下午
 * @desc
 */
public class GroupChatRequestMessage extends Message{

    private String from;
    private String groupName;
    private String content;


    public GroupChatRequestMessage(String from, String groupName, String content) {
        this.from = from;
        this.groupName = groupName;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
