<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../layuimini-2/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="../../layuimini-2/css/public.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div class="layui-form layuimini-form">
    <div class="layui-form-item">
        <label class="layui-form-label required">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="nick" lay-verify="required" lay-reqtext="用户昵称不能为空" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="0" title="男" checked="">
            <input type="radio" name="sex" value="1" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">openId</label>
        <div class="layui-input-block">
            <input type="number" name="openId" lay-verify="required" lay-reqtext="openId不能为空"
                   value=""
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">sessionKey</label>
        <div class="layui-input-block">
            <input type="text" name="sessionKey" lay-verify="required" lay-reqtext="sessionKey不能为空" value=""
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">imgUrl</label>
        <div class="layui-input-block">
            <textarea name="imgUrl" class="layui-textarea"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</div>
<script src="../../layuimini-2/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.$;

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            var index = layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            }, function () {
                // 关闭弹出层
                layer.close(index);
                var iframeIndex = parent.layer.getFrameIndex(window.name);
                parent.layer.close(iframeIndex);

            });
            return false;
        });

    });
</script>
</body>
</html>
