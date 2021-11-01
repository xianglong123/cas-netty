package com.cas.server.service;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/11/1 5:51 下午
 * @desc
 */
public class ServicesFactory {

    static Map<Class<?>, Object> map = new ConcurrentHashMap<>();

    static {
        try {
            Class<?> clazz = Class.forName("com.cas.server.service.HelloService");
            Object instance = Class.forName("com.cas.server.service.HelloServiceImpl").newInstance();
            map.put(clazz, instance);
    } catch(ClassNotFoundException |IllegalAccessException |
    InstantiationException e)

    {
        e.printStackTrace();
    }

}

    public static <T> T getService(Class<T> interfaceClass) {
        return (T) map.get(interfaceClass);
    }


}
