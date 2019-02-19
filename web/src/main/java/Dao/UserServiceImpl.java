/*
package Dao;

import RSA.RSAJS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.SysUser;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl {
    @Autowired
    private UserDaolmpl userDaolmpl;
    //通过UserDaoFactory获取UserDao对象

    public boolean successlogin(SysUser user, HttpSession session)throws Exception{
        if(user.getName()==null||user.getPassword()==null
                ||user.getName().equals("")||user.getPassword().equals(""))
            return false;
        String password=RSAJS.sessionDecryptHexStr(session,user.getPassword());
        String realpassword=userDaolmpl.getPassword(user.getName());
        return realpassword!=null&&password.equals(realpassword);
    }
}
*/
