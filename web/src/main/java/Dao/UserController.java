/*
package Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import user.BaseModel;
import user.SysUser;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;


    @ResponseBody
    @RequestMapping(value = "/login2",method =RequestMethod.POST)
    public BaseModel login(SysUser user, HttpServletRequest request,BaseModel baseModel)throws Exception{
        if(userServiceImpl.successlogin(user,request.getSession())){
        request.getSession().setAttribute("user_name",user.getName());
        baseModel.setMessage("登陆成功");
        return baseModel;
}
        return baseModel;
                }


}*/
