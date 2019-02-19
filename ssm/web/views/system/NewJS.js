/*var roleListParams={
    page:{
        add:{
            url:"views/system/role/roleAdd.jsp",
            text:"新增角色信息"
        },

    }
}*/

/*新选项卡页面跳转*/
function tabInnerChange(url, text) {
    parent.$("#nav_temp").attr("name", url);
    parent.$("#nav_temp").text(text);
    parent.$("#nav_temp").click();
}

/*获取前端传送ID*/
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); // 匹配目标参数
    if (r != null)
        return unescape(r[2]);
    return null; // 返回参数值
}

/*角色权限视图加载*/
function code(data) {
    for (var j = 0; j < data.length; ++j) {
        var code = data[j].code;
        var perval = data[j].permission_value;
        if (code == 203) {
            var num = 0;
            for (var i = 0; i < perval.length; ++i) {
                var id = code + "-" + i;
                var val = perval.charAt(i);
                if (val == "1") {
                    $("#" + id).children(".chk_item").prop("checked", true);
                    $("#" + id).children(".layui-form-checkbox").addClass("layui-form-checked")
                    num++;
                } else {
                    $("#" + id).children(".chk_item").prop("checked", false);
                    $("#" + id).children(".layui-form-checkbox").removeClass("layui-form-checked")
                }
            }
            if (num == perval.length) {
                $("#" + code).children("input").prop("checked", true);
                $("#" + code).children("div").addClass("layui-form-checked");
            } else {
                $("#" + code).prop("checked", false);
                $("#" + code).removeClass("layui-form-checked")
            }
        }
        else if (code == 204) {
            var num = 0;
            for (var i = 0; i < perval.length; ++i) {
                var id = code + "-" + i;
                var val = perval.charAt(i);
                if (val == "1") {
                    $("#" + id).children(".chk_item").prop("checked", true);
                    $("#" + id).children(".layui-form-checkbox").addClass("layui-form-checked")
                    num++;
                } else {
                    $("#" + id).children(".chk_item").prop("checked", false);
                    $("#" + id).children(".layui-form-checkbox").removeClass("layui-form-checked")
                }
            }
            if (num == perval.length) {
                $("#" + code).children("input").prop("checked", true);
                $("#" + code).children("div").addClass("layui-form-checked");
            } else {
                $("#" + code).prop("checked", false);
                $("#" + code).removeClass("layui-form-checked")
            }
        }
        else if (code == 103) {
            var num = 0;
            for (var i = 0; i < perval.length; ++i) {
                var id = code + "-" + i;
                var val = perval.charAt(i);
                if (val == "1") {
                    $("#" + id).children(".chk_item").prop("checked", true);
                    $("#" + id).children(".layui-form-checkbox").addClass("layui-form-checked")
                    num++;
                } else {
                    $("#" + id).children(".chk_item").prop("checked", false);
                    $("#" + id).children(".layui-form-checkbox").removeClass("layui-form-checked")
                }
            }
            if (num == perval.length) {
                $("#" + code).children("input").prop("checked", true);
                $("#" + code).children("div").addClass("layui-form-checked");
            } else {
                $("#" + code).prop("checked", false);
                $("#" + code).removeClass("layui-form-checked")
            }
        }
        else if (code == 102) {
            var num = 0;
            for (var i = 0; i < perval.length; ++i) {
                var id = data[j].code + "-" + i;
                var val = perval.charAt(i);
                if (val == "1") {
                    $("#" + id).children(".chk_item").prop("checked", true);
                    $("#" + id).children(".layui-form-checkbox").addClass("layui-form-checked");
                    num++;
                } else {
                    $("#" + id).children(".chk_item").prop("checked", false);
                    $("#" + id).children(".layui-form-checkbox").removeClass("layui-form-checked");
                }
            }
            if (num == perval.length) {
                $("#" + code).children("input").prop("checked", true);
                $("#" + code).children("div").addClass("layui-form-checked");
            } else {
                $("#" + code).prop("checked", false);
                $("#" + code).removeClass("layui-form-checked");
            }
        }
    }

}

