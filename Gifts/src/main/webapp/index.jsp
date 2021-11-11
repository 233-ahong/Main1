<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="layuimini-2/lib/layui-v2.6.3/css/layui.css" media="all">
    <!--[if lt IE 9]>
    <script src="/layuimini-2/zj/html5.min.js"></script>
    <script src="/layuimini-2/zj/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div style="width: 100%;height: 100%;margin-top:150px" class="layui-container layui-fluid">
    <h1 class="layedit-tool-active" style="text-align: center;font-size:40px;color: #1E9FFF">欢迎来到荐物娘</h1>
    <br/>
    <div style="width: 100%;display: table">
        <div style="width: 200px; margin: auto">
            <a href="${pageContext.request.contextPath}/admins/log">
                <button type="button" class="layui-btn layui-btn-fluid" style="align-content: center">登录</button>
            </a>
        </div>
    </div>
</div>


</body>
</html>