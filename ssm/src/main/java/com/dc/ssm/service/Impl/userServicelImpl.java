package com.dc.ssm.service.Impl;

import com.base.pojo.BaseModel;
import com.dc.ssm.dao.userDao;
import com.dc.ssm.po.report;
import com.dc.ssm.po.user_user;
import com.dc.ssm.service.userService;
import com.dc.ssm.vo.ReportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServicelImpl implements userService {

    @Autowired
    userDao userDao;

    @Override
    public void selectuser(int user_id, BaseModel baseModel) throws Exception {
        baseModel.setData(userDao.selectuser(user_id));
        baseModel.setMessage("查询成功");
    }

    @Override
    public void reportSend(ReportVo reportVo, BaseModel baseModel,user_user user)throws Exception{
        report report=reportVo.getReport();
        userDao.insertReport(report);
        int report_id=report.getReport_id();
        for (user_user u : reportVo.getUser()) {
            userDao.insertUserReport(u.getUser_id(),report_id);
        }
        userDao.insertUserReport(user.getUser_id(),report_id);
        baseModel.setMessage("发送成功");
    }

    @Override
    public void notRead(int user_id, BaseModel baseModel) throws Exception {
        baseModel.setData(userDao.notRead(user_id));
    }

    @Override
    public void allReport(int user_id, BaseModel baseModel) throws Exception {
        baseModel.setData(userDao.allReport(user_id));
    }

    @Override
    public void selectReport(int report_id, BaseModel baseModel) throws Exception {
        baseModel.setData(userDao.selectReport(report_id));
    }


}
