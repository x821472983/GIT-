<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
</head>
${basePath}
<script src="/base/js/myJavaScript.js" type="text/javascript"></script>
<link href="/base/css/myCss.css" rel="stylesheet" type="text/css">
<script type="application/javascript" src="/views/system/NewJS.js"></script>
<script type="application/javascript" src="/base/js/requestUtil.js"></script>
<style>
    .layui-input {
        width: 13%;
        display: inline-block;
        margin-right: 20px;
    }
    .hidden_ps{
        display:none;
    }
    .layui-table {
        text-align: center;
    }

    .layui-table th {
        text-align: center;
    }

</style>

<body>
<fieldset class="layui-elem-field layui-field-title" style="margin: 20px">
    <legend>筛选查询</legend>
</fieldset>
<div class="search">
    <form class="layui-form" style="margin-left: 20px">
        用户账号：<input type="text" name="account" placeholder="请输入账号" autocomplete="off" class="layui-input SearchContext">
        姓名：<input type="text" name="name" placeholder="请输入姓名" autocomplete="off" class="layui-input SearchContext">
        <input id="SearchUser" type="button" value="搜索" class="layui-btn hidden_ps">
        <a id="addUser" class="layui-btn hidden_ps" title="/views/system/user/userAdd.jsp">添加用户</a>
        <a id="deleted" class="layui-btn hidden_ps" >批量删除</a>
        <a class="layui-btn hidden_ps" id="import">导入</a>
        <a class="layui-btn hidden_ps" id="xlsExportTemplate">导出</a>
    </form>
</div>
<fieldset class="layui-elem-field layui-field-title" style="margin: 20px">
    <legend>用户信息列表</legend>
</fieldset>
<div class="list">
    <table class="layui-table">
        <thead>
        <tr>
            <th><input type="checkbox" class="allPick"></th>
            <th>账号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>手机号码</th>
            <th>所属角色</th>
            <th>区域</th>
            <th>出生日期</th>
            <th>账户启用状态</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody>

        </tbody>
    </table>

</div>

<div id="page" class="layui-box layui-laypage"></div>

</body>
</html>
<script>
    $(document).ready(function () {




        $("#SearchUser").click(function () {
            var queryData="";
            var num=0;
            $(".SearchContext").each(function () {
                queryData+="&baseModel.listAdvancedQuery["+num+"].fieldName="+$(this).attr("name");
                queryData+="&baseModel.listAdvancedQuery["+num+"].fieldValue="+$(this).val();
                num++;
            });
            var url="/sysUser/selectUserRoleLikeName";
            callbackMethod(1,url,queryData);
        })

        /*添加用户按钮*/
        $("#addUser").click(function () {
            var url = $(this).attr("title");
            var text = $(this).text();
            tabInnerChange(url, text);
        })

        /*批量删除按钮*/
        $("#deleted").click(function () {
            User_Ids();
        })

        /*导出*/
        $("#xlsExportTemplate").click(function () {
            var actionName="/sysUser/xlsExportTemplate";
            downFile(actionName);
        });

        /*导入*/
        $("#import").click(function () {
            var actionName="/sysUser/xlsImportTemplate";
            impData(actionName);
        })

        var view = "/sysUser/selectUser";
        callbackMethod(1, view,"");
        function callbackMethod(currpage,view,queryData) {
            if (currpage == null)
                currpage = 1;
            $.ajax({
                url: view,
                data: "baseModel.queryParams.curr_page=" + currpage+queryData,
                dataType: "json",
                type:"post",
                success: function (res) {
                    layui.use(['laypage', 'layer'], function () {
                        var laypage = layui.laypage;
                        laypage.render({
                            elem: 'page',
                            curr: res.data.pageNum,
                            limit: res.data.pageSize,
                            count: res.data.total,
                            prev: '<',
                            next: '>',
                            first: res.data.firstPage,
                            last: res.data.pages,
                            jump: function (pageInfo, first) {
                                if (!first) {
                                    callbackMethod(pageInfo.curr, view, queryData);
                                }
                            }
                        })

                    })
                    var data = res.data.list;
                    user_view(data);
                    showPermission(res);
                    layui.use('form',function () {
                        layui.form.render();
                    })
                }, error: function (XMLHttpRequest, textStatus, errorThrow) {
                    var jsonError = XMLHttpRequest.responseJSON;
                    if (jsonError != null) {
                        alert(jsonError.message);
                    } else {
                        console.log(XMLHttpRequest);
                        alert("未知错误");
                    }
                }
            });
        }

        /**
         * 全选
         */
        initAllPick();



    });
</script>