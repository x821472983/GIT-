package com.dc.ssm.service;

import com.base.pojo.BaseModel;
import com.dc.ssm.vo.SysRoleVo;

public interface SysRoleService {
    void inserRole(SysRoleVo sysRoleVo, BaseModel baseModel)throws Exception;

    void selectRole(BaseModel baseModel)throws Exception;

    void selectRoleById(int id,BaseModel baseModel)throws Exception;

    void removeRoleAndPermission(String id,BaseModel baseModel)throws Exception;

    /*void updateRolePermission(SysRoleVo sysRoleVo, BaseModel baseModel)throws Exception;*/

    void updataRoleById(int id,SysRoleVo sysRoleVo)throws Exception;
    void selectRoleLikeName(String name,BaseModel baseModel)throws Exception;

    void testSearch(BaseModel baseModel)throws Exception;

    void selectRoleAndName(BaseModel baseModel)throws Exception;
}
