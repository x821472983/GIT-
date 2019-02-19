<%@ page import="java.util.Date" %>
<%@ page import="user.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录界面</title>
    <script type="text/javascript" src="jquery-3.3.1.js"></script>
    <link rel="stylesheet" type="text/css" href="jquery-ui/jquery-ui/jquery-ui.min.css">
    <script src="jquery-ui/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
    <link href="buttonCaptcha/buttonCaptcha/jquery.buttonCaptcha.styles.css" rel="stylesheet" type="text/css">
    <script src="buttonCaptcha/buttonCaptcha/jquery.buttonCaptcha.js"></script>
    <script type="application/javascript" src="jquery.cookie.js"></script>
</head>
<!--<link rel="stylesheet" href="layui-v2.3.0/layui/css/layui.css" media="all">-->
<style>
    body{
        margin: 0px;
        padding: 0px;
    }

    .background_pic {
        width: 100%;
        background-color: #1ABC9C;
        position: absolute;
        left: 50%;
        top: 50%;
        top: 50%;
        margin-left: -50%;
        margin-top: -12%;
        z-index: -1;
    }

    .main {
        box-shadow: 0px 0px 10px gray;
        margin: 50px auto;
        width: 350px;
        text-align: center;
        background-color: white;
        padding-bottom: 20px;
    }

    input {
        border: 1px #c2c2c2 solid;
        width: 250px;
        height: 40px;
        padding-left: 40px;
    }

    input::placeholder {
        color: #c2c2c2;
    }

    /*.clear {
        background-color: transparent;
        border: none;
        color: #c2c2c2;
        padding: 15px;
        position: absolute;
        right: 538px;
    }*/

    .submit {
        width: 300px;
        display: block;
        margin: 0 auto;
        height: 50px;
        border: none;
        background-color: #1ABC9C;
        font-size: 20px;
        margin-top: 30px;
        padding-left: 20px;
        color: white
    }

    .small_icon {
        position: absolute;
        padding: 6px;
    }

    .foot {
        width: 100%;
        color: gray;
        font-size: 15px;
        text-align: center;
        position: absolute;
        bottom: 10px;
    }

    .captcha_gbws_wrap{
        margin: 0px;
    }

    .verifyGroup {
        padding-left: 80px;
        margin-bottom: 10px;
    }
</style>
<body>
<div>
    <img class="background_pic" src="images/background.png">
</div>
<div class="main">

    <div style="background-color: #1ABC9C;height: 15px;"></div>
    <br>

    <img class="main_icon" src="images/icon.png" width="80px" alt="总图标">

    <h2 class="title" style="color: #1ABC9C">统一身份认证系统</h2>

    <form action="" onsubmit="return nameAndPassword()" method="post">
        <p>
            <img class="small_icon" src="images/用户名图标.png">

            <input type="text" id="username" name="username" placeholder="请输入用户名">

            <!--<button type="button" class="clear">X</button>-->
        </p>
        <p>
            <img class="small_icon" src="images/密码图标.png">

            <input type="password" id="password" name="password" placeholder="请输入密码">

            <!--<button type="button" class="clear">X</button>-->
        </p>

        <div class="verifyGroup" id="verifyGroup"></div> <!--验证码区域-->

        <input class="submit" type="submit" value="登录" id="btnSubmit">

        <!--<br><a href="index.html">测试登录</a>-->

    </form>
</div>

<div class="foot">
    <label>Copyright 东臣科技 All Rights Reserve</label>
</div>

</body>
</html>
<script>
    /**
     * 验证用户名和密码是否正确
     * @returns {boolean}
     */
    function nameAndPassword() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var pattern = /admin/i;
        if (pattern.test(username) && password != "") {
            return true;
        }
        else {
            alert("登录失败");
            unLocked();
            return false;
        }
    }

    function unLocked() {
        $(".captcha_gbws_wrap").remove();
        /**
         * 验证码
         */
        $("#btnSubmit").buttonCaptcha({
            codeWord: 'u',
            codeZone: 'nlock',
            captchaHeader: '请将字符移动到框内',
            captchaUnlocked: '通过验证'
        });

        $(".captcha_gbws_wrap").appendTo("#verifyGroup");//添加至verifyGroup
    }

</script>