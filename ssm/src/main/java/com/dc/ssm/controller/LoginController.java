package com.dc.ssm.controller;

import com.base.constants.ConstantsBase;
import com.base.controller.BaseController;
import com.base.pojo.BaseModel;
import com.base.pojo.BusinessException;
import com.base.rsa.RSAJS;
import com.dc.ssm.po.SysLoginLog;
import com.dc.ssm.service.LoginService;
import com.dc.ssm.service.SysUserService;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.HttpMethod;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
    private static Logger logger = Logger.getLogger(BaseModel.class);
    @Autowired
    LoginService loginService;
    @Autowired
    SysUserService userService;


    @RequestMapping("/login")
    public BaseModel selectPasswordByAccount(SysLoginLog sysLogin, BaseModel baseModel) throws Exception {
        String account = sysLogin.getUser_account();
        String PW = sysLogin.getUser_password();
        if (account == null && PW == null) {
            throw new Exception("账号密码不能为空");
        }
        String EPW = RSAJS.sessionDecryptHexStr(session, PW);
        sysLogin.setUser_password(EPW);
        sysLogin.setSession_id(session.getId());
        sysLogin.setModel_number(request.getHeader("User-Agent"));
        sysLogin.setRequest_ip(request.getRemoteAddr());
        loginService.LoginDaily(sysLogin, baseModel);
        if (baseModel.getResultCode() == 0) {
            session.setAttribute(ConstantsBase.SESSION_USER, baseModel.getData());
            session.setAttribute(ConstantsBase.SESSION_PERMISSION,loginService.selectRolePermissionByUserId(sysLogin.getUser_id()));
        }
        return baseModel;
    }


    @RequestMapping("/outLogin")
    public void outLogin() throws Exception {
        session.invalidate();
        response.sendRedirect("/views/system/login.jsp?exit=1");

    }


    @ApiOperation(value = "用户登录日志查询", notes = "用户登录日志查询", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "baseModel.queryParams.curr_page",
                    value = "当前页", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "baseModel.queryParams.page_size",
                    value = "每页显示的记录数", paramType = "query", dataType = "int")
    })
    @RequestMapping("/selectLoginLog")
    public BaseModel selectLoginLog(BaseModel baseModel) throws Exception {
        getSessionUser();
        loginService.selectLoginLog(baseModel);
        if (baseModel.getData() == null || baseModel.getData().equals("")) {
            throw new BusinessException("查询登录日志错误");
        } else {
            baseModel.setMessage("查询成功");
        }
        return baseModel;
    }

    @ApiOperation(value = "查询权限菜单", notes = "查询权限菜单", httpMethod = HttpMethod.GET)
    @RequestMapping("findMyMenu")
    public BaseModel findMyMenu(BaseModel baseModel)throws Exception{
        loginService.selectMenuByUserId(getSessionUser().getUser_id(),baseModel);
        return baseModel;
    }
}