/*时间转化函数*/
function formatTime(time) {
    if (time == null) return "";
    var t = new Date(time);
    var y = t.getFullYear();
    var m = t.getMonth() + 1;
    var d = t.getDate();
    var h = t.getHours();
    var mn = t.getMinutes();
    var sec = t.getSeconds();
    return y + "-" + (m > 9 ? m : "0" + m) + "-" + (d > 9 ? d : "0" + d) + " " + (h > 9 ? h : "0" + h)
        + ":" + (mn > 9 ? mn : "0" + mn) + ":" + (sec > 9 ? sec : "0" + sec);

}

/*状态判断，0为成功，1为失败*/
function state(state) {
    var a = "";
    if (state == 0) {
        a = "登录成功";
    } else {
        a = "登录失败";
    }
    return a;
}

/*角色用户初始视图加载*/
function role_view(data) {
    var content = $(".layui-table>tbody");
    content.empty();//移除所有子元素
    for (var i = 0; i < data.length; ++i) {
        var $tr = $("<tr></tr>");
        var roledata = data[i];
        $tr.append("<td><input type='checkbox' name='check' class='choice'></td>");//选择框
        $tr.append("<td class='role_id'>" + roledata.role_id + "</td>");//角色ID
        $tr.append("<td class='NA'>" + roledata.name + "</td>");//角色名称
        $tr.append("<td>" + roledata.name + "</td>");//角色描述
        $tr.append("<td>" + roledata.create_time + "</td>");//创建时间
        $tr.append("<td>" + roledata.create_user_name + "</td>");//创建人
        $tr.append("<td>" + roledata.update_time + "</td>");//修改时间
        $tr.append("<td>" + roledata.update_user_name + "</td>");//修改人
        $tr.append("<td>" + roledata.memo + "</td>");//备注
        $tr.append("" +
            "<td>" +
            "<a class=\"hidden_ps check layui-btn layui-btn-xs layui-btn-normal\"  title='/views/system/role/roleView.jsp?role_id=" + roledata.role_id + "'>查看</a>" +
            "<a class=\"hidden_ps edit layui-btn layui-btn-xs\"  title='/views/system/role/roleUpdate.jsp?role_id=" + roledata.role_id + "'>编辑</a>" +
            "<a class=\"hidden_ps delet layui-btn layui-btn-xs layui-btn-danger\"  title='" + roledata.role_id + "'>删除</a>" +
            "</td>"
        );
        content.append($tr);

    }
    /*查看按钮绑定时间*/
    $(".check").click(function () {
        var url = $(this).attr("title");
        var text = $(this).parent().siblings(".NA").text();
        tabInnerChange(url, text);
    });

    /*删除按钮绑定时间*/
    $(".delet").click(function () {
        var id = $(this).prop("title");
        var text = $(this).parent().siblings(".NA").text();
        deletedRole(id, text);
    })

    /*编辑按钮绑定时间*/
    $(".edit").click(function () {
        var url = $(this).attr("title");
        var text = $(this).parent().siblings(".NA").text();
        tabInnerChange(url, text);
    })
}

/*添加角色用户*/
function add_role_submit() {
    if ($(".chk_item:checked").length == 0) {
        alert("角色权限不能为空");
        return;
    }
    $("#form_submit input.permissionItem").val(0);
    $("#form_submit input.chk_item:checked").siblings(".permissionItem").val(1);
    $.ajax({
        url: "/Role/addRoleVo",
        type: "post",
        data: $("#form_submit").serialize(),
        success: function () {
            alert("增加成功");
            parent.$("iframe[src='/views/system/role/roleList.jsp']").prop("src", '/views/system/role/roleList.jsp');
            parent.layui.element.tabDelete('demo', parent.$(".layui-tab-title .layui-this").attr("lay-id"));
        }, error: function (XMLHttpRequest, textStatus, errorThrow) {
            var jsonError = XMLHttpRequest.responseJSON;
            if (jsonError != null) {
                alert(jsonError.message);
            } else {
                console.log(XMLHttpRequest);
                alert("未知错误");
            }
            parent.$("iframe[src='/views/system/role/roleList.jsp']").prop("src", '/views/system/role/roleList.jsp');
            parent.layui.element.tabDelete('demo', parent.$(".layui-tab-title .layui-this").attr("lay-id"));
        }
    })
}

