<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>操作日志</title>
</head>
${basePath}
<script src="/base/js/myJavaScript.js" type="text/javascript"></script>
<link href="/base/css/myCss.css" rel="stylesheet" type="text/css">
<script type="application/javascript" src="/views/system/NewJS.js"></script>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin: 20px">
    <legend>筛选查询</legend>
</fieldset>

<div>
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">账号</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" placeholder="请输入账号">
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">操作模块</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" placeholder="请输入模块">
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">操作时间</label>
                <div class="layui-input-inline">
                    <input type="date" class="layui-input">
                </div>
            </div>

            <div class="layui-inline">
                <input type="submit" class="layui-btn" value="搜索">
            </div>
        </div>
    </form>
</div>

<fieldset class="layui-elem-field layui-field-title" style="margin: 20px">
    <legend>操作日志</legend>
</fieldset>

<div>
    <table class="layui-table" style="text-align: center">
        <thead>
        <tr>
            <td>操作账号</td>
            <td>操作ip</td>
            <td>操作时间</td>
            <td>操作模块</td>
            <td>操作类型</td>
            <td>操作内容</td>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>

<div id="page" class="layui-box layui-laypage layui-laypage-default">

</div>
</body>
</html>
<script>
    $(function () {

        var view="/Operation/selectOperationLog";
        callbackMethod(1,view);
        function callbackMethod(currpage,view) {
            if (currpage == null)
                currpage = 1;
            $.ajax({
                url: view,
                data: "baseModel.queryParams.curr_page=" + currpage,
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
                            jump: function (pageInfo, first) {
                                if (!first) {
                                    callbackMethod(pageInfo.curr, view);
                                }
                            }
                        })

                    })
                    var data = res.data.list;
                    OperationLog_view(data);
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



    })



    //Demo
    layui.use('form', function () {
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>