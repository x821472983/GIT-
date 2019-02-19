package com.dc.ssm.dao;

import com.dc.ssm.po.SysLoginLog;
import com.dc.ssm.po.SysMenu;

import java.util.List;

public interface LoginLogDao {
    int insertLoginLog(SysLoginLog sysLogin)throws Exception;

    List<SysLoginLog> selectLoginLog()throws Exception;

    List<SysMenu> selectAllMenu();
}