/*修改角色用户*/
function update_role_submit() {
    $.ajax({
        url: "/Role/updateRolePermission/" + getUrlParam("role_id"),
        type: "get",
        data: $("#form_submit").serialize(),
        success: function () {
            alert("修改成功");
            parent.$("iframe[src='/views/system/role/roleList.jsp']").prop("src", '/views/system/role/roleList.jsp');
            parent.layui.element.tabDelete('demo', parent.$(".layui-tab-title .layui-this").attr("lay-id"));
        }, error: function (XMLHttpRequest, textStatus, errorThrow) {
            var jsonError = XMLHttpRequest.responseJSON;
            if (jsonError != null) {
                alert(jsonError.message);
            } else {
                console.log(XMLHttpRequest);
                alert("未知错误");
            }
            parent.$("iframe[src='/views/system/role/roleList.jsp']").prop("src", '/views/system/role/roleList.jsp');
            parent.layui.element.tabDelete('demo', parent.$(".layui-tab-title .layui-this").attr("lay-id"));
        }
    })
}

/*多个角色用户ID*/
function Role_Ids() {
    var a = 0;
    var ids = "";
    $(".choice").each(function () {
        if ($(this).prop("checked")) {
            a = $(this).parent().siblings(".role_id").text();
            ids += a + ",";
        }
    })
    ids = ids.substring(0, ids.length - 1);
    deletedRole(ids, "批量");
}

/*删除角色用户*/
function deletedRole(id, text) {
    $.ajax({
        url: "/Role/removeRoleAndPermission/" + id,
        dataType: "json",
        success: function (data) {
            alert(text + data.message);
            location.reload();
        }, error: function (XMLHttpRequest, textStatus, errorThrow) {
            var jsonError = XMLHttpRequest.responseJSON;
            if (jsonError != null) {
                alert(jsonError.message);
            } else {
                console.log(XMLHttpRequest);
                alert("未知错误");
            }
        }
    })
}

/*系统用户初始视图加载*/
function user_view(response) {
    var content = $(".layui-table>tbody");//获取tbody
    content.empty();//移除所有子元素
    for (var i = 0; i < response.length; ++i) {
        var data = response[i];
        var userdata = data.user;
        var role_ids = data.role_ids;
        var role_nams = data.role_names;
        var ids = "";
        var names = "";
        if (role_ids != null) {
            ids = role_ids.split(",");
        }
        if (role_nams != null) {
            names = role_nams.split(",");
        }
        var $tr = $("<tr></tr>");
        $tr.append("<td><input type='checkbox' class='subPick' title='" + data.user_id + "'></td>");
        $tr.append("<td class='NA'>" + userdata.account + "</td>");//账号
        $tr.append("<td>" + userdata.name + "</td>");//姓名
        $tr.append("<td>" + userdata.gender + "</td>");//性别
        $tr.append("<td></td>");//手机号码
        var $td = $("<td></td>");
        if (names != null) {
            for (var k = 0; k < names.length; ++k) {
                $td.append("<li>" + names[k] + "</li>")
            }
        }
        $tr.append($td);//所属角色
        $tr.append("<td>" + userdata.address + "</td>");//区域
        $tr.append("<td>" + formatTime(userdata.birthday).substr(0, 10) + "</td>");//出生日期


        //用户启用状态
        var state = userdata.state == 1 ? "checked" : "";
        $tr.append("<td><div class=\"layui-form userState hidden_ps\" data-id='" + userdata.user_id + "'>\n" +
            "<input type=\"checkbox\" lay-skin=\"switch\" lay-text=\"激活|冻结\""
            + state + "></div></td>");


        $tr.append("<td><a class=\"hidden_ps check layui-btn layui-btn-xs layui-btn-normal\" title='/views/system/user/userView.jsp?user_id=" + userdata.user_id + "'>查看</a>\n" +
            "<a class=\"hidden_ps updata layui-btn layui-btn-xs\" title='/views/system/user/userUpdata.jsp?user_id=" + userdata.user_id + "'>编辑</a>\n" +
            "<a class=\"hidden_ps delete layui-btn layui-btn-xs layui-btn-danger\" title='" + userdata.user_id + "'>删除</a></td>");
        content.append($tr);
    }
    /*账号状态按钮绑定*/
    $(".layui-form.userState").click(function () {
        var user_id = $(this).data("id");
        var state;
        if ($(this).children("input").prop("checked")) {
            state = 1
        } else {
            state = 0;
        }
        updateState(user_id, state);
    })

    /*查看按钮绑定时间*/
    $(".check").click(function () {
        var url = $(this).attr("title");
        var text = $(this).parent().siblings(".NA").text();
        tabInnerChange(url, text);
    });

    /*删除按钮绑定时间*/
    $(".delete").click(function () {
        var id = $(this).prop("title");
        var text = $(this).parent().siblings(".NA").text();
        deletedUser(id, text);
    })

    /*编辑按钮绑定时间*/
    $(".updata").click(function () {
        var url = $(this).attr("title");
        var text = $(this).parent().siblings(".NA").text();
        tabInnerChange(url, text);
    })
}

