<html>•
<head>• <title>Hello World</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        out.print(basePath);
    %>
    <base href="<%=basePath%>">
    <script src="demo/yingyong.js" type="text/javascript"></script>

</head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<body>




</body>

</html>
<script
