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
    <legend>公告栏</legend>
</fieldset>

<div>
    <form id="form_submit_tow" class="layui-form" action="" style="width: 500px;">


        <div class="layui-form-item">
            <label class="layui-form-label">公告题目</label>
            <div class="layui-input-block">
                <input id="report_title" type="text" name="report.report_title" placeholder="公告题目" autocomplete="off"
                       class="layui-input">
            </div>
        </div>



        <div class="layui-form-item">
            <label class="layui-form-label">公告内容</label>
            <div class="layui-input-block">
                <textarea id="report_content" type="text" name="report.report_content" placeholder="请输入公告内容" autocomplete="off"
                          class="layui-input"></textarea>
            </div>
        </div>



    </form>
</div>
</body>
</html>
<script>

    $(document).ready(function () {


        $("#btn_close").click(function () {

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