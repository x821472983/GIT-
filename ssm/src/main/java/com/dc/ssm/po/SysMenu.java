package com.dc.ssm.po;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class SysMenu {
    @ApiModelProperty(value = "菜单id")
    private int menu_id;
    @ApiModelProperty(value = " 编号")
    private String code;
    @ApiModelProperty(value = " 名字")
    private String name;
    @ApiModelProperty(value = "0不是叶子，1是叶子")
    private int is_leaf;
    @ApiModelProperty(value = "父节点id")
    private int fk_parent_id;
    @ApiModelProperty(value = "0不显示1显示")
    private int is_show;
    @ApiModelProperty(value = "0不是1是")
    private int is_phone;
    @ApiModelProperty(value = " 地址")
    private String url;
    @ApiModelProperty(value = " 图标")
    private String icon;
    @ApiModelProperty(value = " 前端按钮id")
    private String ids;
    @ApiModelProperty(value = "版本号")
    private int version ;

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIs_leaf() {
        return is_leaf;
    }

    public void setIs_leaf(int is_leaf) {
        this.is_leaf = is_leaf;
    }

    public int getFk_parent_id() {
        return fk_parent_id;
    }

    public void setFk_parent_id(int fk_parent_id) {
        this.fk_parent_id = fk_parent_id;
    }

    public int getIs_show() {
        return is_show;
    }

    public void setIs_show(int is_show) {
        this.is_show = is_show;
    }

    public int getIs_phone() {
        return is_phone;
    }

    public void setIs_phone(int is_phone) {
        this.is_phone = is_phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
                "menu_id=" + menu_id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", is_leaf=" + is_leaf +
                ", fk_parent_id=" + fk_parent_id +
                ", is_show=" + is_show +
                ", is_phone=" + is_phone +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", ids='" + ids + '\'' +
                ", version=" + version +
                '}';
    }
}
