<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>登录界面</title>
    <script type="text/javascript" src="/base/js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" type="text/css" href="/base/plugins/jquery-ui/jquery-ui/jquery-ui.min.css">
    <script src="/base/plugins/jquery-ui/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
    <link href="/base/plugins/buttonCaptcha/buttonCaptcha/jquery.buttonCaptcha.styles.css" rel="stylesheet"
          type="text/css">
    <script src="/base/plugins/buttonCaptcha/buttonCaptcha/jquery.buttonCaptcha.js"></script>
    <script type="application/javascript" src="/base/js/security.js"></script>
    <script type="application/javascript" src="/base/js/jquery.cookie.js"></script>

</head>
<style>
    body {
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

    .captcha_gbws_wrap {
        margin: 0px;
    }

    .verifyGroup {
        padding-left: 80px;
        margin-bottom: 10px;
    }

    input[type = "checkbox"] {
        width: 15px;
        height: 15px;
        position: relative;
        top: 2px;
    }

</style>
<body>
<div>
    <img class="background_pic" src="/base/css/images/background.png">
</div>
<div class="main">

    <div style="background-color: #1ABC9C;height: 15px;"></div>
    <br>

    <img class="main_icon" src="/base/css//images/icon.png" width="80px" alt="总图标">

    <h2 class="title" style="color: #1ABC9C">统一身份认证系统</h2>

    <form action="" method="post" id="form_login">
        <p>
            <img class="small_icon" src="/base/css/images/用户名图标.png">

            <input type="text" id="account" name="User_account" placeholder="请输入用户名">

        </p>
        <p>
            <img class="small_icon" src="/base/css/images/密码图标.png">

            <input type="password" id="password" placeholder="请输入密码">
            <input type="password" name="User_password" hidden id="Epassword">

            <!--<button type="button" class="clear">X</button>--><%--测试登录--%>
        </p>
        <div class="verifyGroup" id="verifyGroup"></div> <!--验证码区域-->
        <input type="checkbox" id="remember" value="记住密码">记住密码
        <input type="checkbox" id="auto" value="自动登录">自动登录
        <input class="submit" type="button" value="登录" id="btnSubmit">
    </form>
</div>

<div class="foot">
    <label>Copyright 东臣科技 All Rights Reserve</label>
</div>

</body>
</html>
<script>


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

    function rsaEncrypt(str) {
        var encrypedPwd = "";
        $.ajax({
            url: "/RSA/generateRSAJsKey", async: false,
            success: function (result) {
                if (result != null) {
                    //加密模
                    var Modulus = result.split(';')[0];
                    //公钥指数
                    var public_exponent = result.split(';')[1];
                    //通过模和公钥参数获取公钥
                    var key = new RSAUtils.getKeyPair(public_exponent, "", Modulus);
                    //颠倒密码的顺序，要不然后解密后会发现密码顺序是反的
                    var reversedPwd = str.split("").reverse().join("");
                    //对密码进行加密传输
                    encrypedPwd = RSAUtils.encryptedString(key, reversedPwd);
                }
            }, error: function () {
                alert("请求异常");
            }
        });
        return encrypedPwd;
    }

    $(function () {
        $("#btnSubmit").click(function () {
            var name = $("#account").val();
            var password = $("#password").val();
            if (name != null && password != null && name != "" && password != "") {
                var pas = rsaEncrypt(password);
                $("#Epassword").val(pas);

                //上传表单
                var form = new FormData($("#form_login")[0]);
                $.ajax({
                    url: "/login/login",
                    type: "post",
                    data: form,
                    //因为data值是FormData对象，不需要对数据做处理
                    processData: false,
                    //因为是由<form>表单构造的FormData对象，且已经声明了属性enctype="multipart/form-data"，所以这里设置为false
                    contentType: false,
                    //接收后台返回的数据
                    success: function (data) {
                        var m = data.message;
                        if (m =="登陆成功") {
                            if ($("#remember").prop("checked")) {
                                var account = $("#account").val();
                                var password = $("#password").val();
                                $.cookie('account', account);
                                $.cookie('password', password);
                                $.cookie('remember',"checked");
                            }
                            else {
                                $.cookie('account', "");
                                $.cookie('password', "");
                                $.cookie('remember',null);
                            }
                            if ($("#auto").prop("checked")) {
                                $.cookie("auto", true);
                            } else {
                                $.cookie('auto', null);
                            }
                            location.href = "/views/system/index.jsp";
                        } else { // 登录失败，弹出验证码
                            unLocked();
                        }
                    },
                    error: function (XMLHttpRequest,textStatus,errorThrow) {
                            var jsonError=XMLHttpRequest.responseJSON;
                            if(jsonError!=null){
                            alert(jsonError.message);
                            }else{
                                console.log(XMLHttpRequest);
                                alert("未知错误");
                            }
                    }
                });
            }

            else {
                alert("用户名或密码不能为空");
            }
        });

        $("#remember").prop("checked",$.cookie("remember")=="checked");
        $("#auto").prop("checked",$.cookie("auto")=="true");
        <%
            String exit = request.getParameter("exit");
            if (exit != null) {%>
        $.cookie("auto",null);<%}%>

        if ($.cookie("account") != null && $.cookie("password") != null) {
            $("#account").val($.cookie("account"));
            $("#password").val($.cookie("password"));
        }
        if ($.cookie("auto") == "true") {
            $("#btnSubmit").click();
        }
    });

</script>