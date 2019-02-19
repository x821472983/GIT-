package com.dc.ssm.service.Impl;

import com.base.constants.GloblaVarible;
import com.base.em.EnumError;
import com.base.pojo.BaseModel;
import com.base.pojo.BusinessException;
import com.dc.ssm.dao.LoginLogDao;
import com.dc.ssm.dao.SysRoleDao;
import com.dc.ssm.dao.SysUserDao;
import com.dc.ssm.po.SysLoginLog;
import com.dc.ssm.po.SysMenu;
import com.dc.ssm.po.SysRolePermission;
import com.dc.ssm.po.SysUser;
import com.dc.ssm.service.LoginService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysLoginLogImpl implements LoginService {
    @Autowired
    SysUserDao userDao;
    @Autowired
    LoginLogDao loginLogDao;
    @Autowired
    SysRoleDao sysRoleDao;


    @Override
    public void LoginDaily(SysLoginLog sysLogin, BaseModel baseModel) throws Exception {
        sysLogin.setLogin_date(new Date());
        String account = sysLogin.getUser_account();
        String password = sysLogin.getUser_password();
        SysUser userInfo = userDao.selectUserByAccount(account);
        if (userInfo == null) {
            sysLogin.setState(2);
            loginLogDao.insertLoginLog(sysLogin);
            throw new BusinessException(EnumError.LOGIN_NOt_EXIST_ACCOUNT);//用户不存在
        } else {
            if (!userInfo.getPassword().equals(password)) {
                sysLogin.setState(0);
                loginLogDao.insertLoginLog(sysLogin);
                throw new BusinessException(EnumError.LOGIN_WRONG_PASSWORD);//密码错误
            } else if (userInfo.getState() != 1) {
                sysLogin.setState(3);
                loginLogDao.insertLoginLog(sysLogin);
                throw new BusinessException(EnumError.LOGIN_INACTIVE);//账号未激活
            } else {
                baseModel.setMessage("登陆成功");
                baseModel.setResultCode(0);
                baseModel.setData(userInfo);
                sysLogin.setUser_id(userInfo.getUser_id());
                sysLogin.setUser_name(userInfo.getName());
                sysLogin.setUser_password(null);
                sysLogin.setState(1);
            }
        }
        //插入登录日志
        int count = loginLogDao.insertLoginLog(sysLogin);
        //添加登录日志失败
        if (count == 0) {
            baseModel.setResultCode(1);//登录失败
            baseModel.setMessage("添加日志失败");
        }
        if(GloblaVarible.mapCodeMenu==null){
            synchronized (GloblaVarible.class){
                if(GloblaVarible.mapCodeMenu==null){
                    selectAllMenu();
                }
            }
        }
    }

    @Override
    public void selectLoginLog(BaseModel baseModel) throws Exception {
        PageHelper.startPage(baseModel.getQueryParams().getCurr_page()
                , baseModel.getQueryParams().getPage_size());
        List<SysLoginLog> logList = loginLogDao.selectLoginLog();
        PageInfo page = new PageInfo(logList);
        baseModel.setData(page);
    }

    @Override
    public void selectMenuByUserId(int user_id, BaseModel baseModel) throws Exception {
        List<SysMenu> listTwoMenu=sysRoleDao.selectMenuByUserId(user_id);
        List<Integer> listId=new ArrayList<>();
        for(SysMenu menu:listTwoMenu){
            if(!listId.contains(menu.getFk_parent_id())){
                listId.add(menu.getFk_parent_id());
            }
        }
        String ids="";
        for(Integer id:listId){
            if(ids.length()==0){
                ids=id+"";
            }else{
                ids+=","+id;
            }
        }
        List<SysMenu> listOne =sysRoleDao.selectMenuByMenuId(ids);
        Map<String,Object>map=new HashMap<>();
        map.put("menu_1",listOne);
        map.put("menu_2",listTwoMenu);
        baseModel.setData(map);
    }

    @Override
    public Map<String, String> selectRolePermissionByUserId(int user_id) throws Exception {
        Map<String,String> map=new HashMap<>();
        List<SysRolePermission> list =sysRoleDao.selectRolePermissionByUserId(user_id);
        for(SysRolePermission item:list){
            String exitValue=map.get(item.getCode());
            if(exitValue==null){
                map.put(item.getCode(),item.getPermission_value());
            }else {
                StringBuilder value=new StringBuilder(exitValue);
                StringBuilder value2=new StringBuilder(item.getPermission_value());
                for(int i=0;i<value.length();++i){
                    if(value.charAt(i)!='1'&&value2.charAt(i)=='1'){
                        value.replace(i,i+1,"1");
                    }
                }
                if(value2.length()>value.length()){
                    value.append(value2.substring(value.length()));
                }
                map.put(item.getCode(),value.toString());
            }

        }

        return map;
    }

    @Override
    public void selectAllMenu() throws Exception {
        List<SysMenu> list=loginLogDao.selectAllMenu();
        GloblaVarible.mapCodeMenu=new HashMap<>();
        GloblaVarible.mapIdCode=new HashMap<>();
        for(SysMenu menu :list){
            GloblaVarible.mapCodeMenu.put(menu.getCode(),menu);
            GloblaVarible.mapIdCode.put(menu.getMenu_id(),menu.getCode());
        }
    }

}
