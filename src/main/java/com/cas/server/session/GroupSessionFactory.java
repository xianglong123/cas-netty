package com.cas.server.session;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/29 11:30 下午
 * @desc
 */
public abstract class GroupSessionFactory {

    private static GroupSession session;

    public static GroupSession getGroupSession() {
        if (session == null) {
            session = new GroupSessionMemoryImpl();
        }
        return session;
    }

}