/*添加系统用户*/
function user_add_submit() {
    $("#form_submit").attr("action", "/sysUser/insertUser");
    if (checkuserData()) {
        requestUtilParams.uploaderFile("#form_submit").done(function (res) {
            alert(res.message);
            parent.$("iframe[src='/views/system/user/userList.jsp']").prop("src", '/views/system/user/userList.jsp');
            parent.layui.element.tabDelete('demo', parent.$(".layui-tab-title .layui-this").attr("lay-id"));
        })
    }

}

/*个人用户信息加载*/
function user_id_view() {
    $.ajax({
        url: "/sysUser/selectUserById/" + getUrlParam("user_id"),
        dataType: "json",
        type: "get",
        success: function (response) {
            if (response.filesArray == null || response.filesArray == "") {
                $("#img").attr("src", "/com.base/css/images/accounthead.png");
            } else {
                $("#img").attr("src", "/files/upload/" + response.filesArray);
            }
            var content = $("#user_message").children("tbody");//获取tbody
            var data = response.data;
            //创建表格
            var $tr = $("<tr></tr>");
            $tr.append("<td>" + data.account + "</td>");//账号
            $tr.append("<td>" + data.name + "</td>");//姓名
            $tr.append("<td>" + data.gender + "</td>");//性别
            $tr.append("<td>" + data.birthday + "</td>");//生日
            $tr.append("<td>" + data.address + "</td>");//籍贯
            $tr.append("<td></td>");//专业
            $tr.append("<td>" + data.email + "</td>");//邮箱
            $tr.append("<td></td>");//用户类型
            $tr.append("<td>" + formatTime(data.create_time) + "</td>");//注册时间
            $tr.append("<td></td>");//qq
            content.append($tr);
        }, error: function (XMLHttpRequest, textStatus, errorThrow) {
            var jsonError = XMLHttpRequest.responseJSON;
            if (jsonError != null) {
                alert(jsonError.message);
            } else {
                console.log(XMLHttpRequest);
                alert("未知错误");
            }
        }
    })
}

/*个人用户登录日志*/
function user_loginlog_view(data) {
    var content = $("#loginlog").children("tbody");//获取tbody
    content.empty();//移除所有子元素
    for (var i = 0; i < data.length; ++i) {
        var $tr = $("<tr></tr>");
        var loginData = data[i];
        $tr.append("<td>" + formatTime(loginData.login_date) + "</td>");//登陆时间
        $tr.append("<td>" + loginData.user_account + "</td>");//用户账号
        $tr.append("<td>" + loginData.name + "</td>");//登录IP
        $tr.append("<td>" + state(loginData.state) + "</td>");//登录状态
        $tr.append("<td>" + loginData.type + "</td>");//登录类型
        $tr.append("<td>" + loginData.model_number + "</td>");//手机型号/浏览器型号
        $tr.append("<td>" + loginData.imei + "</td>");//imei
        content.append($tr);
    }
}

