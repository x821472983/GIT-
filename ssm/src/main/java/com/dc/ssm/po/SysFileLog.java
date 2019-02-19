package com.dc.ssm.po;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class SysFileLog {
    @ApiModelProperty(value = "主键Id")
    String file_log_id;
    @ApiModelProperty(value = "原始文件名")
    String file_name;
    @ApiModelProperty(value = "重命名的文件名")
    String file_rename;
    @ApiModelProperty(value = "文件类型，不包含点")
    String file_type;
    @ApiModelProperty(value = "文件保存地址")
    String save_path;
    @ApiModelProperty(value = "文件大小")
    long file_size;
    @ApiModelProperty(value = "文件长度，针对doc、pdf、xls文档的文件页码数，针对视频文件的时长")
    int file_length;
    @ApiModelProperty(value = "转化状态，0不转化，1待转化，2转化中，3转化完成，4转化异常")
    int state;
    @ApiModelProperty(value = "消息")
    String message;
    @ApiModelProperty(value = "上传时间")
    Date upload_time;
    @ApiModelProperty(value = "上传者")
    String uploader;
    @ApiModelProperty(value = "是否删除")
    int isdeleted;

    public String getFile_log_id() {
        return file_log_id;
    }

    public void setFile_log_id(String file_log_id) {
        this.file_log_id = file_log_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_rename() {
        return file_rename;
    }

    public void setFile_rename(String file_rename) {
        this.file_rename = file_rename;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getSave_path() {
        return save_path;
    }

    public void setSave_path(String save_path) {
        this.save_path = save_path;
    }

    public long getFile_size() {
        return file_size;
    }

    public void setFile_size(long file_size) {
        this.file_size = file_size;
    }

    public int getFile_length() {
        return file_length;
    }

    public void setFile_length(int file_length) {
        this.file_length = file_length;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Date upload_time) {
        this.upload_time = upload_time;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public int getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(int isdeleted) {
        this.isdeleted = isdeleted;
    }

    @Override
    public String toString() {
        return "SysFileLog{" +
                "file_log_id='" + file_log_id + '\'' +
                ", file_name='" + file_name + '\'' +
                ", file_rename='" + file_rename + '\'' +
                ", file_type='" + file_type + '\'' +
                ", file_path='" + save_path + '\'' +
                ", file_size=" + file_size +
                ", file_length='" + file_length + '\'' +
                ", state=" + state +
                ", message='" + message + '\'' +
                ", uplooad_time=" + upload_time +
                ", uploader='" + uploader + '\'' +
                ", isdeleted=" + isdeleted +
                '}';
    }
}
