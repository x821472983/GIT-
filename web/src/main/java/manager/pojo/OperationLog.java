package manager.pojo;

import java.util.Date;

/**
 * 操作日志
 */
public class OperationLog {
    private int operationId;//操作记录id
    private int operatorId;//操作人id
    private String operatorName;//操作人姓名
    private String operatorAccount;//操作人账号
    private Date operationTime;//操作时间
    private String content;//操作内容
    private String Model;//操作模块
    private String operationIP;//操作ip
    private String paramBefore;//请求前参数
    private String paramAfter;//请求后参数
    private String paramRequest;//请求参数
    private String methodRequest;//请求方法
}
