package com.dc.ssm.com.dc.base.aop;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class DemoAspect {
    Logger log = Logger.getLogger(DemoAspect.class);
    @Pointcut("execution(* com.dc.ssm.controller..*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void doBeforeController(JoinPoint joinPoint){
        log.error("前置通知【方法执行前执行】："+joinPoint.getTarget().getClass().getName()
                +"."+joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "pointCut()")
    public void afterReturn(JoinPoint joinPoint){
        log.error("后置通知【方法执行完成，返回前触发】："+joinPoint.getTarget().getClass().getName()
                +"."+joinPoint.getSignature().getName());

    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint){
        log.error("最终通知【方法执行完成后执行】："+joinPoint.getTarget().getClass()+"."
                +joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "error")
    public void afterThrowing(JoinPoint joinPoint, Throwable error) {
        log.error("异常通知[方法执行出错触发]" +
                joinPoint.getTarget().getClass().getName() +
                "." + joinPoint.getSignature().getName() + "\n" + error);
    }
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp)throws Throwable{
        log.error("环绕通知【包含前置、后置、最终】："+pjp.getTarget().getClass().getName()+"."
        +pjp.getSignature().getName());
        Object result=null;
        log.error("AOP Around Before...");
        try{
            result=pjp.proceed();
            log.error("AOP Around afterReturing...");
        }catch(Throwable e){
            log.error("AOP Around afterThrowing...");
            throw e;
        }finally {
            log.error("AOP Around after...");
        }
        return  result;
    }
}
