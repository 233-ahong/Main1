<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layuimini-2/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="/layuimini-2/css/public.css" media="all">
    <style>
        .layui-form-item .layui-input-company {
            width: auto;
            padding-right: 10px;
            line-height: 38px;
        }
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <div class="layui-form layuimini-form">
            <div class="layui-form-item">
                <label class="layui-form-label required">旧的密码</label>
                <div class="layui-input-block">
                    <input type="hidden" name="admin_name" value="${sessionScope.user.admin_name}">
                    <input type="password" name="old_password" lay-verify="required" lay-reqtext="旧的密码不能为空"
                           placeholder="请输入旧的密码" value="" class="layui-input">
                    <tip>填写自己账号的旧的密码。</tip>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">新的密码</label>
                <div class="layui-input-block">
                    <input type="password" name="new_password" lay-verify="required" lay-reqtext="新的密码不能为空"
                           placeholder="请输入新的密码" value="" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">新的密码</label>
                <div class="layui-input-block">
                    <input type="password" name="again_password" lay-verify="required" lay-reqtext="新的密码不能为空"
                           placeholder="请输入新的密码" value="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
                </div>
                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert">
                        <span>&times;</span>
                    </button>
                    <strong>${msg}</strong>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/layuimini-2/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="/layuimini-2/js/lay-config.js?v=1.0.4" charset="utf-8"></script>
<script>
    layui.use(['form', 'miniTab'], function () {
        var form = layui.form,
            $ = layui.jquery,
            layer = layui.layer,
            miniTab = layui.miniTab;

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            console.log(data.field.new_password);
            if (data.field.new_password === data.field.again_password) {
                var index = layer.alert(${sessionScope.user.admin_name}, {
                        title: '确定修改密码？'
                    }, function () {
                        layer.close(index);
                        $.ajax({
                            url: '${pageContext.request.contextPath}/admins/setAdminPassword',
                            type: "POST",
                            dataType: "json",
                            contentType: "application/json",
                            data: JSON.stringify(data.field),
                        })
                    }
                );

            }
            return false;
        });

    });
</script>
</body>
</html>