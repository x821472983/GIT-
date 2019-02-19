package manager.pojo;

import java.util.Date;

/**
 * 用户信息
 */
public class User {
    private int userId;//用户id
    private String userAccount;//用户账号
    private String password;//密码
    private String userName;//用户姓名
    private String headImgUrl;//头像地址
    private int userState;//用户状态
    private String userPlace;//籍贯
    private String email;//邮箱
    private Date birthday;//生日
    private int creatorId;//创建人id
    private String creatorName;//创建人
    private Date createTime;//创建时间
    private int modifierId;//修改人id
    private String modifierName;//修改人
    private Date modifyTime;//修改时间
    private int isDelete;//是否删除
    private int optimisticId;//乐观锁编号

}
