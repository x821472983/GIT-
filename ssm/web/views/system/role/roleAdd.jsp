<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="com.base.em.EnumRole"
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增角色</title>
</head>
${basePath}
<script type="application/javascript" src="/base/js/requestUtil.js"></script>
<script type="application/javascript" src="/views/system/NewJS.js"></script>
<script type="application/javascript" src="/base/js/uploadPreview.js"></script>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin: 20px">
    <legend>新增角色</legend>
</fieldset>

<form class="layui-form" action="" style="width: 800px" id="form_submit">
    <div class="layui-form-item">
        <label class="layui-form-label">角色名称</label>
        <div class="layui-input-block">
            <input type="text" name="role.name" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea name="role.memo" class="layui-textarea"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <%
            int num = 0;
            for (EnumRole roleItem : EnumRole.values()) {
        %>
        <dl class="layui-input-block item">
            <dt style="display: inline">
                <input type="checkbox" title="<%=roleItem.getName()%>">
                <input type="hidden" value="<%=roleItem.getCode()%>" name="listRolePermissions[<%=num%>].code">
            </dt>
            <%
                String[] values = roleItem.getRoleValue().split("，");
                for (int i = 0; i < values.length; ++i) {
            %>
            <dd style="display: inline">
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
        <dl class="layui-input-block AllPick">
            <dt style="display: inline">
                <input type="checkbox" title="选择全部">
            </dt>
        </dl>


    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="button" value="提交" class="layui-btn" id="btn_submit">
            <input type="button" value="关闭" class="layui-btn" id="btn_close">
        </div>

    </div>
</form>

</body>
</html>
<script>


    $(document).ready(function () {
        $("#btn_close").click(function () {
            parent.layui.element.tabDelete('demo', parent.$(".layui-tab-title .layui-this").attr("lay-id"));
        });

        $("#btn_submit").click(function () {
            openMaskLayer();
            add_role_submit();
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


        /**
         * 各项主按钮点击
         */
        function itemClick(dl) {

            if (dl.children("input").prop("checked")) {
                dl.siblings("dd").children("input").prop("checked", true);
                dl.siblings("dd").children("div").addClass("layui-form-checked");
            } else {
                dl.siblings("dd").children("input").prop("checked", false);
                dl.siblings("dd").children("div").removeClass("layui-form-checked");
            }
        }


        /**
         * 所有主按钮点击事件绑定
         */
        $(".item").children("dt").click(function () {
            itemClick($(this));
        });


        /**
         * 全选按钮
         */
        $(".AllPick").click(function () {
            if ($(this).children("dt").children("input").prop("checked")) {
                $(this).siblings("dl").children().children("input").prop("checked", true);
                $(this).siblings("dl").children().children("div").addClass("layui-form-checked");
            } else {
                $(this).siblings("dl").children().children("input").prop("checked", false);
                $(this).siblings("dl").children().children("div").removeClass("layui-form-checked");
            }
        });

    });

</script>