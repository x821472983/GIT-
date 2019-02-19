package com.dc.ssm.service;

import com.base.pojo.BaseModel;
import com.dc.ssm.po.SysLoginLog;

import java.util.Map;

public interface LoginService {
    void LoginDaily(SysLoginLog sysLogin, BaseModel baseModel)throws Exception;

    void selectLoginLog(BaseModel baseModel)throws Exception;

    void selectMenuByUserId(int user_id,BaseModel baseModel)throws Exception;

    Map<String,String> selectRolePermissionByUserId(int user_id)throws Exception;

    void selectAllMenu()throws Exception;
}
