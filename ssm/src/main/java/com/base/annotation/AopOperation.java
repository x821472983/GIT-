package com.base.annotation;

import com.base.em.EnumRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 注解用于方法
@Retention(RetentionPolicy.RUNTIME) // 可以反射获取注解参数
public @interface AopOperation {
    /**
     * 操作描述
     *
     * @return
     */
    String desc() default "";

    /**
     * 记录操作类型(新增,修改,删除)
     *
     * @return
     */
    String type();

    /**
     * 操作的模块(用户管理,角色管理)
     *
     * @return
     */
    EnumRole module();

    /**
     * 是否记录操作日志
     *
     * @return
     */
    boolean saveLog() default true;

    /**
     * 是否进行登录拦截
     * @return
     */
    boolean interceptLogin() default true;

    /**
     * 是否进行权限拦截
     * @return
     */
    boolean interceptPermission() default true;
}
