package com.base.em;

public enum EnumRole {
    NO_203(203,"角色管理","查询，新增，修改，删除"),
    NO_204(204,"用户管理","查询，新增，修改，删除，导入，导出"),
    NO_103(103,"操作日志","查询"),
    NO_102(102,"登录日志","查询");
    int code;
    String name;
    String roleValue;
    EnumRole(int code,String name,String roleValue){
        this.code=code;
        this.name=name;
        this.roleValue=roleValue;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(String roleValue) {
        this.roleValue = roleValue;
    }
}
