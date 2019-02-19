package manager.pojo;

import java.util.Date;

/**
 * 文件记录
 */
public class FileRecord {
    private int fileId;//文件id
    private String oldFileName;//原始文件名
    private String newFileName;//重命名后文件名
    private String filePath;//保存路径
    private String fileSize;//文件大小
    private String fileType;//文件类型
    private int creatorId;//创建人id
    private String creatorName;//创建人
    private Date createTime;//创建时间
}
