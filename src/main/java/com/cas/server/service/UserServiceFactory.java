package com.cas.server.service;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/29 11:30 下午
 * @desc
 */
public abstract class UserServiceFactory {

    private static UserService userService;

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserServiceMemoryImpl();
        }
        return userService;
    }

}
