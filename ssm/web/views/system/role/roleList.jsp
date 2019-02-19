<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>角色列表</title>
</head>
${basePath}
<script src="/base/js/myJavaScript.js" type="text/javascript"></script>
<link href="/base/css/myCss.css" rel="stylesheet" type="text/css">
<script type="application/javascript" src="/views/system/NewJS.js"></script>
<style>
    .layui-table th {
        text-align: center;
    }
    .hidden_ps{
        display:none;
    }
</style>

<body>
<fieldset class="layui-elem-field layui-field-title" style="margin: 20px">
    <legend>筛选查询</legend>
</fieldset>

<div class="operation">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">角色名称</label>
                <div class="layui-input-inline">
                    <input  id="search" name="name" type="text" name="search_context" placeholder="请输入角色名称" class="layui-input search">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">角色备注</label>
                <div class="layui-input-inline">
                    <input  name="memo" type="text" name="search_context" placeholder="请输入角色备注" class="layui-input search ">
                </div>
            </div>
            <div class="layui-inline">
                <input id="bte_search"  type="button" value="搜索" class="layui-btn hidden_ps">
            </div>
            <a class="layui-btn hidden_ps" id="btn_add" hidden>添加角色</a>
            <a class="layui-btn hidden_ps" id="btn_moredel" hidden>批量删除</a>
        </div>
    </form>
</div>

<fieldset class="layui-elem-field layui-field-title" style="margin: 20px">
    <legend>角色信息列表</legend>
</fieldset>

<div class="list">
    <table class="layui-table" style="text-align: center">
        <thead>
        <tr>
            <th><input type="checkbox" onclick="allPick()" id="allPickBt"></th>
            <th>角色ID</th>
            <th>角色名称</th>
            <th>角色描述</th>
            <th>创建时间</th>
            <th>创建人</th>
            <th>修改时间</th>
            <th>修改人</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>
<div id="page"></div>

</body>
</html>
<script>

    $(function () {
        var view="/Role/selectRole";
        var queryCondition="";
        callbackMethod(1,view,queryCondition);

       /* /!*查询框*!/
        $("#search").bind('input propertychange', function () {
            var text = $("#search").val();
            var content = $("tbody");
            content.empty();
            if (text != null && text != "") {
                var url="/Role/selectRoleLikeName/"+text;
                callbackMethod(1,url);
            }
            else if ($("#search").val() == null || $("#search").val() == "") {
                parent.$("iframe[src='/views/system/role/roleList.jsp']").prop("src", '/views/system/role/roleList.jsp');
            }

        })*/

        /*搜索按钮*/
        $("#bte_search").click(function () {
            var i=0;
            queryCondition="";
            $(".search").each(function () {
                queryCondition+=
                    "&baseModel.listAdvancedQuery["+i+"].fieldName=" +$(this).attr("name")
                    +"&baseModel.listAdvancedQuery["+i+"].fieldValue="+$(this).val();
                i++;
            });
            var url="/Role/selectRoleLikeName";
            var j=1;
            callbackMethod(j,url,queryCondition);

        })

        /*批量删除按钮*/
        $("#btn_moredel").click(function () {
            Role_Ids();
        })

        /*增加用户按钮*/
        $("#btn_add").click(function () {
            var url = "/views/system/role/roleAdd.jsp";
            var text = "新增角色信息";
            tabInnerChange(url, text);
        })

        function callbackMethod(currpage,view,queryCondition){
            if(currpage==null)
                currpage=1;
            $.ajax({
                url:view,
                data: "baseModel.queryParams.curr_page=" + currpage+queryCondition,
                dataType:"json",
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
                                    callbackMethod(pageInfo.curr,view,queryCondition);
                                }
                            }
                        })

                    })
                    var data = res.data.list;
                    role_view(data);
                    showPermission(res);
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

        //layuiDemo
        layui.use('form', function () {
            var form = layui.form;

            //监听提交
            form.on('submit(formDemo)', function (data) {
                layer.msg(JSON.stringify(data.field));
                return false;
            });
        });
    });

    /**
     * 全选
     */
    function allPick() {
        var checks = document.getElementsByName("check");
        var bool = document.getElementById("allPickBt").checked;
        for (var i = 0; i < checks.length; ++i) {
            checks[i].checked = bool;
        }
    }



</script>
