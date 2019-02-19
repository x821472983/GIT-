package Mybatis;

import user.SysUser;

import java.util.List;

public interface UserDao2 {
    public List<SysUser> selectAllUser() throws Exception;

    public SysUser selectUserById(int id) throws Exception;

    public  int insertUser(SysUser user) throws  Exception;

    public int updateUser(SysUser user) throws Exception;

    public int deleteUser(int id) throws Exception;
}
