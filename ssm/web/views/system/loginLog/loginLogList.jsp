<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>登录日志</title>
</head>
${basePath}
<link rel="stylesheet" type="text/css" href="/base/plugins/layui-v2.3.0/layui/css/layui.css">
<script type="text/javascript" charset="UTF-8" src="/base/plugins/layui-v2.3.0/layui/layui.js"></script>
<script type="application/javascript" src="/views/system/NewJS.js"></script>
<style>
    .layui-laypage .layui-laypage-curr {
        color: white;
        background-color: #009688;
    }

    .layui-laypage a:hover {
        color: #333;
    }

    .layui-laypage a.hidden {
        display: none;
    }
</style>
<body>
<div>
    <fieldset class="layui-elem-field layui-field-title" style="margin: 20px">
        <legend>筛选查询</legend>
    </fieldset>

    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户账号</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" placeholder="请输入账号">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">登录IP</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" placeholder="请输入IP">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">登录时间</label>
                <div class="layui-input-inline">
                    <input type="date" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <input type="submit" value="搜索" class="layui-btn">
            </div>
        </div>

    </form>


</div>
<fieldset class="layui-elem-field layui-field-title" style="margin: 20px">
    <legend>登录日志列表</legend>
</fieldset>
<div>
    <table class="layui-table" style="text-align: center">
        <thead>
        <tr>
            <td>登录时间</td>
            <td>用户账号</td>
            <td>登录ip</td>
            <td>登录状态</td>
            <td>登录类型</td>
            <td>手机型号/浏览器型号</td>
            <td>imei</td>
        </tr>
        </thead>
        <tbody>
        <!--json数据-->
        </tbody>
    </table>
</div>

<div class="layui-box layui-laypage" id="page"></div>
</body>
</html>
<script>
    $(document).ready(function () {
        var view="/login/selectLoginLog";
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
                    loginlog_view(data);
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

        /**
         * 全选框
         */
        $(".allPick").click(function () {
            $(".subPick").prop("checked", $(this).prop("checked"));
        });







    //layui
    layui.use('form', function () {
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>