/*编辑系统用户视图请求*/
function updateUser() {
    $.ajax({
        url: "/sysUser/selectUserById/" + getUrlParam("user_id"),
        dataType: "json",
        type: "get",
        success: function (response) {
            updateUser_view(response);

        }, error: function (XMLHttpRequest, textStatus, errorThrow) {
            var jsonError = XMLHttpRequest.responseJSON;
            if (jsonError != null) {
                alert(jsonError.message);
            } else {
                console.log(XMLHttpRequest);
                alert("未知错误");
            }
        }
    })
}

/*编辑系统用户视图加载*/
function updateUser_view(res) {
    if (res.filesArray == null || res.filesArray == "") {
        $("#portrait_img_add").attr("src", "/com.base/css/images/accounthead.png");
    } else {
        $("#portrait_img_add").attr("src", "/files/upload/" + res.filesArray);
    }
    var data = res.data;
    $("#head_img_url").val(data.head_img_url);//图片对应ID
    $("#user_account").val(data.account);//账号
    $("#version").val(data.version);
    $("#user_id").val(data.user_id);
    $("#name").val(data.name);
    $("#phonenumber").val();
    var gender = data.gender;
    var sex;
    if (gender == "男") {
        $("#man").attr("checked", true);
        sex = "男"
    } else if (gender == "女") {
        $("#wowen").attr("checked", true);
        sex = "女";
    } else {
        $("#unknow").attr("checked", true);
        sex = "保密";
    }
    $(".layui-form-radio").each(function () {
        if ($(this).children("div").text() == sex) {
            $(this).click();
        }
    })
    var place = data.address;
    $("#place").each(function () {
        var text = $(this).text();
        if (text == place) {
            $(this).prop("selected", true);
        }
    })
    $("#email").val(data.email);
    $("#birthday").val(formatTime(data.birthday).substring(0, 10))
    var list;
    if (res.message == null) {
        list = "";
    } else {
        list = res.message.split(",");
    }
    $(".userItem").each(function () {
        for (var i = 0; i < list.length; ++i) {
            if (list[i] == $(this).val()) {
                $(this).prop("checked", true);
                $(this).siblings(".layui-form-checkbox").addClass("layui-form-checked")
            }
        }
    })
}

/*修改用户信息*/
function user_update_submit() {
    $("#form_submit").attr("action", "/sysUser/userUpdate");
    if (checkuserData()) {
        requestUtilParams.uploaderFile("#form_submit").done(function (res) {
            alert(res.message);
            parent.$("iframe[src='/views/system/user/userList.jsp']").prop("src", '/views/system/user/userList.jsp');
            parent.layui.element.tabDelete('demo', parent.$(".layui-tab-title .layui-this").attr("lay-id"));
        })
    }
}

/*表单提交检查函数*/
function checkuserData() {
    var num = 0;
    var msg = "";
    if ($("#account").val() == "") {
        msg += "账号不能为空\n";
    }
    if ($("#name").val() == "") {
        msg += "姓名不能为空\n";
    }
    $("#form_submit .userItem").each(function () {
        if ($(this).prop("checked")) {
            $(this).attr("name", "roleList[" + num + "].role_id");
            num++;
        }
    })
    if (msg.length > 0) {
        alert(msg);
        return false;
    } else {
        return true;
    }
}

/*多个用户ID*/
function User_Ids() {
    var a = 0;
    var ids = "";
    $(".subPick").each(function () {
        if ($(this).prop("checked")) {
            a = $(this).attr("title");
            ids += a + ",";
        }
    })
    ids = ids.substring(0, ids.length - 1);
    if (ids != null && ids != "") {
        deletedUser(ids, "批量");
    } else {
        alert("请选择要删除的用户！");
    }

}

