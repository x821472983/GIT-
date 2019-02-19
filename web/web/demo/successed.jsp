<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/6
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    if(application.getAttribute("list")==null){
        List<Map<String,String>> list=new ArrayList<Map<String,String>>();
        application.setAttribute("list",list);
    }
    Map<String,String> map = new HashMap<String,String>();
    String username = session.getAttribute("username").toString();
    String password = session.getAttribute("password").toString();
    map.put("username",username);
    map.put("password",password);
    map.put("state","登录成功");
    List<Map<String,String>>list=(List<Map<String,String>>) application.getAttribute("list");
    list.add(map);
    for(Map<String,String> m:list){
        out.print(m.get("username")+","+m.get("password")+","+m.get("state")+"<br>");
    }
%>

<a href="login.jsp">返回登录页面</a>
</body>
</html>
<script>alert("登录成功")</script>
