<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/black/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/icon.css">
</head>
<body class="easyui-layout">
<div id="north" region="north" border="false" style="padding: 5px; height: 20px;">
</div>
<div id="content" border="false" region="center" style="width: 100%">
    <table id="datagrid" title="博文列表" toolbar="#tb" class="easyui-datagrid" fitColumns="true" style="width: 100%;" pagination="true"
           fit="true" rownumbers="true" singleSelect="true">
        <thead>
            <tr>
                <th align="center" field="articleTitle" width="25%">博客名称</th>
                <th align="center" field="articleDescription" width="20%">博客简介</th>
                <th align="center" field="createDate" width="19%" formatter="dateFormatter">创建时间</th>
                <th align="center" field="roleDescription" width="25%">评论数量</th>
            </tr>
        </thead>
    </table>
    <div id="tb">
        <a href="javascript:" style="outline: none;" onclick="add();" class="easyui-linkbutton"
           data-options="iconCls:'icon-add'">添加博客</a>
        <a href="javascript:" style="outline: none;" onclick="update();" class="easyui-linkbutton"
           data-options="iconCls:'icon-edit'">修改博客</a>
        <a href="javascript:" style="outline: none;" onclick="deletes();" class="easyui-linkbutton"
           data-options="iconCls:'icon-remove'">删除博客</a>
    </div>
</div>
<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/easyui-formatter.js"></script>
<script type="text/javascript">
    var jsonUrl = "<%=path%>/applications/article/getJson.html";
    var addUrl = "<%=path%>/applications/article/add.html";
    var updateUrl = "<%=path%>/applications/article/edit.html";
    var deleteUrl = "<%=path%>/applications/article/deletes.html";

    $().ready(function () {
        $("#datagrid").datagrid({
            url: jsonUrl
        });

    });

    function queryParam() {
        var query = {}
    }

    function reloadData() {
        $("#datagrid").datagrid("reload");
    }

    function add(){
        window.location = addUrl;
    }

</script>
</body>
</html>