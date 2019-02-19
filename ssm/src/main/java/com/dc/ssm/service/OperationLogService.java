package com.dc.ssm.service;

import com.base.pojo.BaseModel;
import com.dc.ssm.po.SysOperationLog;

public interface OperationLogService {
    void selectOperationLog(BaseModel baseModel)throws Exception;

    void insertOperationLog(SysOperationLog operationLog, BaseModel baseModel)throws Exception;
}
