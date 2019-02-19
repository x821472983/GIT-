package com.dc.ssm.service.Impl;

import com.base.constants.ConstantsBase;
import com.base.pojo.BaseModel;
import com.dc.ssm.dao.FileDao;
import com.dc.ssm.po.SysFileLog;
import com.dc.ssm.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileDao fileDao;

    @Override
    public void insertFileLog(String uploader, BaseModel baseModel) throws Exception {
        String filedIds = "";
        MultipartFile[] tempMFiles = baseModel.getTempMFile();
        if (tempMFiles != null) {
            int file_id=0;
            for (MultipartFile file : tempMFiles) {
                if (file.isEmpty()) {
                    continue;
                }
                SysFileLog fileLog = new SysFileLog();
                String fileName = file.getOriginalFilename(); // 获取原始名称
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                Date now = new Date();
                String day = format.format(now);
                fileLog.setFile_name(fileName); // 原来的文件名
                fileLog.setFile_rename(UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."))); // 重新生成的唯一标识文件名
                fileLog.setUpload_time(now); // 时间
                fileLog.setUploader(uploader); // 上传人
                fileLog.setFile_size(file.getSize());
                fileLog.setFile_type(file.getContentType());
                fileLog.setSave_path(day.substring(0, 4) + File.separator + day.substring(0, 6) +
                        File.separator + day + File.separator + fileLog.getFile_rename());
                String path = System.getProperty(ConstantsBase.WEBAPP_ROOT) + "files" +
                        File.separator + "upload" + File.separator + fileLog.getSave_path();
                File f = new File(path);
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                }
                FileOutputStream outputStream = new FileOutputStream(path);
                outputStream.write(file.getBytes());
                outputStream.flush();
                outputStream.close();
                fileDao.insertFileLog(fileLog);

                if (filedIds.length() > 0) {
                    filedIds += "," + fileLog.getFile_log_id();
                } else {
                    filedIds = fileLog.getFile_log_id();
                }
                baseModel.setMessage(fileLog.getFile_log_id()+"");
            }

            baseModel.setFilesArray(filedIds);
        }

    }

    @Override
    public void selectFileLogById(int user_id, BaseModel baseModel) throws Exception {
        SysFileLog fileLog=fileDao.selectFileLogById(user_id);
        if(fileLog.getSave_path()!=null){
            baseModel.setFilesArray(fileLog.getSave_path());
        }
    }

    @Override
    public void deleteFileLogById(int Head_img_url, BaseModel baseModel) throws Exception {
        fileDao.deleteFileLogById(Head_img_url);
    }


}
