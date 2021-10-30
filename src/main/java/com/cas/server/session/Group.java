package com.cas.server.session;

import java.util.Set;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/29 11:53 下午
 * @desc
 */
public class Group {

    public static final Group EMPTY_GROUP = new Group();

    private String name;

    private Set<String> members;

    public Group(){}

    public Group(String name, Set<String> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getMembers() {
        return members;
    }

    public void setMembers(Set<String> members) {
        this.members = members;
    }

}
