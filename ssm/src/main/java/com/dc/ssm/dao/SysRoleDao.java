package com.dc.ssm.dao;

import com.base.pojo.QueryParams;
import com.dc.ssm.po.SysMenu;
import com.dc.ssm.po.SysRole;
import com.dc.ssm.po.SysRolePermission;
import com.dc.ssm.vo.SysRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleDao {
    int addRole(SysRole role);
    int insertRolePermission(SysRolePermission rolePermission);
    SysRole selectRoleByName(@Param("name")String name);
    List<SysRole> selectRole();
    SysRoleVo selectRoleVoById(int id);
    int removeRolePermission(@Param("id")String id);
    int removeRole(@Param("id")String id);
    int updateRolePermission(SysRolePermission rolePermission);
    int deleteRolePermissionById(int id);
    int updataRoleById(SysRole sysRole);
    List<SysRole> selectRoleLikeName(@Param("name")String name);
    List<SysRole> selectRoleByLikeName(QueryParams where);

    List<SysRolePermission> selectRolePermissionByUserId(int user_id);

    List<SysMenu> selectMenuByUserId(int user_id);

    List<SysMenu> selectMenuByMenuId(@Param("ids") String ids);
}
