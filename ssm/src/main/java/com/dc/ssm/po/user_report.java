package com.dc.ssm.po;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class user_report {
    @ApiModelProperty(value = "ID")
    private int sr_id;
    @ApiModelProperty(value = "用户名")
    private String user_name;
    @ApiModelProperty(value = "公告ID")
    private int report_id;
    @ApiModelProperty (value = "阅读状态，0未读，1已阅")
    private int read_state;

    public int getSr_id() {
        return sr_id;
    }

    public void setSr_id(int sr_id) {
        this.sr_id = sr_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getRead_state() {
        return read_state;
    }

    public void setRead_state(int read_state) {
        this.read_state = read_state;
    }

    @Override
    public String toString() {
        return "user_report{" +
                "sr_id=" + sr_id +
                ", user_name='" + user_name + '\'' +
                ", report_id=" + report_id +
                ", read_state=" + read_state +
                '}';
    }
}
