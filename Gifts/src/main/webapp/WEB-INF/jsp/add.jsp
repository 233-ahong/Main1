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
    <label class="layui-form-label required">用户昵称</label>
    <div class="layui-input-block">
      <input type="text" name="nick" lay-verify="required" lay-reqtext="用户昵称不能为空" placeholder="请输入用户昵称" value=""
             class="layui-input">
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
      <input type="number" name="openId" lay-verify="required" lay-reqtext="openId不能为空" placeholder="请输入openId"
             value=""
             class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label required">sessionKey</label>
    <div class="layui-input-block">
      <input type="text" name="sessionKey" lay-verify="required" lay-reqtext="sessionKey不能为空"
             placeholder="请输入sessionKey" value=""
             class="layui-input">
    </div>
  </div>
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">头像地址</label>
    <div class="layui-input-block">
      <textarea name="imgUrl" class="layui-textarea" placeholder="请输入头像地址"></textarea>
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
        //关闭弹出层
        layer.close(index);
        $.ajax({
          url: '${pageContext.request.contextPath}/admins/addLoginUser',
          method: 'POST',
          dataType: 'json',
          contentType: "application/json",
          data: JSON.stringify(data.field),
          success: function (js) {
            layer.alert(js);
            if (js == "1") {
              layer.load(2);
              layer.msg("添加成功", {icon: 6});
              setTimeout(function () {
                window.parent.location.reload();//修改成功后刷新父界面
              }, 1000);
              //加载层-风格
            } else {
              layer.msg("已经存在该用户,不支持重复添加", {icon: 5});
            }
          }
        });
      });
      return false;
    });

  });
</script>
</body>
</html>
