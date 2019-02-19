package com.dc.ssm.vo;

import com.dc.ssm.po.SysUser;

public class SysUserRoleVo {
    private String role_ids;
    private String role_names;
    private SysUser user;
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRole_ids() {
        return role_ids;
    }

    public void setRole_ids(String role_ids) {
        this.role_ids = role_ids;
    }

    public String getRole_names() {
        return role_names;
    }

    public void setRole_names(String role_names) {
        this.role_names = role_names;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SysUserRoleVo{" +
                "role_ids='" + role_ids + '\'' +
                ", role_names='" + role_names + '\'' +
                ", sysUser=" + user +
                ", user_id=" + user_id +
                '}';
    }
}
