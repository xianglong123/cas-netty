package com.cas.server.service;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/10/29 11:29 下午
 * @desc
 */
public interface UserService {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return true 成功  false 失败
     */
    boolean login(String username, String password);

}
