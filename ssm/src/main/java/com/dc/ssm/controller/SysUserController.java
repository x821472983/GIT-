package com.dc.ssm.controller;

import com.base.annotation.AopOperation;
import com.base.constants.ConstantsBase;
import com.base.controller.BaseController;
import com.base.em.EnumRole;
import com.base.pojo.BaseModel;
import com.base.pojo.BusinessException;
import com.base.pojo.UploaderFileUtil;
import com.dc.ssm.po.SysUser;
import com.dc.ssm.po.user_user;
import com.dc.ssm.service.*;
import com.dc.ssm.vo.ReportVo;
import com.dc.ssm.vo.SysUserVo;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("sysUser")
public class SysUserController  extends BaseController {
    @Autowired
    LoginService loginService;
    @Autowired
    SysUserService userService;
    @Autowired
    SysRoleService roleService;
    @Autowired
    FileService fileService;
    @Autowired
    OperationLogService operationLogService;
    @Autowired
    userService user_user;


    @ApiOperation(value = "登出", notes = "登出", httpMethod = "POST")
    @RequestMapping("outLogin")
    public void outLogin()throws Exception{
        session.invalidate();
        response.sendRedirect("/views/system/login.jsp?exit=1");

    }

    @RequestMapping("t_sys_user")
    public BaseModel selectAllUser(BaseModel baseModel)throws Exception{
        userService.selectAllUser(baseModel);
        userService.selectAllUserLength(baseModel);
        return baseModel;
    }


