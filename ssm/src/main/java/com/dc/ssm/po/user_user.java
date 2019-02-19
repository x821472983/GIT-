package com.dc.ssm.po;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class user_user {
    @ApiModelProperty(value = "用户ID")
    private int user_id;
    @ApiModelProperty(value = "用户名")
    private String name;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "user_user{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                '}';
    }
}
