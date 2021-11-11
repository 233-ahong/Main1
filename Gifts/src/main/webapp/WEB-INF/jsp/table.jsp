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
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">用户昵称</label>
                            <div class="layui-input-inline">
                                <input type="text" name="nick" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">用户性别</label>
                            <div class="layui-input-inline">
                                <input type="text" name="sex" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">用户sessionKey</label>
                            <div class="layui-input-inline">
                                <input type="text" name="sessionKey" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button type="submit" class="layui-btn layui-btn-primary" lay-submit
                                    lay-filter="data-search-btn"><i class="layui-icon"></i> 搜 索
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加</button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除</button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
        </script>

    </div>
</div>
<script src="../../layuimini-2/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render({
            height: 550,
            method: 'post',
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admins/tableByPage',
            toolbar: '#toolbarDemo',
            request: {
                pageName: 'page', //页码的参数名称，默认：page
                limitName: 'rows' //每页数据量的参数名，默认：limit
            },
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'nick', width: 80, title: '用户名'},
                {field: 'sex', width: 80, title: '性别', sort: true},
                {field: 'city', width: 80, title: '城市'},
                {field: 'sign', title: '签名', width: 80},
                {field: 'experience', width: 80, title: '积分', sort: true},
                {field: 'sessionKey', width: 80, title: 'sessionKey', sort: true},
                {field: 'imgUrl', width: 80, title: '头像地址'},
                {field: 'openId', width: 135, title: 'OpenID', sort: true},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 25, 50, 100],
            limit: 15,
            page: true,
            skin: 'line'
        });

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            var result = JSON.stringify(data.field);
            console.log(result);
            layer.alert(result, {
                title: '最终的搜索信息'
            });

            //执行搜索重载
            table.reload('currentTableId', {
                page: {
                    curr: 1
                },
                method: 'post',
                where: {
                    nick: data.field.nick,
                    sex: data.field.sex,
                    sessionKey: data.field.sessionKey
                },
                url:'${pageContext.request.contextPath}/admins/tableByPage',
                data:JSON.stringify(data.field),
            }, 'data');

            return false;
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加用户',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['80%', '80%'],
                    content: '${pageContext.request.contextPath}/admins/add',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                layer.alert(JSON.stringify(data),{
                    title:'确定要删除下列用户吗？'
                },function () {
                    console.log(data.data);
                    $.ajax({
                        url: "${pageContext.request.contextPath}/admins/delList",
                        type: "POST",
                        data: JSON.stringify(data),
                        dataType: "json",
                        contentType: "application/json",
                        success:function (res) {
                            if (res>"0"){
                                layer.msg("删除成功,共删除"+res+"条记录", {icon: 6});
                            }else {
                                layer.msg("删除失败", {icon: 5});
                            }
                        }
                })
                });

            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                var index = layer.open({
                    title: '编辑用户',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['80%', '80%'],
                    content: '${pageContext.request.contextPath}/edit.jsp',
                    success: function (layero,index) {
                        var body = layer.getChildFrame('body', index);
                        body.find("[name='openId']").val(data.openId);
                        obj.update({
                            sex:data.sex,
                        });
                        body.find("[name='sessionKey']").val(data.sessionKey);
                        body.find("[name='imgUrl']").val(data.imgUrl);
                        body.find("[name='nick']").val(data.nick);
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                var index = layer.alert(JSON.stringify(obj.data), {
                        title: '确定要删除该用户吗？'
                    },function () {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/admins/delLoginUser",
                            type: "POST",
                            data: data.openId,
                            dataType: "json",
                            contentType: "application/json",
                            success: function (data) {
                                if (data == "1") {
                                    obj.del();
                                    //关闭弹框
                                    layer.close(index);
                                    layer.msg("删除成功", {icon: 6});
                                } else {
                                    layer.msg("删除失败", {icon: 5});
                                }
                            }
                        })
                })
            }
        });

    });
</script>

</body>
</html>
