<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/6
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String checkbox = request.getParameter("checkbox");
    if (username.equals("")) {
        String msg="账号不能为空";
        session.setAttribute("msg",msg);
    }
    else if (username.equals("admin")) {
        if(password.equals("")) {

            String msg="密码不能为空";
            session.setAttribute("msg",msg);
        }
        else if (password.equals("123456")) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            response.sendRedirect("successed.jsp");
        }
        else{

            String msg="密码错误";
            session.setAttribute("msg",msg);
            }
    } else {

        String msg="账号名错误";
        session.setAttribute("msg",msg);
    }
    request.getRequestDispatcher("error.jsp").forward(request,response);

%>
</body>
</html>
