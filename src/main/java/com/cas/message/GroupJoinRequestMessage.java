package com.cas.message;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/30 3:55 下午
 * @desc
 */
public class GroupJoinRequestMessage extends Message{

    private String username;
    private String groupName;

    public GroupJoinRequestMessage(String userName, String groupName) {
        this.username = userName;
        this.groupName = groupName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public int getMessageType() {
        return 0;
    }
}
