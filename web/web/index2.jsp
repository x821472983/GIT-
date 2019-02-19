<%@ page import="Lis.Listener" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/6
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String username=request.getParameter("username");
int number=Listener.count;
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
当前用户：<%=username%><br>
当前在线人数：<%=number%><br>
所有用户：<br>
<%
    Map<String,String> list=Listener.map;
    for(Map.Entry entry:list.entrySet()){
        out.print("用户ID："+entry.getKey()+"   用户名："+entry.getValue().toString()+"<br>");
    }
%>
</body>
</html>
