<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增用户</title>
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
    <form id="form_submit" class="layui-form" action="" style="width: 500px;">


        <div class="layui-form-item">
            <label class="layui-form-label">公告题目</label>
            <div class="layui-input-block">
                <input id="account" type="text" name="report_name" placeholder="请输入公告题目" autocomplete="off"
                       class="layui-input">
            </div>
        </div>



        <div class="layui-form-item">
            <label class="layui-form-label">公告内容</label>
            <div class="layui-input-block">
                <input id="name" type="text" name="report" placeholder="请输入公告内容" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label"></label>
            <button id="choice">请选择发送人</button>
            <div class="layui-input-block" id="choice_send">

            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="button" value="提交" class="layui-btn" id="btn_submit">
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
                url:"/sysUser/UserChoice",
                data:"json",
                success:function (res) {
                    var $div=$("<div id='choice_send'></div>");
                    for(var i=0;i<res.data.length;++i){
                        $div.append("<input class='userItem' type='checkbox' name='name' value='"+res.data[i].name+"'>")
                    }
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





</script>