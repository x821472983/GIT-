<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="com.base.em.EnumRole" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>角色信息</title>
</head>

${basePath}
<script type="application/javascript" src="/views/system/NewJS.js"></script>
<style>
    .item {
        pointer-events: none; /*取消鼠标点击事件*/
    }
</style>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin: 20px">
    <legend>角色信息</legend>
</fieldset>

<form class="layui-form" action="" style="width: 800px">
    <div class="layui-form-item">
        <label class="layui-form-label">角色名称</label>
        <div class="layui-input-block">
            <input type="text" id="rolename" class="layui-input" disabled>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea id="note" class="layui-textarea" disabled></textarea>
        </div>
    </div>

    <div class="layui-form-item">

        <%
            int num = 0;
            for (EnumRole roleItem : EnumRole.values()) {
        %>
        <dl class="layui-input-block item">
            <dt style="display: inline" id="<%=roleItem.getCode()%>">
                <input type="checkbox" title="<%=roleItem.getName()%>">
                <input type="hidden" value="<%=roleItem.getCode()%>" name="listRolePermissions[<%=num%>].code">
            </dt>
            <%
                String[] values = roleItem.getRoleValue().split("，");
                for (int i = 0; i < values.length; ++i) {
                    String a = roleItem.getCode() + "-" + i;
            %>
            <dd style="display: inline" id="<%=a%>">
                <input type="checkbox" title="<%=values[i]%>" class="chk_item">
                <input class="permissionItem" type="hidden" name="listRolePermissions[<%=num%>].permission_value">
            </dd>
            <%
                }
            %></dl>
        <%
                num++;
            }
        %>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="button" value="关闭" class="layui-btn" id="btn_close">
            </div>
        </div>

    </div>
</form>

</body>
</html>
<script>
    /*获得地址栏问好后面的数值*/
    /*alert(getUrlParam("role_id"));*/
    $(document).ready(function () {
        $("#btn_close").click(function () {
            parent.layui.element.tabDelete('demo', parent.$(".layui-tab-title .layui-this").attr("lay-id"));
        });

        $.ajax({
            url: "/Role/selectRoleById/" + getUrlParam("role_id"),
            dataType: "json",
            type: "get",
            success: function (response) {
                var role = response.data.role;
                var permission = response.data.listRolePermissions;
                $("#rolename").val(role.name);
                $("#note").val(role.memo);
                code(permission);
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