    @AopOperation(desc = "查询用户角色", module = EnumRole.NO_204, type = ConstantsBase.FUN_SEARCH)
    @ApiOperation(value = "查询用户角色", notes = "查询用户角色", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="baseModel.queryParams.curr_page",
                    value = "当前页",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name="baseModel.queryParams.page_size",
                    value = "每页显示的记录数",paramType = "query",dataType = "int")
    })
    @RequestMapping("selectUser")
    public BaseModel selectUser(BaseModel baseModel)throws Exception{
        getSessionUser();
        userService.selectUser(baseModel);
        return baseModel;
    }


    @ApiOperation(value = "测试用户查询",notes="分页查询用户信息",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="baseModel.queryParams.curr_page",
                    value = "当前页",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name="baseModel.queryParams.page_size",
                    value = "每页显示的记录数",paramType = "query",dataType = "int")
    })
    @RequestMapping("selectPageUser")
    public BaseModel selectPgaeAllUser(BaseModel baseModel)throws Exception{
        userService.selectPageUser(baseModel);
        baseModel.setMessage("查询成功");
        return baseModel;
    }


    @AopOperation(desc = "查看用户个人信息", module = EnumRole.NO_204, type = ConstantsBase.FUN_SEARCH)
    @ApiOperation(value = "ID用户查询",notes="ID用户查询",httpMethod = "POST")
    @RequestMapping("/selectUserById/{user_id}")
    public BaseModel selectUserById(@PathVariable("user_id")int user_id,BaseModel baseModel)throws Exception{
        getSessionUser();
        int upload_id=0;
        userService.selectUserById(user_id,baseModel);
        if (((SysUser)baseModel.getData()).getHead_img_url()!=null&&
                !((SysUser)baseModel.getData()).getHead_img_url().equals("")){
            upload_id=Integer.valueOf(((SysUser)baseModel.getData()).getHead_img_url());
        }
        if (upload_id != 0) {
            fileService.selectFileLogById(upload_id,baseModel);
        }

        return baseModel;
    }


    @AopOperation(desc = "查看用户登录信息", module = EnumRole.NO_204, type = ConstantsBase.FUN_SEARCH)
    @ApiOperation(value = "ID用户登录查询",notes="ID用户登录查询",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="baseModel.queryParams.curr_page",
                    value = "当前页",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name="baseModel.queryParams.page_size",
                    value = "每页显示的记录数",paramType = "query",dataType = "int")
    })
    @RequestMapping("/selectLoginByUserId/{user_id}")
    public BaseModel selectLoginByUserId(@PathVariable("user_id")int user_id,BaseModel baseModel)throws Exception{
        getSessionUser();
        userService.selectLoginByUserId(user_id,baseModel);
        if(baseModel.getMessage().equals("暂无登陆记录")){
            throw new BusinessException("暂无登陆记录");
        }
        return baseModel;
    }


    @AopOperation(desc = "新增用户信息", module = EnumRole.NO_204, type = ConstantsBase.FUN_ADD)
    @ApiOperation(value = "新增用户",notes="新增用户",httpMethod = "POST")
    @RequestMapping("/insertUser")
    public BaseModel insertUser(SysUserVo sysUserVo, BaseModel baseModel)throws Exception{
        getSessionUser();
        userService.insertUser(sysUserVo, baseModel);
        return baseModel;
    }


    @ApiOperation(value = "role_view",notes="role_view",httpMethod = "POST")
    @RequestMapping("/role_view")
    public BaseModel selectRoleAndName(BaseModel baseModel)throws Exception{
        getSessionUser();
        roleService.selectRoleAndName(baseModel);
        return baseModel;
    }


    @AopOperation(desc = "删除用户信息", module = EnumRole.NO_204, type = ConstantsBase.FUN_DELETE)
    @ApiOperation(value = "deleted",notes="deleted",httpMethod = "POST")
    @RequestMapping("/deletedUser/{user_id}")
    public BaseModel deletedUser(@PathVariable("user_id")String id,BaseModel baseModel)throws Exception{
        getSessionUser();
        userService.deletedUser(id,baseModel);
        return baseModel;
    }



    @AopOperation(desc = "更改用户信息", module = EnumRole.NO_204, type = ConstantsBase.FUN_UPDATE)
    @ApiOperation(value = "userUpdate",notes="userUpdate",httpMethod = "POST")
    @RequestMapping("/userUpdate")
    public BaseModel userUpdate(SysUserVo sysUserVo,BaseModel baseModel)throws Exception{
        SysUser u =getSessionUser();
        String uploader=u.getName();
        String file_log_id="";
        if(!baseModel.getTempMFile()[0].isEmpty()){
            fileService.deleteFileLogById(Integer.valueOf(sysUserVo.getUser().getHead_img_url()),baseModel);
            fileService.insertFileLog(uploader,baseModel);
            file_log_id=baseModel.getMessage();
        }else {
            file_log_id=sysUserVo.getUser().getHead_img_url();
        }
        sysUserVo.getUser().setHead_img_url(file_log_id);
        userService.updateUser(sysUserVo,baseModel);
        baseModel.setMessage("修改成功");
        return baseModel;
    }



    @AopOperation(desc = "查找用户信息", module = EnumRole.NO_204, type = ConstantsBase.FUN_SEARCH)
    @ApiOperation(value = "selectUserRoleLikeName",notes="selectUserRoleLikeName",httpMethod = "POST")
    @RequestMapping("/selectUserRoleLikeName")
    public BaseModel selectUserRoleLikeName(BaseModel baseModel)throws Exception{
        getSessionUser();
        userService.selectUserRoleLikeName(baseModel);
        return baseModel;
    }

    @AopOperation(desc = "导出用户信息", module = EnumRole.NO_204, type = ConstantsBase.FUN_EXPORT)
    @RequestMapping("/xlsExportTemplate")
    public BaseModel xlsExportTemplate(BaseModel baseModel)throws Exception{
        getSessionUser();
        String path=userService.exportUserInfo(baseModel);
        UploaderFileUtil.download(response,path,"用户信息[全].xls");
        baseModel.setAopMessage("导出文件成功");
        return baseModel;
    }

    @AopOperation(desc = "导入用户信息", module = EnumRole.NO_204, type = ConstantsBase.FUN_IMPORT)
    @RequestMapping("/xlsImportTemplate")
    public BaseModel xlsImportTemplate(BaseModel baseModel)throws Exception{
        getSessionUser();
        userService.importUserInfo(baseModel, getSessionUser());
        baseModel.setAopMessage(baseModel.getMessage());
        return baseModel;
    }

    @AopOperation(desc = "更改用户状态", module = EnumRole.NO_204, type = ConstantsBase.FUN_UPDATE)
    @RequestMapping("/updateState/{user_id}/{state}")
    public BaseModel updateState(BaseModel baseModel,@PathVariable("user_id")int user_id,
                                 @PathVariable("state")int state)throws Exception{
        getSessionUser();
        userService.updateState(baseModel,user_id,state);
        return baseModel;
    }


    @ApiOperation(value = "selectuser",notes="selectuser",httpMethod = "POST")
    @RequestMapping("/UserChoice")
    public BaseModel selectuser(BaseModel baseModel)throws Exception{
        SysUser u =getSessionUser();
        int user_id=u.getUser_id();
        user_user.selectuser(user_id,baseModel);
        return baseModel;
    }


    @RequestMapping("/reportSend")
    public BaseModel reportSend(ReportVo reportVo,BaseModel baseModel)throws Exception{
        user_user user=new user_user();
        SysUser u =getSessionUser();
        user.setName(u.getName());
        user.setUser_id(u.getUser_id());
        reportVo.getReport().setIssuer(u.getName());
        user_user.reportSend(reportVo,baseModel,user);
        return baseModel;
    }

    @RequestMapping("/allReport")
    public BaseModel allReport(BaseModel baseModel)throws Exception{
        SysUser u =getSessionUser();
        int user_id=u.getUser_id();
        user_user.allReport(user_id,baseModel);
        return baseModel;
    }

    @RequestMapping("/notRead")
    public BaseModel notRead(BaseModel baseModel)throws Exception{
        SysUser u =getSessionUser();
        int user_id=u.getUser_id();
        user_user.notRead(user_id,baseModel);
        return baseModel;
    }

    @RequestMapping("/selectReport")
    public BaseModel selectReport(int report_id,BaseModel baseModel)throws Exception{
        getSessionUser();
        user_user.selectReport(report_id,baseModel);
        return baseModel;
    }
}
