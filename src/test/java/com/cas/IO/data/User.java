package com.cas.IO.data;

import java.io.Serializable;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/22 11:17 上午
 * @desc
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String age;

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
