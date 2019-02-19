package com.base.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class BaseModel {
    @ApiModelProperty(value = "存放消息状态值，0表示正常，非0表示异常")
    int resultCode;// 正确消息是0；错误是1,未登录9，登录后无权限8
    @ApiModelProperty(value = "存放返回的响应消息")
    String message = "";// 存放返回的消息
    @ApiModelProperty(value = "存放返回的结果集")
    Object data;// 存放查询结果集
    @ApiModelProperty(value = "存放上传的文件")
    MultipartFile[] tempMFile;// 用于文件导入时存放文件
    @ApiModelProperty(value = "用于返回上传文件信息")
    String filesArray;
    @ApiModelProperty(value = "token")
    String token;
    @ApiModelProperty(value = "用于放回文件数量")
    int filesLength;//存放文件数量
    @ApiModelProperty(value="查询参数类")
    private com.base.pojo.QueryParams queryParams;
    @ApiModelProperty(value="")
    private List<AdvancedQuery> listAdvancedQuery;
    @ApiModelProperty(value = "权限值")
    private String Permission;
    @ApiModelProperty(value = "切面消息")
    private String AopMessage;
    @ApiModelProperty(value = "权限值按钮")
    private String PermissionButtons;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public MultipartFile[] getTempMFile() {
        return tempMFile;
    }

    public void setTempMFile(MultipartFile[] tempMFile) {
        this.tempMFile = tempMFile;
    }

    public String getFilesArray() {
        return filesArray;
    }

    public void setFilesArray(String filesArray) {
        this.filesArray = filesArray;
    }

    public int getFilesLength() {
        return filesLength;
    }

    public void setFilesLength(int filesLength) {
        this.filesLength = filesLength;
    }

    public com.base.pojo.QueryParams getQueryParams() {
        return queryParams;
    }


    public List<AdvancedQuery> getListAdvancedQuery() {
        return listAdvancedQuery;
    }

    public void setListAdvancedQuery(List<AdvancedQuery> listAdvancedQuery) {
        this.listAdvancedQuery = listAdvancedQuery;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPermission() {
        return Permission;
    }

    public void setPermission(String permission) {
        Permission = permission;
    }

    public String getAopMessage() {
        return AopMessage;
    }

    public void setAopMessage(String aopMessage) {
        AopMessage = aopMessage;
    }

    public String getPermissionButtons() {
        return PermissionButtons;
    }

    public void setPermissionButtons(String permissionButtons) {
        PermissionButtons = permissionButtons;
    }

    public void setQueryParams(com.base.pojo.QueryParams queryParams) {
        this.queryParams = queryParams;



    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "resultCode=" + resultCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", tempMFile=" + (tempMFile == null ? null : Arrays.asList(tempMFile)) +
                ", filesArray='" + filesArray + '\'' +
                ", token='" + token + '\'' +
                ", filesLength=" + filesLength +
                ", queryParams=" + queryParams +
                ", listAdvancedQuery=" + listAdvancedQuery +
                ", Permission='" + Permission + '\'' +
                ", AopMessage='" + AopMessage + '\'' +
                ", PermissionButtons='" + PermissionButtons + '\'' +
                '}';
    }
}
