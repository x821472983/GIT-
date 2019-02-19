package com.base.em;

public enum  EnumError {

    //系统相关
    SYS_ERROR(1001, "系统异常,请联系管理员"),
    //登录相关
    LOGIN_NULL_USER(1020, "账号或密码不能为空"),
    LOGIN_WRONG_USER(1009, "账号或密码错误"),
    LOGIN_NOt_EXIST_ACCOUNT(1021, "账号不存在"),
    LOGIN_INACTIVE(1010, "账号未激活"),
    LOGIN_WRONG_PASSWORD(1012, "密码错误或加密异常"),
    //表单提交相关
    RESUBMIT(1016, "请勿重复提交请求"),
    UPDATE_VES(1017, "更新失败,操作记录已被更新"),
    UPDATE_NULL(1018, "更新失败,字段不允许为空"),
    UPDATE_PK(1019, "主键未标记"),
    NO_PERMISSIONS(1002, "对不起,您无权限进行此操作"),
    NO_LOGIN(999,"没有登录"),
    QUERY_ERROR(1030, "查询请求错误"),
    QUERY_ERROR_TIME(1031, "查询时间格式错误");

    private int code;
    private String message;

    private EnumError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
