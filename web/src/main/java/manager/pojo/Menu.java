package manager.pojo;

/**
 * 菜单信息
 */
public class Menu {
    private int menuId;//菜单id
    private  String menuNumber;//权限菜单编号
    private  String menuName;//菜单名称
    private int isSubMenu;//是否是子菜单
    private int heigherMenu;//上级菜单id
    private int isShow;//是否显示
    private int isPhoneSupport;//是否手机需要
    private String menuUrl;//菜单链接url
    private String icon;//图标
    private String dom;//前端权限控制按钮dom
    private int optimisticId;//乐观锁编号

}
