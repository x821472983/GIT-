package com.dc.ssm.po;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class SysRolePermission {
    @ApiModelProperty(value = " 权限自增id")
    private int permission_id;
    @ApiModelProperty(value = " 角色id")
    private int fk_role_id;
    @ApiModelProperty(value = " 二级菜单编号")
    private String code;
    @ApiModelProperty(value = " 权限值")
    private String permission_value;
    private int state;
    @ApiModelProperty(value = " 创建人用户id")
    private int create_user_id;
    @ApiModelProperty(value = " 创建人用户名")
    private String create_user_name;
    @ApiModelProperty(value = " 创建时间")
    private Date create_time;
    @ApiModelProperty(value = " 更新人用户id")
    private int update_user_id;
    @ApiModelProperty(value = "更新人用户名")
    private String update_user_name;
    @ApiModelProperty(value = "更新时间")
    private Date update_time;
    @ApiModelProperty(value = "是否删除，1标记已删除")
    private int is_deleted;
    @ApiModelProperty(value = "乐观锁")
    private int version;

    public int getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }

    public int getFk_role_id() {
        return fk_role_id;
    }

    public void setFk_role_id(int fk_role_id) {
        this.fk_role_id = fk_role_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPermission_value() {
        return permission_value;
    }

    public void setPermission_value(String permission_value) {
        this.permission_value = permission_value;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(int create_user_id) {
        this.create_user_id = create_user_id;
    }

    public String getCreate_user_name() {
        return create_user_name;
    }

    public void setCreate_user_name(String create_user_name) {
        this.create_user_name = create_user_name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getUpdate_user_id() {
        return update_user_id;
    }

    public void setUpdate_user_id(int update_user_id) {
        this.update_user_id = update_user_id;
    }

    public String getUpdate_user_name() {
        return update_user_name;
    }

    public void setUpdate_user_name(String update_user_name) {
        this.update_user_name = update_user_name;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "SysRolePermission{" +
                "permission_id=" + permission_id +
                ", fk_role_id=" + fk_role_id +
                ", code='" + code + '\'' +
                ", permission_value='" + permission_value + '\'' +
                ", state=" + state +
                ", create_user_id=" + create_user_id +
                ", create_user_name='" + create_user_name + '\'' +
                ", create_time=" + create_time +
                ", update_user_id=" + update_user_id +
                ", update_user_name='" + update_user_name + '\'' +
                ", update_time=" + update_time +
                ", is_deleted=" + is_deleted +
                ", version=" + version +
                '}';
    }
}
