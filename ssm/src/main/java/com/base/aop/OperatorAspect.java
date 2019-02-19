package com.base.aop;

import com.base.em.EnumError;
import com.base.annotation.AopOperation;
import com.base.constants.ConstantsBase;
import com.base.constants.GloblaVarible;
import com.base.pojo.BaseModel;
import com.base.pojo.BusinessException;
import com.dc.ssm.dao.OperationLogDao;
import com.dc.ssm.po.SysOperationLog;
import com.dc.ssm.po.SysUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
@Aspect
public class OperatorAspect {
    @Autowired
    private OperationLogDao operationLogDao;


    /*private Logger log = Logger.getLogger(Operator.class);*/
    protected HttpServletRequest request = null;
    protected HttpSession session = null;
    private BaseModel baseModel = null;

    @Pointcut("execution(* com.dc.ssm.controller..*.*(..))")
    public void pointCut() {

    }

    /**
     * 定义前置方法，切入点为定义的切点，并且包含自定义注解
     * 在前置方法中获得request对象和session对象
     * AopOperation
     *
     * @param joinPoint
     * @param operation
     */

    /*在XXX运行之前*/
    @Before("pointCut() && @annotation(operation)")
    public void before(JoinPoint joinPoint, AopOperation operation) throws Exception {
        if (joinPoint.getArgs() != null) {
            for (Object object : joinPoint.getArgs()) {
                if (object.getClass() == BaseModel.class) {
                    baseModel = (BaseModel) object;
                    break;
                }
            }
        }

        if (!operation.saveLog()) return;
        request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        session = request.getSession();
        Map<String, String> mapPermission = (Map<String, String>) session.getAttribute(ConstantsBase.SESSION_PERMISSION);
        if (mapPermission == null) {
            throw new BusinessException(EnumError.NO_LOGIN);
        } else {
            String permission = mapPermission.get(operation.module().getCode() + "");
            if (operation.interceptPermission()) {
                if (permission == null) {
                    throw new BusinessException("");
                }

                String[] valueNames = operation.module().getRoleValue().split("，");
                //
                String[] nodes = GloblaVarible.mapCodeMenu.get(operation.module().getCode() + "").getIds().split(";");
                String permission_btns = "";
                for (int i = 0; i < valueNames.length; ++i) {
                    //如果权限名匹配，则权限值相同的位置的字符代表着是否拥有权限
                    if (valueNames[i].equals(operation.type())) {
                        //如果没有该权限则跑出异常
                        if (permission.charAt(i) != '1') {
                            throw new BusinessException(EnumError.NO_PERMISSIONS
                                    .getMessage().replace("{module}", operation.module().getName()));
                        }
                    }
                    //把权限的权限按钮返回给前端
                    if(permission.charAt(i)=='1'&&i<nodes.length){
                        permission_btns+=permission_btns.length()==0?nodes[i]:(","+nodes[i]);
                    }
                }
                baseModel.setPermissionButtons(permission_btns);
                baseModel.setPermission(permission);
            }

        }
    }


    /*在XXX运行之后*/
    @AfterReturning("pointCut()&& @annotation(operation)")
    public void afterReturn(JoinPoint joinPoint, AopOperation operation) throws Exception {
        if (!operation.saveLog()) return;
        if (joinPoint.getArgs() != null) {
            for (Object object : joinPoint.getArgs()) {
                if (object.getClass() == BaseModel.class) {
                    baseModel = (BaseModel) object;
                    break;
                }
            }
        }

        SysUser user = (SysUser) session.getAttribute(ConstantsBase.SESSION_USER);
        SysOperationLog operationLog = new SysOperationLog();
        operationLog.setType(operation.type());
        operationLog.setModule(operation.module().getName());
        operationLog.setRequest_ip(request.getRemoteAddr());
        operationLog.setRequest_method(joinPoint.getTarget().getClass().getName() + "." +
                joinPoint.getSignature().getName() + "()"); // 调用的接口方法
        operationLog.setContent(operation.desc() + ":" + (baseModel == null ? "" : baseModel.getAopMessage()));
        if (user != null) {
            operationLog.setUser_id(user.getUser_id());
            operationLog.setUser_account(user.getAccount());
            operationLog.setUser_name(user.getName());
            operationLog.setModule(codeName(operation.module().getCode()));
            operationLog.setType(operation.type());
            operationLog.setRequest_ip(request.getRemoteAddr());
        }
        operationLogDao.insertOperationLog(operationLog);
    }

    protected String codeName(int code) throws Exception {
        String[] module = {"用户管理", "角色管理", "操作日志", "登录日志"};
        if (code == 204) {
            return module[0];
        } else if (code == 203) {
            return module[1];
        } else if (code == 103) {
            return module[2];
        } else if (code == 102) {
            return module[3];
        } else {
            return "";
        }
    }
}
