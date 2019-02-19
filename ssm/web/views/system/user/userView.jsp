<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户查看页</title>
</head>
${basePath}
<script type="application/javascript" src="/views/system/NewJS.js"></script>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin: 20px">
    <legend>用户信息</legend>
</fieldset>

<div style="text-align: center">
    <div>
        <img id="img" src="">
        <p style="font-weight: bold;font-size: 16px" id="userAccount"></p>
        <p style="font-weight: bold;font-size: 20px" id="userRole"></p>
    </div>
    <div>
        <table class="layui-table" id="user_message">
            <thead>
            <tr>
                <td>账号</td>
                <td>姓名</td>
                <td>性别</td>
                <td>生日</td>
                <td>籍贯</td>
                <td>专业</td>
                <td>邮箱</td>
                <td>用户类型</td>
                <td>注册时间</td>
                <td>qq</td>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</div>

<fieldset class="layui-elem-field layui-field-title" style="margin: 20px">
    <legend>登录历史记录</legend>
</fieldset>

<div style="text-align: center">
    <table class="layui-table" id="loginlog">
        <thead>
        <tr>
            <td>登录时间</td>
            <td>用户账号</td>
            <td>登录ip</td>
            <td>登录状态</td>
            <td>登录类型</td>
            <td>手机型号/浏览器型号</td>
            <td>imei</td>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<div id="page" class="layui-box layui-laypage layui-laypage-default">

</div>
</body>
</html>
<script>
    //Demo
    layui.use('form', function () {
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });

    $(document).ready(function () {
        /*请求个人信息*/
        user_id_view();

        /*请求登录信息*/
        var currpage = 1;
        var loginlog = "/sysUser/selectLoginByUserId/" + getUrlParam("user_id");
        callbackMethod(currpage, loginlog);

        function callbackMethod(currpage, view) {
            if (currpage == null)
                currpage = 1;
            $.ajax({
                url: view,
                data: "baseModel.queryParams.curr_page=" + currpage,
                dataType: "json",
                type: "post",
                success: function (res) {
                    layui.use(['laypage', 'layer'], function () {
                        var laypage = layui.laypage;
                        laypage.render({
                            elem: 'page',
                            curr: res.data.pageNum,
                            limit: res.data.pageSize,
                            count: res.data.total,
                            prev: '<',
                            next: '>',
                            jump: function (pageInfo, first) {
                                if (!first) {
                                    callbackMethod(pageInfo.curr, view);
                                }
                            }
                        })

                    })
                    var data = res.data.list;
                    user_loginlog_view(data);
                }, error: function (XMLHttpRequest, textStatus, errorThrow) {
                    var jsonError = XMLHttpRequest.responseJSON;
                    if (jsonError != null) {
                        alert(jsonError.message);
                    } else {
                        console.log(XMLHttpRequest);
                        alert("未知错误");
                    }
                }
            });
        }
    })


</script>
