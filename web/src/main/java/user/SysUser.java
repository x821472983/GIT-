package user;

import com.wordnik.swagger.annotations.ApiModelProperty;

/*用户数据登录集合*/
public class SysUser {

    @ApiModelProperty(value = "用户id")
    private int user_id;
    @ApiModelProperty(value = "用户名")
    private String account;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "头像")
    private String head_img_url;




    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getHead_img_url() {
        return head_img_url;
    }

    public void setHead_img_url(String head_img_url) {
        this.head_img_url = head_img_url;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "user_id=" + user_id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", head_img_url='" + head_img_url + '\'' +
                '}';
    }
}
