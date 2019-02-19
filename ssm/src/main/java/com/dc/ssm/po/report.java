package com.dc.ssm.po;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class report {

    @ApiModelProperty(value = "发布人")
    private String issuer;
    @ApiModelProperty(value = "公告ID")
    private int report_id;
    @ApiModelProperty(value = "公告标题")
    private String report_title;
    @ApiModelProperty(value = "公告内容")
    private String report_content;



    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public String getReport_title() {
        return report_title;
    }

    public void setReport_title(String report_title) {
        this.report_title = report_title;
    }

    public String getReport_content() {
        return report_content;
    }

    public void setReport_content(String report_content) {
        this.report_content = report_content;
    }

    @Override
    public String toString() {
        return "report{" +
                ", issuer='" + issuer + '\'' +
                ", report_id=" + report_id +
                ", report_title='" + report_title + '\'' +
                ", report_content='" + report_content + '\'' +
                '}';
    }
}
