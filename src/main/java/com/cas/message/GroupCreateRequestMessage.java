package com.cas.message;

import java.util.Set;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/30 4:05 下午
 * @desc
 */
public class GroupCreateRequestMessage extends Message{

    private String groupName;
    private Set<String> members;

    public GroupCreateRequestMessage(String groupName, Set<String> members) {
        this.groupName = groupName;
        this.members = members;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<String> getMembers() {
        return members;
    }

    public void setMembers(Set<String> members) {
        this.members = members;
    }

    @Override
    public int getMessageType() {
        return 0;
    }
}
