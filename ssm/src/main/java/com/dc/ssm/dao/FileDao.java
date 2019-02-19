package com.dc.ssm.dao;

import com.dc.ssm.po.SysFileLog;

public interface FileDao {
    // 插入文件上传日志
    int insertFileLog(SysFileLog sysFileLog) throws Exception;
    // 通过id查询文件上传日志
    SysFileLog selectFileLogById(int user_id) throws Exception;
    // 通过id删除文件日志
    int deleteFileLogById(int Head_img_url) throws Exception;


}
