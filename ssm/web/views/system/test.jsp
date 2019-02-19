<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>公告栏</title>
</head>
${basePath}
<script type="application/javascript" src="/base/js/requestUtil.js"></script>
<script type="application/javascript" src="/views/system/NewJS.js"></script>
<script type="application/javascript" src="/base/js/uploadPreview.js"></script>
<style>
    .role_pick {
        display: inline-block;
    }
</style>

<body>
<fieldset class="layui-elem-field layui-field-title" style="margin: 20px">
    <legend>新增用户</legend>
</fieldset>

<div>
    <form id="form_submit" class="layui-form" action="" style="width: 500px;height: 400px;">


        <div class="layui-form-item">
            <label class="layui-form-label">发布丶公告题目</label>
            <div class="layui-input-block">
                <input id="account" type="text" name="report.report_title" placeholder="请输入公告题目" autocomplete="off"
                       class="layui-input">
            </div>
        </div>



        <div class="layui-form-item">
            <label class="layui-form-label">发布丶公告内容</label>
            <div class="layui-input-block">
                <textarea id="name" type="text" name="report.report_content" placeholder="请输入公告内容" autocomplete="off"
                       class="layui-input"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label"></label>
            <button type="button" id="choice">请选择发送人</button>
            <div class="layui-input-block" id="choice_send">

            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="button" value="提交" class="layui-btn" id="btn_submit">
            </div>

        </div>

    </form>

    <form id="form_submit_tow" class="layui-form" action="" style="width: 500px;height: 400px;">


        <div class="layui-form-item">
            <label class="layui-form-label">查看丶公告</label>
            <div class="layui-input-block">
                <input id="report_title" type="text" name="report.report_title" placeholder="公告题目" autocomplete="off"
                       class="layui-input">
            </div>
        </div>



        <div class="layui-form-item">
            <label class="layui-form-label">查看丶公告内容</label>
            <div class="layui-input-block">
                <textarea id="report_content" type="text" name="report.report_content" placeholder="请输入公告内容" autocomplete="off"
                       class="layui-input"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="button" value="查看公告" class="layui-btn" id="btn_check">
            </div>

        </div>


    </form>
</div>
</body>
</html>
<script>

    $(document).ready(function () {
        $("#choice").click(function () {
            $.ajax({
                url: "/sysUser/UserChoice",
                datatype: "json",
                success: function (res) {
                    var content = $("#choice_send");
                    var $div = $("<div class='role_pick'></div>");
                    for (var i = 0; i < res.data.length; ++i) {
                        $div.append("<input class='userItem' type='checkbox' value='" + res.data[i].user_id + "' title='" + res.data[i].name + "'>")
                    }
                    content.append($div);
                    var form = layui.form;
                    form.render();
                }, error: function (XMLHttpRequest, textStatus, errorThrow) {
                    var jsonError = XMLHttpRequest.responseJSON;
                    if (jsonError != null) {
                        alert(jsonError.message);
                    } else {
                        console.log(XMLHttpRequest);
                        alert("未知错误");
                    }
                }
            })
        })

        $("#btn_submit").click(function () {
            var num=0;
            $("#form_submit").attr("action", "/sysUser/reportSend");
            $("#form_submit .userItem").each(function () {
                if ($(this).prop("checked")) {
                    $(this).attr("name", "user[" + num + "].user_id");
                    num++;
                }
            })
            requestUtilParams.uploaderFile("#form_submit").done(function (res) {
                alert(res.message);
                parent.$("iframe[src='/views/system/test.jsp']").prop("src", '/views/system/test.jsp');
                parent.layui.element.tabDelete('demo', parent.$(".layui-tab-title .layui-this").attr("lay-id"));
            })
        })

        $("#btn_check").click(function () {
            $.ajax(function () {

            })
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
    })





</script>