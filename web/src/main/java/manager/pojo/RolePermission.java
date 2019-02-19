package manager.pojo;

import java.util.Date;

/**
 * 角色权限
 */
public class RolePermission {
    private int rolePermissionId;//角色权限id
    private int roleId;//角色id
    private String menuNumber;//菜单编号
    private String permissionValue;//权限值
    private int creatorId;//创建人id
    private String creatorName;//创建人
    private Date createTime;//创建时间
    private int modifierId;//修改人id
    private String modifierName;//修改人
    private Date modifyTime;//修改时间
    private int isDelete;//是否删除
    private int optimisticId;//乐观锁编号
}
