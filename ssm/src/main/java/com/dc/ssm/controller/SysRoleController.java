package com.dc.ssm.controller;

import com.base.annotation.AopOperation;
import com.base.constants.ConstantsBase;
import com.base.controller.BaseController;
import com.base.em.EnumRole;
import com.base.pojo.BaseModel;
import com.base.pojo.BusinessException;
import com.dc.ssm.po.SysUser;
import com.dc.ssm.service.SysRoleService;
import com.dc.ssm.vo.SysRoleVo;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Role")
public class SysRoleController extends BaseController {


    @Autowired
    SysRoleService roleService;



    /*新增角色信息*/
    @AopOperation(desc = "新增角色信息", module = EnumRole.NO_203, type = ConstantsBase.FUN_ADD)
    @ApiOperation(value = "新增角色", notes = "新增角色和角色权限", httpMethod = "POST")
    @RequestMapping("/addRoleVo")
    public BaseModel insetRoleVo(@ModelAttribute SysRoleVo roleVo, BaseModel baseModel)throws Exception{
        SysUser user=getSessionUser();
        if(roleVo.getRole()==null){
            throw new BusinessException("角色信息不能为空");
        }
        if(roleVo.getListRolePermissions()==null){
            throw new BusinessException("角色权限不能为空");
        }
        if(roleVo.getRole().getName()==null&&"".equals(roleVo.getRole().getName())){
            throw new BusinessException("角色名不能为空");
        }
        roleVo.getRole().setCreate_user_id(user.getUser_id());
        roleVo.getRole().setCreate_user_name(user.getName());
        roleService.inserRole(roleVo,baseModel);
        baseModel.setMessage("新增角色‘"+roleVo.getRole().getName()+"’信息成功");
        return baseModel;
    }


    /*查询角色信息*/
    @AopOperation(desc = "查询角色信息", module = EnumRole.NO_203, type = ConstantsBase.FUN_SEARCH)
    @ApiOperation(value = "查询角色信息",notes="分页查询角色信息",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="baseModel.queryParams.curr_page",
                    value = "当前页",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name="baseModel.queryParams.page_size",
                    value = "每页显示的记录数",paramType = "query",dataType = "int")
    })
    @RequestMapping("/selectRole")
    public BaseModel selectRole(BaseModel baseModel)throws Exception{
        getSessionUser();
        roleService.selectRole(baseModel);
        return  baseModel;
    }


    /*根据ID查询角色信息*/
    @AopOperation(desc = "查看角色信息", module = EnumRole.NO_203, type = ConstantsBase.FUN_SEARCH)
    @ApiOperation(value = "根据ID查询角色信息", notes = "根据ID查询角色信息", httpMethod = "POST")
    @RequestMapping("/selectRoleById/{role_id}")
    public BaseModel selectRoleById(@PathVariable("role_id") int role, BaseModel baseModel)throws Exception{
        getSessionUser();
        roleService.selectRoleById(role,baseModel);
        return  baseModel;
    }

    @AopOperation(desc = "删除角色信息", module = EnumRole.NO_203, type = ConstantsBase.FUN_DELETE)
    @ApiOperation(value = "根据ID删除角色信息", notes = "根据ID删除角色信息", httpMethod = "POST")
    @RequestMapping("/removeRoleAndPermission/{role_id}")
    public BaseModel removeRoleAndPermission(@PathVariable("role_id")String role, BaseModel baseModel)throws Exception{
        getSessionUser();
        roleService.removeRoleAndPermission(role,baseModel);
        return baseModel;
    }



    @AopOperation(desc = "修改角色信息", module = EnumRole.NO_203, type = ConstantsBase.FUN_UPDATE)
    @ApiOperation(value = "修改角色", notes = "修改角色", httpMethod = "POST")
    @RequestMapping("/updateRolePermission/{role_id}")
    public BaseModel updateRolePermission(@PathVariable("role_id")int role_id,@ModelAttribute SysRoleVo roleVo, BaseModel baseModel)throws Exception{
        getSessionUser();
        if(roleVo.getRole()==null){
            throw new BusinessException("角色信息不能为空");
        }
        if(roleVo.getListRolePermissions()==null){
            throw new BusinessException("角色权限不能为空");
        }
        if(roleVo.getRole().getName()==null&&"".equals(roleVo.getRole().getName())){
            throw new BusinessException("角色名不能为空");
        }
        roleService.updataRoleById(role_id,roleVo);
        baseModel.setMessage("修改角色‘"+roleVo.getRole().getName()+"’信息成功");
        return baseModel;
    }

    @AopOperation(desc = "查找角色信息", module = EnumRole.NO_203, type = ConstantsBase.FUN_SEARCH)
    @ApiOperation(value = "模糊查找", notes = "模糊查找", httpMethod = "POST")
    @RequestMapping("/selectRoleLikeName")
    public BaseModel selectRoleLikeName(BaseModel baseModel)throws Exception{
        getSessionUser();
        roleService.testSearch(baseModel);
        if(baseModel.getData()==null){
            throw new BusinessException("查询用户不存在");
        }
        return baseModel;
    }
}
