package com.dc.ssm.vo;

import com.dc.ssm.po.report;
import com.dc.ssm.po.user_user;

import java.util.List;

public class ReportVo {
    int report_id;
    private List<user_user> user;
    private report report;

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public List<user_user> getUser() {
        return user;
    }

    public void setUser(List<user_user> user) {
        this.user = user;
    }

    public com.dc.ssm.po.report getReport() {
        return report;
    }

    public void setReport(com.dc.ssm.po.report report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "ReportVo{" +
                "report_id=" + report_id +
                ", user=" + user +
                ", report=" + report +
                '}';
    }
}
