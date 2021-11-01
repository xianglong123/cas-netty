package com.cas.server.service;

import org.springframework.stereotype.Service;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/11/1 5:49 下午
 * @desc
 */
@Service
public class HelloServiceImpl implements HelloService{

    @Override
    public String sayHello(String msg) {
//        int i = 1/0;
        return "你好 " + msg;
    }
}
