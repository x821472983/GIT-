<%--
  Created by IntelliJ IDEA.
  User: L_captain
  Date: 2018/8/16
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>error 404</title>
</head>
<script type="text/javascript" src="/base/js/jquery-3.3.1.js"></script>
<body>
Not Found!<br>
<a id="time">5</a><br>
返回<a href="/views/system/login.jsp">登录页面</a>
</body>
</html>
<script>
    $(function () {
        setInterval(function () {
            var time=$("#time").text();
            if(time==0)
                location.href="/views/system/login.jsp";
            else{
                $("#time").text(parseInt(time)-1);
            }
        },1000)

    })
</script>
