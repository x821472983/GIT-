package com.dc.ssm.vo;

import com.dc.ssm.po.SysRole;
import com.dc.ssm.po.SysRolePermission;

import java.util.List;

public class SysRoleVo {
    int role_id;
    private SysRole role;
    private List<SysRolePermission> listRolePermissions;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public List<SysRolePermission> getListRolePermissions() {
        return listRolePermissions;
    }

    public void setListRolePermissions(List<SysRolePermission> listRolePermissions) {
        this.listRolePermissions = listRolePermissions;
    }

    @Override
    public String toString() {
        return "SysRoleVo{" +
                "role_id=" + role_id +
                ", role=" + role +
                ", listRolePermissions=" + listRolePermissions +
                '}';
    }
}
