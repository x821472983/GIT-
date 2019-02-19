package com.dc.ssm.service;

import com.base.pojo.BaseModel;

public interface FileService {
    void insertFileLog(String uploader, BaseModel baseModel) throws Exception;

    void selectFileLogById(int user_id, BaseModel baseModel) throws Exception;

    void deleteFileLogById(int Head_img_url, BaseModel baseModel) throws Exception;
}
