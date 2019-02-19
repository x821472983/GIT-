package com.dc.ssm.vo;

import com.dc.ssm.po.SysRole;
import com.dc.ssm.po.SysUser;

import java.util.List;

public class SysUserVo {
    private int user_id;
    private int role_id;
    private String role_ids;
    private SysUser user;
    private List<SysRole> roleList;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_ids() {
        return role_ids;
    }

    public void setRole_ids(String role_ids) {
        this.role_ids = role_ids;
    }

    @Override
    public String toString() {
        return "SysUserVo{" +
                "user_id=" + user_id +
                ", role_id=" + role_id +
                ", role_ids='" + role_ids + '\'' +
                ", user=" + user +
                ", roleList=" + roleList +
                '}';
    }
}
