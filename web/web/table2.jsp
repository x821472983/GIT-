<%--
  Created by IntelliJ IDEA.
  User: L_captain
  Date: 2018/8/8
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <script type="text/javascript" src="page_set/jquery-3.3.1.js"></script>
</head>
<body>
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="file" name="file2">
    <button type="submit" >提交</button>
</form>
</body>
</html>
<%--<script>
    $(function () {
        $("#btn").click(function () {
            $.post("getPerson", {name: $("#name").val()}, function (data) {
                alert(data);
            });
        })
    })
</script>--%>
