package com.dc.ssm.service;

import com.base.pojo.BaseModel;
import com.dc.ssm.po.user_user;
import com.dc.ssm.vo.ReportVo;

public interface userService {
    void selectuser(int user_id, BaseModel baseModel)throws Exception;
    void reportSend(ReportVo reportVo,BaseModel baseModel,user_user user)throws Exception;
    void notRead(int user_id,BaseModel baseModel)throws Exception;
    void allReport(int user_id,BaseModel baseModel)throws Exception;
    void selectReport(int report_id,BaseModel baseModel)throws Exception;
}
