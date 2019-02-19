package manager.pojo;

import java.util.Date;

/**
 * 登录日志
 */
public class LoginLog {
    private int loginLogId;//登录日志id
    private int userId;//用户id
    private String userAccount;//用户名账号
    private String password;//密码
    private String userName;//用户姓名
    private Date loginTime;//登入时间
    private Date logoutTime;//登出时间
    private String requestIP;//请求ip
    private int loginState;//登录状态
    private int loginType;//登录类型
    private String phoneIMEI;//手机IMEI
    private String phoneType;//手机型号
    private String version;//版本号
    private int logoutType;//登出类型
    private String sessionId;//会话id
}
