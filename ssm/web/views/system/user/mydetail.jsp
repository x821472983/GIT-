<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人信息</title>
</head>
${basePath}
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin: 20px">
    <legend>个人信息</legend>
</fieldset>

<div style="text-align: center">
    <div>
        <img src="/base/css/images/accounthead.png">
        <p style="font-weight: bold;font-size: 16px">18000000000</p>
        <p style="font-weight: bold;font-size: 20px">超级管理员</p>
    </div>
    <div>
        <table class="layui-table">
            <thead>
            <tr>
                <td>账号</td>
                <td>昵称</td>
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
            <tr>
                <td><label>18000000000</label></td>
                <td><label>admin</label></td>
                <td><label>保密</label></td>
                <td><label></label></td>
                <td><label></label></td>
                <td><label>信息与计算科学</label></td>
                <td><label></label></td>
                <td><label></label></td>
                <td><label>2017-07-24 17:25:38</label></td>
                <td><label></label></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<fieldset class="layui-elem-field layui-field-title" style="margin: 20px">
    <legend>登录历史记录</legend>
</fieldset>

<div style="text-align: center">
    <table class="layui-table">
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
        <tr>
            <td>2018-06-19 14:48:38</td>
            <td>18000000000</td>
            <td>120.1.1.1</td>
            <td>密码错误</td>
            <td>Andriod</td>
            <td>华为</td>
            <td></td>
        </tr>
        <tr>
            <td>2018-06-19 14:48:38</td>
            <td>18000000000</td>
            <td>120.1.1.1</td>
            <td>账号错误</td>
            <td>ios</td>
            <td>iphone7</td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>
<div class="layui-box layui-laypage layui-laypage-default">
    <a href="">1</a>
    <a href="">2</a>
    <a href="">3</a>
    <a href="">上一页</a>
    <a href="">下一页</a>
</div>
</body>
</html>
<!--
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
</script>-->
