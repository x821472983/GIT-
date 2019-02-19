<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    if(application.getAttribute("errorList")==null){
        List<Map<String,String>> errorlist=new ArrayList<Map<String,String>>();
        application.setAttribute("errorList",errorlist);
    }
    Map<String,String> map = new HashMap<String,String>();
    String msg = session.getAttribute("msg").toString();
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    map.put("username",username);
    map.put("password",password);
    map.put("msg",msg);
    List<Map<String,String>>errorList=(List<Map<String,String>>) application.getAttribute("errorList");
    errorList.add(map);
    for(Map<String,String> m:errorList){
        out.print(m.get("username")+","+m.get("password")+","+m.get("msg")+"<br>");
    }
%>
<a href="login.jsp">返回登录页面</a>
</body>
</html>
<script>alert("<%=msg%>")</script>
