package com.base.pojo;

import com.base.em.EnumError;
import com.base.controller.BaseController;
import com.dc.ssm.po.SysOperationLog;
import com.dc.ssm.service.OperationLogService;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


public class BusinessException extends Exception{
private int code;
private String message;

public  BusinessException(){
}

public BusinessException(String message){this.message=message;}

public BusinessException(int code,String message){
    this.message=message;
    this.code=code;
}

public BusinessException(EnumError emError){
    this.message=emError.getMessage();
    this.code=emError.getCode();
}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEnumError(EnumError enumError){
    message=enumError.getMessage();
    code=enumError.getCode();
    }
    @Override
    public String toString() {
        return "BusinessException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    @Controller
    @RequestMapping("/Operation")
    public static class OperationLogController extends BaseController {
        private static Logger logger=Logger.getLogger(BaseModel.class);

        @Autowired
        OperationLogService operationLogService;


        @ApiOperation(value = "用户操作日志查询",notes="用户操作日志查询",httpMethod = "POST")
        @ApiImplicitParams({
                @ApiImplicitParam(name="baseModel.queryParams.curr_page",
                        value = "当前页",paramType = "query",dataType = "int"),
                @ApiImplicitParam(name="baseModel.queryParams.page_size",
                        value = "每页显示的记录数",paramType = "query",dataType = "int")
        })
        @RequestMapping("/selectOperationLog")
        public BaseModel selectLoginLog(BaseModel baseModel)throws Exception{
            getSessionUser();
            operationLogService.selectOperationLog(baseModel);
            if(baseModel.getData()==null||baseModel.getData().equals("")){
                throw new BusinessException("查询操作日志错误");
            }else{
                baseModel.setMessage("查询成功");
            }
            return baseModel;
        }

        @RequestMapping("/insertOperationLog")
        public void insertOperationLog(SysOperationLog sysOperationLog, BaseModel baseModel)throws Exception{
            operationLogService.insertOperationLog(sysOperationLog,baseModel);
        }
    }
}