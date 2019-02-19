package com.dc.ssm.controller;

import com.base.controller.BaseController;
import com.base.pojo.BaseModel;
import com.dc.ssm.po.SysUser;
import com.dc.ssm.service.FileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
    private static Logger logger=Logger.getLogger(BaseModel.class);
    @Autowired
    FileService fileService;


    @RequestMapping("/upload")
    public BaseModel insertFileLog(String uploader,BaseModel baseModel)throws Exception{
        SysUser sysUser=getSessionUser();

        if(baseModel.getFilesArray()==null){
            baseModel.setMessage("上传失败");
        }else {
            baseModel.setMessage("上传成功");
        }
        return baseModel;
    }
}
