package com.cas.server.session;

import com.cas.server.service.UserService;
import com.cas.server.service.UserServiceMemoryImpl;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/29 11:30 下午
 * @desc
 */
public abstract class SessionFactory {

    private static Session session;

    public static Session getSession() {
        if (session == null) {
            session = new SessionMemoryImpl();
        }
        return session;
    }

}
