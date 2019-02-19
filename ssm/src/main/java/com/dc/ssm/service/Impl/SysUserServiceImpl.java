package com.dc.ssm.service.Impl;

import com.base.pojo.*;
import com.base.rsa.RSAJS;
import com.dc.ssm.dao.SysRoleDao;
import com.dc.ssm.dao.SysUserDao;
import com.dc.ssm.po.*;
import com.dc.ssm.service.SysUserService;
import com.dc.ssm.vo.SysUserRoleVo;
import com.dc.ssm.vo.SysUserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserDao userDao;
    @Autowired
    SysRoleDao roleDao;

    @Override
    public void selectAllUser(BaseModel baseModel) {
        List<SysUser> list = userDao.selectAllUser();
        baseModel.setData(list);
    }

    public void selectAllUserLength(BaseModel baseModel) throws Exception {
        int length = userDao.selectAllUserLength();
        baseModel.setFilesLength(length);
    }


    public void insertUser(SysUserVo sysUserVo, BaseModel baseModel) throws Exception {
        String UserAccount = sysUserVo.getUser().getAccount();
        if (UserAccount == null || UserAccount.equals("")) {
            throw new BusinessException("账号不能为空");
        }
        SysUser u = userDao.selectUserByAccount(UserAccount);
        if (u != null) {
            throw new BusinessException("用户名已存在，请勿重复添加");
        }
        int count = userDao.insertUser(sysUserVo.getUser());
        if (count > 0) {
            baseModel.setMessage("新增用户成功");
        } else {
            throw new BusinessException("新增用户失败");
        }
        int UserId = sysUserVo.getUser().getUser_id();

        if (sysUserVo.getRoleList() == null) {
            throw new BusinessException("权限角色不能为空");
        }
        for (SysRole sysRole : sysUserVo.getRoleList()) {
            int RoleId = sysRole.getRole_id();
            userDao.insertUserAndRole(UserId, RoleId);
        }


    }

    public void updateUser(SysUserVo sysUserVo, BaseModel baseModel) throws Exception {
        int user_id = sysUserVo.getUser().getUser_id();
        String user_account = sysUserVo.getUser().getAccount();
        SysUser u = userDao.selectUserByAccount(user_account);
        if (u != null) {
            if (u.getUser_id() != user_id)
                throw new BusinessException("用户名已存在");
        }
        int count = userDao.updateUser(sysUserVo.getUser());
        if (count == 0) {
            throw new BusinessException("修改用户失败");
        }
        userDao.deletedUser_Role(user_id);
        for (SysRole sysRole : sysUserVo.getRoleList()) {
            int role_id = sysRole.getRole_id();
            userDao.insertUserAndRole(user_id, role_id);
        }

        baseModel.setData(sysUserVo.getUser());
    }

    public void removeUser(String ids, BaseModel baseModel) throws Exception {
        int count = userDao.removeUser(ids);
        baseModel.setData(count);
        if (count > 0) {
            baseModel.setMessage("逻辑删除用户成功");
        } else {
            baseModel.setMessage("逻辑删除用户失败");
        }
    }

    @Override
    public void selectPasswordByAccount(SysUser sysUser, BaseModel baseModel, HttpSession session) throws Exception {
        if (sysUser.getName() == null || sysUser.getPassword() == null
                || sysUser.getName().equals("") || sysUser.getPassword().equals(""))
            baseModel.setMessage("账号名或密码不能为空");
        String account = sysUser.getAccount();
        String PW = sysUser.getPassword();
        String EPW = RSAJS.sessionDecryptHexStr(session, PW);
        SysUser user = userDao.selectUserByAccount(account);
        if (user == null) {
            baseModel.setMessage("不存在此用户");
        } else if (!user.getPassword().equals(EPW)) {
            baseModel.setMessage("密码错误！");
        } else {
            baseModel.setMessage("登录成功");
            baseModel.setData(user);
            session.setAttribute("user", user);
        }
    }


    @Override
    public void selectUser(BaseModel baseModel) throws Exception {
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page()
                , baseModel.getQueryParams().getPage_size());
        List<SysUserRoleVo> list = userDao.selectUser();
        PageInfo page = new PageInfo(list);
        baseModel.setData(page);
        baseModel.setMessage("查询成功");
        /*List<SysUserVo> list = userDao.selectUser();
        baseModel.setData(list);*/
    }

    @Override
    public void selectPageUser(BaseModel baseModel) throws Exception {
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page()
                , baseModel.getQueryParams().getPage_size());
        List<SysUser> list = userDao.selectAllUser();
        PageInfo page = new PageInfo(list);
        baseModel.setData(page);
    }

    @Override
    public void selectUserById(int id, BaseModel baseModel) throws Exception {
        SysUser user = userDao.selectUserByUserId(id);
        String ids = userDao.selectRoleIdByUserId(id);
        baseModel.setMessage(ids);
        if (user == null) {
            throw new BusinessException("此用户已不存在");
        }
        baseModel.setData(user);
    }

    @Override
    public void selectLoginByUserId(int id, BaseModel baseModel) throws Exception {
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page()
                , baseModel.getQueryParams().getPage_size());
        List<SysLoginLog> list = userDao.selectLoginByUserId(id);
        if (list == null) {
            baseModel.setMessage("暂无登陆记录");
        } else {
            baseModel.setMessage("查询成功");
        }
        PageInfo page = new PageInfo(list);
        baseModel.setData(page);
    }

    @Override
    public void selectRoleName(BaseModel baseModel) throws Exception {
        baseModel.setData(userDao.selectRoleName());
        if (baseModel.getData() == null) {
            baseModel.setMessage("erroe");
        } else {
            baseModel.setMessage("success");
        }
    }

    @Override
    public void deletedUser(String id, BaseModel baseModel) throws Exception {
        int count = userDao.deletedUser(id);
        userDao.deletedUserRole(id);
        if (count == 0) {
            throw new BusinessException("角色已被删除");
        } else {
            baseModel.setMessage("删除成功");
        }
    }

    @Override
    public void selectUserRoleLikeName(BaseModel baseModel) throws Exception {
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page()
                , baseModel.getQueryParams().getPage_size());
        List<AdvancedQuery> list = baseModel.getListAdvancedQuery();
        StringBuilder where = new StringBuilder();
        if (list != null) {
            for (AdvancedQuery query : list) {
                if (query.getFieldValue() == null || query.getFieldValue().equals("")) {
                    continue;
                }
                if (query.getLogicalOperator() == null || query.getLogicalOperator().equals("")) {
                    query.setLogicalOperator("and");
                }
                if (query.getRelationOperator() == null || query.getRelationOperator().equals("")) {
                    query.setRelationOperator("like");
                }
                String blank = " ";
                where.append(blank).append(query.getLogicalOperator())
                        .append(blank).append(query.getFieldName()).append(blank).append(query.getRelationOperator())
                        .append(blank).append("'" + query.getFieldValue() + "%'");
            }
        }
        baseModel.getQueryParams().setWhere(where.toString());
        List<SysUserRoleVo> userList = userDao.selectUserByCondition(baseModel.getQueryParams());
        PageInfo page = new PageInfo(userList);
        baseModel.setData(page);
        baseModel.setMessage("查询成功");
    }

    @Override
    public String exportUserInfo(BaseModel baseModel) throws Exception {
        List<SysUserRoleVo> listVo = userDao.selectUser();
        List<SysUserDTO> list = new ArrayList<>();
        String[] state = {"冻结", "激活",};
        /*String[] gender={"保密","男","女"};*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < listVo.size(); i++) {
            SysUserDTO user = new SysUserDTO();
            BaseUtil.copyObj1ToObj2(listVo.get(i).getUser(), user);
            if(listVo.get(i).getUser().getBirthday()!=null){
                user.setBirthday_str(sdf.format(listVo.get(i).getUser().getBirthday()));
            }
            user.setRole_names(listVo.get(i).getRole_names());
            user.setState_str(state[user.getState()]);
            user.setCount(i + 1);
            list.add(user);
        }

        return BaseUtil.expTemplateExcel(list,"SysUser.xls");
    }

    @Override
    public void importUserInfo(BaseModel baseModel, SysUser sysUser) throws Exception {
        // 读取excel内容，保存在一个map数组中
        List<Map<String, Object>> listMap = ExcelUtil.getListMapWorkBook(baseModel.getTempMFile());
        baseModel.setTempMFile(null);
        Map<String, Integer> mapRole = new HashMap<>();
        String password = "123456"; // 默认密码
        // 生日字符转date类型
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < listMap.size(); i++) {
            SysUserDTO userCustom = new SysUserDTO();
            BaseUtil.mapToEntity(listMap.get(i), userCustom);// 将excel中的内容存放到实体类中
            SysUser user = new SysUser();
            BaseUtil.copyObj1ToObj2(userCustom, user);
            if (user.getAccount() == null || "".equals(user.getAccount())) {
                throw new BusinessException("第" + (i + 1) + "行，帐号不能为空");
            }
            if (user.getName() == null || "".equals(user.getName())) {
                throw new BusinessException("第" + (i + 1) + "行，用户姓名不能为空");
            }

            // 状态[0:冻结,1:激活]
            if (userCustom.getState_str()!=null&&userCustom.getState_str().equals("")) {
                // TODO 只存在两种状态(冻结、激活)，否则抛出异常
                user.setState(userCustom.getState_str().equals("激活") ? 1 : 0);
            }
            // 判断用户名是否存在
            SysUser oldUser = userDao.selectUserByAccount(user.getAccount());
            if (oldUser != null) {
                throw new BusinessException("第" + (i + 1) + "行，用户‘" + oldUser.getAccount() + "’已存在，请勿重复添加");
            }
            // 设置初始化参数
            /*user.setCreate_time(new Date());*/
            user.setCreate_user_name(sysUser.getName());
            user.setCreate_user_id(sysUser.getUser_id());
            user.setPassword(password);
            user.setGender(userCustom.getGender());
            //新增用户
            userDao.insertUser(user);
            //新增用户角色关系
            if (userCustom.getRole_names() != null && !"".equals(userCustom.getRole_names())) {
                String[] roleNames = userCustom.getRole_names().split(",");
                for (int j = 0; j < roleNames.length; j++) {
                    SysUserRole userRole = new SysUserRole();
                    userRole.setFk_user_id(user.getUser_id());
                    if (mapRole.get(roleNames[j]) == null) {//不存在角色id
                        SysRole role = roleDao.selectRoleByName(roleNames[j]);
                        if (role == null) {//角色名称不存在
                            throw new BusinessException("第" + (i + 1) + "行，角色‘" + roleNames[j] + "’不存在");
                        } else {
                            mapRole.put(role.getName(), role.getRole_id());
                            userRole.setFk_role_id(role.getRole_id());
                        }
                    } else {
                        userRole.setFk_role_id(mapRole.get(roleNames[j]));
                    }
                    userDao.insertUserAndRole(userRole.getFk_user_id(),userRole.getFk_role_id());//新增用户角色
                }
            }

        }
        baseModel.setMessage("导入" + listMap.size() + "条用户信息成功");
    }

    @Override
    public void updateState(BaseModel baseModel,int user_id,int state) throws Exception {
        int count=userDao.updateState(user_id,state);
        if(state==1){
            if(count==1){
                baseModel.setMessage("激活成功");
            }else{
                throw new BusinessException("激活失败");
            }
        }
        if(state==0){
            if(count==1){
                baseModel.setMessage("冻结成功");
            }else{
                throw new BusinessException("冻结失败");
            }
        }
    }

}
