<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/6
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<form method="post" action="index.jsp">
    <br><font size="4">账号登录</font><br>
    账号：<input type="text" name="username" placeholder="请输入用户名/邮箱/手机号"><br>
    密码：<input type="password" name="password" placeholder="输入密码"><br>
<%--    <input type="checkbox" name="checkbox"> 下次自动登录--%>
    <input type="submit" class="submit" value="登 录">
</form>
</body>
</html>
