package com.dc.ssm.po;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class SysUserDTO {
    @ApiModelProperty(value = "帐号")
    private String account;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "性别(0保密，1男，2女)")
    private String gender;
    @ApiModelProperty(value = "状态字符串")
    private String state_str;
    @ApiModelProperty(value = "状态(0冻结，1激活)")
    private int state;
    @ApiModelProperty(value = "手机")
    private String phone;
    @ApiModelProperty(value = "序号")
    private int count;
    @ApiModelProperty(value = "手机imei")
    private String imei;
    @ApiModelProperty(value = "角色名称")
    private String role_names;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "出生日期")
    private Date birthday;
    @ApiModelProperty(value = "出生日期字符串")
    private String birthday_str;
    @ApiModelProperty(value = "地址")
    private String address;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String user_name) {
        this.name = user_name;
    }

    public String getState_str() {
        return state_str;
    }

    public void setState_str(String state_str) {
        this.state_str = state_str;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getRole_names() {
        return role_names;
    }

    public void setRole_names(String role_names) {
        this.role_names = role_names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday_str() {
        return birthday_str;
    }

    public void setBirthday_str(String birthday_str) {
        this.birthday_str = birthday_str;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SysUserDTO{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", state_str='" + state_str + '\'' +
                ", state=" + state +
                ", phone='" + phone + '\'' +
                ", count=" + count +
                ", imei='" + imei + '\'' +
                ", role_names='" + role_names + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", birthday_str='" + birthday_str + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
