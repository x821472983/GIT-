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
        <div class="layui-form-item" id="img">
            <label class="layui-form-label">头像</label>
            <input class="" type="file" id="tempMFile_add" name="baseModel.tempMFile"/>
            <img id="portrait_img_add" src="/base/css/images/accounthead.png" width="100" height="100"/>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">账号</label>
            <div class="layui-input-block">
                <input id="account" type="text" name="user.account" placeholder="请输入账号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>



        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input id="name" type="text" name="user.name" placeholder="请输入姓名" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-block">
                <input id="phonenumber" type="text" name="user.phonenumber" placeholder="请输入手机号码" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="user.gender" value="男" title="男" checked>
                <input type="radio" name="user.gender" value="女" title="女">
                <input type="radio" name="user.gender" value="保密" title="保密">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">出生年月</label>
            <div class="layui-input-block">
                <input type="date" name="user.birthday" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">籍贯</label>
            <div class="layui-input-block">
                <div style="width: 150px;float:left;">
                    <select name="user.address">
                        <option value="浙江">浙江</option>
                    </select>
                </div>
                <div style="width: 150px;float:left;margin-left: 20px">
                    <select name="user.address">
                        <option value="杭州">杭州</option>
                        <option value="嘉兴">嘉兴</option>
                        <option value="绍兴">绍兴</option>
                    </select>
                </div>
            </div>

        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" name="user.email" placeholder="请输入邮箱" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">专业</label>
            <div class="layui-input-block">
                <input type="text" name="title" placeholder="请输入专业名称" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">QQ账号</label>
            <div class="layui-input-block">
                <input type="text" name="title" placeholder="请输入QQ号码" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">所属角色</label>
            <div class="layui-input-block" id="role">

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
    $("#btn_submit").click(function () {
        openMaskLayer();
        user_add_submit();
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

    new uploadPreview({
        UpBtn: "tempMFile_add",
        DivShow: "img",
        ImgShow: "portrait_img_add"
    });

    roleName();




</script>