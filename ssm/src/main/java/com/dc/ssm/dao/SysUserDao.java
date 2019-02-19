package com.dc.ssm.dao;

import com.base.pojo.QueryParams;
import com.dc.ssm.po.SysLoginLog;
import com.dc.ssm.po.SysRole;
import com.dc.ssm.po.SysUser;
import com.dc.ssm.vo.SysUserRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserDao {
    List<SysUser> selectAllUser();

    int selectAllUserLength();

    int insertUser(SysUser sysUser);

    /*新增t_sys_user_role表*/
    int insertUserAndRole(@Param("user_id") int user_id, @Param("role_id") int role_id);

    int updateUser(SysUser sysUser);

    SysUser selectUserByUserId(int user_id);

    List<SysLoginLog> selectLoginByUserId(int user_id);

    int removeUser(@Param("ids") String ids);

    SysUser selectUserByAccount(@Param("account") String account) throws Exception;

    List<SysUserRoleVo> selectUser();

    List<SysRole> selectRoleName();


    int deletedUser(@Param("id") String id);

    int deletedUserRole(@Param("id") String id);

    String selectRoleIdByUserId(@Param("user_id") int user_id);

    /*删除t_sys_user_role表*/
    int deletedUser_Role(@Param("user_id") int user_id);

    List<SysUserRoleVo> selectUserByCondition(QueryParams queryParams)throws Exception;

    int updateState(@Param("user_id") int user_id,@Param("state") int state);
}
