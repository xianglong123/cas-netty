package com.cas.message;

import java.util.Set;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/30 12:22 下午
 * @desc
 */
public class GroupMembersResponseMessage extends AbstractResponseMessage{

    private Set<String> members;

    public GroupMembersResponseMessage(Set<String> members) {
        this.members = members;
    }

    public Set<String> getMembers() {
        return members;
    }

    public void setMembers(Set<String> members) {
        this.members = members;
    }

    @Override
    public int getMessageType() {
        return LoginResponseMessage;
    }
}
