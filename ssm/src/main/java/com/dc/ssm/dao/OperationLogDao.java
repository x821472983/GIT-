package com.dc.ssm.dao;

import com.dc.ssm.po.SysOperationLog;

import java.util.List;

public interface OperationLogDao {
    List<SysOperationLog> selectOperationLog()throws Exception;

    int insertOperationLog(SysOperationLog sysOperationLog)throws Exception;
}
