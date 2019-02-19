package com.base.controller;

import com.base.constants.ConstantsBase;
import com.base.em.EnumError;
import com.base.pojo.BaseModel;
import com.base.pojo.BusinessException;
import com.dc.ssm.po.SysUser;
import com.dc.ssm.service.OperationLogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@ResponseBody//所有接口加上ResponseBody
public class BaseController {

    @Autowired
    OperationLogService operationLogService;

    private static Logger logger = Logger.getLogger(BaseModel.class);
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request,
                             HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    @InitBinder("baseModel")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("baseModel.");
    }


    @ExceptionHandler(Exception.class)
    public BusinessException handerException(Exception ex) {
        BusinessException bsex = new BusinessException();
        if (ex instanceof BusinessException) {
            bsex = (BusinessException) ex;
            if (bsex.getCode() == 0) {
                bsex.setCode(1000);
            }
            logger.error("[业务异常]" + ex.getStackTrace());
        } else {
            bsex.setEnumError(EnumError.SYS_ERROR);
            bsex.setStackTrace(ex.getStackTrace());
            logger.error("[" + bsex.getMessage() + "]" + ex.getStackTrace(), ex);
        }
        logger.error(ex.getMessage());
        response.setStatus(bsex.getCode());
        return bsex;
    }

    protected SysUser getSessionUser() throws Exception {
        SysUser sysUser = (SysUser) session.getAttribute(ConstantsBase.SESSION_USER);
        if (sysUser == null) {
            throw new BusinessException(EnumError.NO_LOGIN);
        }
        return sysUser;
    }


}