/*删除系统用户*/
function deletedUser(id, text) {
    $.ajax({
        url: "/sysUser/deletedUser/" + id,
        dataType: "json",
        success: function (data) {
            alert(text + data.message);
            location.reload();
        }, error: function (XMLHttpRequest, textStatus, errorThrow) {
            var jsonError = XMLHttpRequest.responseJSON;
            if (jsonError != null) {
                alert(jsonError.message);
            } else {
                console.log(XMLHttpRequest);
                alert("未知错误");
            }
        }
    })
}

/*角色名称视图加载*/
function roleName() {
    $.ajax({
        url: "/sysUser/role_view",
        async: false,
        datatype: "json",
        success: function (res) {
            var content = $("#role")
            var data = res.data;
            for (var i = 0; i < data.length; ++i) {
                var $div = $("<div class='role_pick'></div>");
                $div.append("<input type='checkbox'  class='userItem'  value='" + data[i].role_id + "' title='" + data[i].name + "'>");
                content.append($div)
            }
            var form = layui.form;
            form.render();
        }, error: function (XMLHttpRequest, textStatus, errorThrow) {
            var jsonError = XMLHttpRequest.responseJSON;
            if (jsonError != null) {
                alert(jsonError.message);
            } else {
                console.log(XMLHttpRequest);
                alert("未知错误");
            }
        }
    })
}

/*遮罩层*/
function openMaskLayer() {
    layui.use('layer', function () {
        layer.msg('加载中...', {
            icon: 16,
            shade: [0.3, '#393D49'],
            time: 5000,
            area: "100px"
        })
    })
}

/**
 * 过滤空字符串
 * @param str
 * @returns {string}
 */
function filterNull(str) {
    return str == null ? "" : str;
}

/*登录日志页面加载*/
function loginlog_view(res) {
    var content = $(".layui-table>tbody");
    content.empty();//移除所有子元素
    var type = ["pc", "android", "ios"];
    var state = ["登陆失败", "登录成功"];
    for (var i = 0; i < res.length; ++i) {
        var data = res[i];
        content.append(
            "<tr>" +
            "<td>" + formatTime(data.login_date) + "</td>" +//登录日期
            "<td>" + data.user_account + "</td>" +//登录正好
            "<td>" + data.request_ip + "</td>" +//请求IP地址
            "<td>" + state[data.state] + "</td>" + //登录状态
            "<td>" + type[data.type] + "</td>" + //登录类型
            "<td>" + filterNull(data.model_number) + "</td>" +
            "<td>" + filterNull(data.imei) + "</td>" +
            "</tr>");
    }
}

/*操作日志页面加载*/
function OperationLog_view(res) {
    var content = $(".layui-table>tbody");//获取tbody
    content.empty();//移除所有子元素
    for (var i = 0; i < res.length; ++i) {
        var data = res[i];
        var $tr = $("<tr></tr>");
        $tr.append("<td>" + data.user_account + "</td>");//操作账号
        $tr.append("<td>" + data.request_ip + "</td>");//操作ip
        $tr.append("<td>" + formatTime(data.date) + "</td>");//操作时间
        $tr.append("<td>" + data.module + "</td>");//操作模块
        $tr.append("<td>" + data.type + "</td>");//操作类型
        $tr.append("<td></td>");//操作内容
        content.append($tr);
    }
}


/*权限值菜单请求*/
function permission_request() {
    $.ajax({
        url: "/login/findMyMenu",
        data: "json",
        success: function (res) {
            permission_view(res);
        }, error: function (XMLHttpRequest, textStatus, errorThrow) {
            var jsonError = XMLHttpRequest.responseJSON;
            if (jsonError != null) {
                alert(jsonError.message);
            } else {
                console.log(XMLHttpRequest);
                alert("未知错误");
            }
        }
    })
}

