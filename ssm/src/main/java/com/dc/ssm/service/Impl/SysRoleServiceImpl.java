package com.dc.ssm.service.Impl;

import com.base.pojo.AdvancedQuery;
import com.base.pojo.BaseModel;
import com.base.pojo.BusinessException;
import com.dc.ssm.dao.SysRoleDao;
import com.dc.ssm.po.SysRole;
import com.dc.ssm.po.SysRolePermission;
import com.dc.ssm.service.SysRoleService;
import com.dc.ssm.vo.SysRoleVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    SysRoleDao roleDao;

    @Override
    public void inserRole(SysRoleVo sysRoleVo, BaseModel baseModel) throws Exception {
        SysRole oldRold = roleDao.selectRoleByName(sysRoleVo.getRole().getName());
        int num = 0;
        if (oldRold != null) {
            throw new BusinessException("角色名称已存在，请勿重复添加");
        }
        int count = roleDao.addRole(sysRoleVo.getRole());
        if (count == 0) {
            throw new BusinessException("新增角色失败");
        }
        for (SysRolePermission permission : sysRoleVo.getListRolePermissions()) {
            if (permission.getPermission_value() == null || !permission.getPermission_value().contains("1")) {
                continue;
            }
            permission.setFk_role_id(sysRoleVo.getRole().getRole_id());
            permission.setCreate_user_name(sysRoleVo.getRole().getCreate_user_name());
            permission.setCreate_user_id(sysRoleVo.getRole().getCreate_user_id());
            permission.setPermission_value(permission.getPermission_value().replace(",", ""));
            num += roleDao.insertRolePermission(permission);
        }
        if (num == 0) {
            throw new BusinessException("权限值不能为空");
        }
    }

    /*查询Role*/
    @Override
    public void selectRole(BaseModel baseModel) throws Exception {
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page()
                , baseModel.getQueryParams().getPage_size());
        List<SysRole> list = roleDao.selectRole();
        PageInfo page = new PageInfo(list);
        baseModel.setData(page);
        baseModel.setMessage("查询成功");
    }

    @Override
    public void selectRoleAndName(BaseModel baseModel)throws Exception{
        baseModel.setData(roleDao.selectRole());
    }
    @Override
    public void selectRoleById(int id, BaseModel baseModel) throws Exception {
        baseModel.setData(roleDao.selectRoleVoById(id));
        baseModel.setMessage("根据ID查询角色信息成功");
    }

    @Override
    public void removeRoleAndPermission(String id, BaseModel baseModel) throws Exception {
        int remove = roleDao.removeRole(id);
        if (remove == 0)
            throw new BusinessException("角色信息不存在");
        int removeP = roleDao.removeRolePermission(id);
        if (removeP == 0)
            throw new BusinessException("角色权限为空");
        baseModel.setMessage("删除成功");
    }

    @Override
    public void updataRoleById(int id, SysRoleVo sysRoleVo) throws Exception {

        roleDao.deleteRolePermissionById(id);
        SysRole oldRold = roleDao.selectRoleByName(sysRoleVo.getRole().getName());
        if (oldRold != null) {
            if (oldRold.getRole_id() != id)
                throw new BusinessException("角色名称已存在，请勿重复添加");
        }
        sysRoleVo.getRole().setRole_id(id);
        roleDao.updataRoleById(sysRoleVo.getRole());
        int count = 0;
        for (SysRolePermission permission : sysRoleVo.getListRolePermissions()) {
            if (permission.getPermission_value() == null || !permission.getPermission_value().contains("1")) {
                continue;
            }
            permission.setFk_role_id(sysRoleVo.getRole().getRole_id());
            permission.setCreate_user_name(sysRoleVo.getRole().getCreate_user_name());
            permission.setCreate_user_id(sysRoleVo.getRole().getCreate_user_id());
            permission.setPermission_value(permission.getPermission_value().replace(",", ""));
            count += roleDao.insertRolePermission(permission);
        }
        if (count == 0) {
            throw new BusinessException("权限值不能为空");
        }
    }


    @Override
    public void selectRoleLikeName(String name, BaseModel baseModel) throws Exception {
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page()
                , baseModel.getQueryParams().getPage_size());
        List<SysRole> list = roleDao.selectRoleLikeName(name);
        PageInfo page = new PageInfo(list);
        baseModel.setData(page);
        baseModel.setMessage("查询成功");
    }

    @Override
    public void testSearch(BaseModel baseModel) throws Exception {
        List<AdvancedQuery> list = baseModel.getListAdvancedQuery();
        String where = "";
        for (AdvancedQuery query : list) {
            if(query.getFieldValue()==null||query.getFieldValue().equals("")){
                continue;
            }
            if (query.getLogicalOperator() == null || "".equals(query.getLogicalOperator())) {
                query.setLogicalOperator("and");
            }
            if (query.getRelationOperator() == null || "".equals(query.getRelationOperator())) {
                query.setRelationOperator("like");
            }
            where += " " + query.getLogicalOperator() + " " + query.getFieldName() + " " + query.getRelationOperator()
                    + " '%" + query.getFieldValue() + "%'";
        }
        baseModel.getQueryParams().setWhere(where);
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page()
                , baseModel.getQueryParams().getPage_size());
        List<SysRole> roleList = roleDao.selectRoleByLikeName(baseModel.getQueryParams());
        PageInfo page = new PageInfo(roleList);
        baseModel.setData(page);
        baseModel.setMessage("查询成功");

    }

}
