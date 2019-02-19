package com.dc.ssm.service;

import com.base.pojo.BaseModel;
import com.dc.ssm.po.SysUser;
import com.dc.ssm.vo.SysUserVo;

import javax.servlet.http.HttpSession;

public interface SysUserService {
    void selectAllUser(BaseModel baseModel);
    void insertUser(SysUserVo sysUserVo, BaseModel baseModel)throws Exception;
    void updateUser(SysUserVo sysUserVo,BaseModel baseModel)throws Exception;
    void removeUser(String ids, BaseModel baseModel)throws Exception;
    void selectPasswordByAccount(SysUser sysUser, BaseModel baseModel, HttpSession session)throws Exception;
    void selectAllUserLength(BaseModel baseModel)throws Exception;
    void selectUser(BaseModel baseModel)throws Exception;
    void selectPageUser(BaseModel baseModel)throws Exception;
    void selectUserById(int id, BaseModel baseModel)throws Exception;
    void selectLoginByUserId(int id, BaseModel baseModel)throws Exception;
    void selectRoleName(BaseModel baseModel)throws Exception;
    void deletedUser(String id,BaseModel baseModel)throws Exception;
    void selectUserRoleLikeName(BaseModel baseModel)throws Exception;
    String exportUserInfo(BaseModel baseModel)throws Exception;
    void importUserInfo(BaseModel baseModel, SysUser sysUser) throws Exception;
    void updateState(BaseModel baseModel,int user_id,int state)throws Exception;
}
