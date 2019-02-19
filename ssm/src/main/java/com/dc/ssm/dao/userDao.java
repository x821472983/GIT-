package com.dc.ssm.dao;

import com.dc.ssm.po.report;
import com.dc.ssm.po.user_user;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface userDao {
    List<user_user> selectuser(@Param("user_id") int user_id);
    int insertReport(report report);
    int insertUserReport(@Param("user_id") int user_id,@Param("report_id") int report_id);
    report  selectReport(@Param("report_id") int report_id);
    List<report> notRead(@Param("user_id")int user_id);
    List<report> allReport(@Param("user_id")int user_id);
}
