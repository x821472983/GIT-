package user;

import com.wordnik.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/*返回结果集合*/
public class BaseModel {
    @ApiModelProperty(value = "返回编号值正确消息是0;未登录9，无权限8，重复提交7:负的值都是有数据有问题")
    private int code = 0;// 正确消息是0;未登录9，无权限8，重复提交7。
    @ApiModelProperty(value = "存放返回的消息")
    private String message = "";// 存放返回的消息
    @ApiModelProperty(value = "存放查询结果集")
    private Object data = null;// 存放查询结果集
    @ApiModelProperty(value = "防表单重复提交token")
    private String token = "";// 防表单重复提交token
    @ApiModelProperty(value = "切面消息")
    private String aopMsg = "";// 切面消息
    @ApiModelProperty(value = "用于多条删除时存放ids")
    private String ids = "";// 用于多条删除时存放ids
    @ApiModelProperty(value = "页码")
    private int page;// 页码
    @ApiModelProperty(value = "行数")
    private int rows;// 行数
    @ApiModelProperty(value = "用于文件导入时存放文件")
    private MultipartFile[] tempMFile = null;// 用于文件导入时存放文件
    private String fileNameList = "";// 用于存放文件上传的路径
    @ApiModelProperty(value = "用于存放权限值")
    private String roleValue = "";// 用于存放权限值
    @ApiModelProperty(value = "用于存放查询权限，1查全，2查单位，3查部门，4查私，0无权限;")
    private String searchRole = "";// 用于存放查询权限，1查全，2查单位，3查部门，4查私，0无权限
    @ApiModelProperty(value = "切面模块数组序列")
    private int moduleNum;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAopMsg() {
        return aopMsg;
    }

    public void setAopMsg(String aopMsg) {
        this.aopMsg = aopMsg;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public MultipartFile[] getTempMFile() {
        return tempMFile;
    }

    public void setTempMFile(MultipartFile[] tempMFile) {
        this.tempMFile = tempMFile;
    }

    public String getFileNameList() {
        return fileNameList;
    }

    public void setFileNameList(String fileNameList) {
        this.fileNameList = fileNameList;
    }

    public String getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(String roleValue) {
        this.roleValue = roleValue;
    }

    public String getSearchRole() {
        return searchRole;
    }

    public void setSearchRole(String searchRole) {
        this.searchRole = searchRole;
    }

    public int getModuleNum() {
        return moduleNum;
    }

    public void setModuleNum(int moduleNum) {
        this.moduleNum = moduleNum;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", token='" + token + '\'' +
                ", aopMsg='" + aopMsg + '\'' +
                ", ids='" + ids + '\'' +
                ", page=" + page +
                ", rows=" + rows +
                ", tempMFile=" + (tempMFile == null ? null : Arrays.asList(tempMFile)) +
                ", fileNameList='" + fileNameList + '\'' +
                ", roleValue='" + roleValue + '\'' +
                ", searchRole='" + searchRole + '\'' +
                ", moduleNum=" + moduleNum +
                '}';
    }
}
