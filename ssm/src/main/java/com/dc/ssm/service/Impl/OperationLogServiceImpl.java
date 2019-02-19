package com.dc.ssm.service.Impl;

import com.base.pojo.BaseModel;
import com.base.pojo.BusinessException;
import com.dc.ssm.dao.OperationLogDao;
import com.dc.ssm.po.SysOperationLog;
import com.dc.ssm.service.OperationLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationLogServiceImpl implements OperationLogService {
    @Autowired
    OperationLogDao operationLogDao;

    @Override
    public void selectOperationLog(BaseModel baseModel) throws Exception {
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page()
                , baseModel.getQueryParams().getPage_size());
        List<SysOperationLog> logList = operationLogDao.selectOperationLog();
        PageInfo page = new PageInfo(logList);
        baseModel.setData(page);
    }

    @Override
    public void insertOperationLog(SysOperationLog operationLog, BaseModel baseModel) throws Exception {
        int count=operationLogDao.insertOperationLog(operationLog);
        if(count==0){
            throw new BusinessException("操作日志插入失败");
        }else{
            baseModel.setMessage("登录日志插入成功");
        }

    }
}
