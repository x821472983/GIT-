<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页框架</title>
</head>
${basePath}
<script type="application/javascript" src="/views/system/NewJS.js"></script>
<style>
    html, body {
        width: 100%;
        height: 100%;
    }

    .layui-tab-title .layui-this {
        background-color: #1abc9c;
        color: white;
    }

    .layui-tab-title .layui-this:after {
        border: none;
    }

    .layui-tab-item iframe {
        width: calc(100% - 200px);
        position: absolute;
        height: calc(100% - 123px);
        top: 104px;
        left: 200px;
        border: none;
    }

</style>
<body>
<div class="header" style="width: 100%;height: 60px;background-color: #393D49;border-bottom: 2px #1abc9c solid">

    <div class="header_left" style="float: left;width: 400px">
        <img src="/base/css/images/icon_white_1.png" style="float: left;padding: 13px">
        <img src="/base/css/images/icon_white_2.png" style="float: left;position: relative;right: 38px;top:20px">
        <h2 style="color: white;padding-top: 15px">统一身份认证系统</h2>
    </div>

    <div class="header_right" style="float: right; ">
        <ul class="layui-nav" lay-filter="">

            <li class="layui-nav-item">
                <!--<img src="images/admin_gray.png" style="padding-right: 5px">-->
                <a class="user_name">admin</a>

                <dl class="layui-nav-child" style="color: black">
                    <dt><span>帐号信息</span><a href="javascript:;">帐号设置</a></dt>
                    <dd><label>所属部门：</label><span>销售部</span></dd>
                    <dd><label>本次登录：</label><span>2018-07-18 14：51</span></dd>
                    <dd><label>登录地区：</label><span>浙江嘉兴</span></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/views/system/index.jsp">首页</a></li>
            <li class="layui-nav-item" ><a href="/sysUser/outLogin">退出登录</a></li>
        </ul>
    </div>
</div>

<!--左侧-->
<div class="content">
    <div class="content_left">
        <ul id="nav_group" class="layui-nav layui-nav-tree layui-nav-side" lay-filter="test" style="margin-top: 62px;border-radius: 0">
            <li class="layui-nav-item homepage"><a href="/views/system/index.jsp">首页</a></li>
        </ul>
    </div>


    <!--顶部-->
    <div class="content_right" style="margin-left: 200px;">

        <div class="layui-tab" lay-allowClose="true" style="margin: 0px" lay-filter="demo"><!--demo-->
            <ul class="layui-tab-title" style="border-bottom: 2px #1abc9c solid;">
                <li class="layui-this">首页</li>
            </ul>
            <div class="layui-tab-content" style="padding: 0px;">
                <div class="layui-tab-item layui-show">
                    <iframe src="/views/system/test.jsp"></iframe>
                </div>
            </div>
        </div>
        <div class="content_right_foot" style="text-align: center;width: calc(100% - 200px);
        color: white;position: absolute;bottom: 0px;background-color: #393D49">
            Copyright © 东臣科技, All Rights Reserved.
        </div>
    </div>
</div>
</body>
</html>
<script>
    $(document).ready(function () {
        permission_request();
        $("#id").text();
        //注意：导航 依赖 element 模块，否则无法进行功能性操作
    })
</script>