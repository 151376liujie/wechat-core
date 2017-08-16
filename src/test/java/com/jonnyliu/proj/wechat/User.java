package com.jonnyliu.proj.wechat;

import java.io.Serializable;

/**
 * Author: jonny
 * Time: 2017-08-16 22:08.
 */
public class User implements Serializable {

    private String Username;

    private int Age;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        this.Age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "Username='" + Username + '\'' +
                ", Age=" + Age +
                '}';
    }
}