/*权限值菜单加载*/
function permission_view(res) {
    var listMenuOne = res.data.menu_1;
    var listMenuTwo = res.data.menu_2;
    for (var i = 0; i < listMenuOne.length; ++i) {
        var $li = ("<li class='layui-nav-item menu_item' id='menu_" + listMenuOne[i].menu_id + "'>" +
            "<a><i class='" + listMenuOne[i].icon + "'></i>" + listMenuOne[i].name + "</a> " +
            "<dl class='layui-nav-child'> " +
            "<dd><a id=\"nav_temp\" class=\"nav_item\" style=\"display:none\"></a></dd>" +
            "</dl>");
        $("#nav_group").append($li);
    }
    for (var i = 0; i < listMenuTwo.length; ++i) {
        var $dd = $("<dd><a class='nav_item' name='" + listMenuTwo[i].url + "'>" +
            "<i class='" + listMenuTwo[i].icon + "'></i>" + listMenuTwo[i].name + "</a>");
        $("#nav_group #menu_" + listMenuTwo[i].fk_parent_id + ">dl").append($dd);
    }
    /*    $("#nav_group .menu_item:first").addClass("layui-nav-itemed");*/
    layui.use('element', function () {
        var element = layui.element;


        /**
         * 左侧点击判断添加
         */
        $(".layui-nav-item .layui-nav-child a").click(function () { /*.homepage>a*/
            if ($(".layui-tab-item>iframe[src='" + $(this).prop("name") + "']").length == 0) {
                var options = {};
                options.id = new Date().getTime();
                options.title = $(this).text() + "<i data-id='" + options.id + "'></i>";
                options.content = "<iframe src = '" + $(this).prop("name") //name取值
                    + "' data-id='" + options.id + "'></iframe>";
                element.tabAdd('demo', options);

                //新添加的底部标签需要增加点击事件
                $(".layui-tab-title>li").click(function () {
                    afterTopClick(this);
                });
            }

            /**
             * 左侧带动顶部
             */
            var title = $(this).text();//当前左侧点击的text
            $(".layui-tab-title>li").each(function () {
                if ($(this).text().substr(0, $(this).text().length - 1) == title) { //当前顶部text匹配左侧text
                    $(this).click();
                }
            })
        });


        /**
         * 顶部带动左侧
         */
        $(".layui-tab-title>li").click(function () {
            afterTopClick(this);
        });

    });
}

/**
 * 点击顶部按键之后带动左侧(仅改变class)
 */
function afterTopClick(bt) {
    var curText = $(bt).text().substr(0, $(bt).text().length - 1);
    $(".layui-nav-child>dd.layui-this").removeClass("layui-this");
    $(".layui-nav-child>dd").each(function () {
        if ($(this).text() == curText) {
            $(this).addClass("layui-this");
        }
    });
}

function showPermission(baseModel) {
    if (baseModel.permissionButtons != null && baseModel.permissionButtons != "") {
        $(baseModel.permissionButtons).removeClass("hidden_ps");
    }

}

function downFile(actionName) {
    var form = $("<form action='" + actionName + "' method='post' target='' style='display:none;'></form>");
    $("body").append(form);
    form.submit();
    form.remove();
}

function impData(actionName) {
    var node = "#formImportTemp";
    if ($("#formImportTemp").length == 0) {
        $("body").append("<form id='" + node.substring(1) + "' action='" + actionName + "'></form>");
    }
    if ($("#importTempFile").length == 0) {
        $(node).append("<input id='importTempFile' type='file' name='baseModel.tempMFile'>");
        $("#importTempFile").change(function () {
            requestUtilParams.uploaderFile("#formImportTemp");
        });
    }
    $("#importTempFile").click();
}

function updateState(user_id, state) {
    $.ajax({
        url: "/sysUser/updateState/" + user_id + "/" + state,
        data: "json",
        success: function (res) {
            alert(res.message);
        }, error: function (XMLHttpRequest, textStatus, errorThrow) {
            var jsonError = XMLHttpRequest.responseJSON;
            if (jsonError != null) {
                alert(jsonError.message);
            } else {
                console.log(XMLHttpRequest);
                alert("未知错误");
            }
        }
    })
}







