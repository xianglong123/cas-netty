package com.cas.message;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/30 3:55 下午
 * @desc
 */
public class GroupMembersRequestMessage extends Message{

    private String groupName;

    public GroupMembersRequestMessage(String groupName) {
        this.groupName = groupName;
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
