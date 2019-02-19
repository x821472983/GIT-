<%@ page import="user.User" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="page_set/jquery-3.3.1.js"></script>
    <script type="application/javascript" src="security.js"></script>
    <script type="application/javascript" src="jquery.cookie.js"></script>
</head>

<body>
<form id="form_login" method="post">
    <p>用户名:</p> <input type="text" name="user_name" id="username">
    <p>密码:</p><input type="password" id="password_temp">
    <input type="hidden" name="password" id="password">
    <input type="checkbox" id="checkbox2">记住密码
    <input type="checkbox"  id="checkbox"> 下次自动登录
    <button type="button" class="submit" id="btn_login">登录</button>
</form>
<%
    User p = (User) request.getAttribute("p");
    if (p != null) {
        out.print(p.toString());
    }
%>
</body>
</html>
<script>
    if($("#checkbox").prop("checked")){
        if(cookie("username")!=null&&cookie("password")!=null)
            location.href="index.jsp";
        else{
            var username=$("#username");
            var password=$("#password_temp");
            $.cookie('username', username);
            $.cookie('password', password);
        }
    }
   else{
        $.cookie('username', null);
        $.cookie('password', null);
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
        $("#btn_login").click(function () {
            var password = $("#password_temp").val();
            if (password != "") {
                var pas = rsaEncrypt(password);
                $("#password").val(pas);
                $("#form_login").submit();
                alert("555");
                window.location.href="//https://www.jb51.net/";
            }
        });
    });
</script>


