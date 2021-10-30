package com.cas.message;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/30 3:55 下午
 * @desc
 */
public class GroupQuitRequestMessage extends Message{

    private String username;
    private String groupName;

    public GroupQuitRequestMessage(String username, String groupName) {
        this.username = username;
        this.groupName = groupName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public int getMessageType() {
        return GroupQuitRequestMessage;
    }
}
