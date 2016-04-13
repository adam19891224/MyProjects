<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="<%=path%>/resources/manager/resource/css/main.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/black/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/icon.css">
</head>
<body>
<div class="easyui-layout" style="width:100%; height:100%;">
    <div id="west" region="west" split="true" border="true" style="width: 20%;">
        <ul id="resource-tree" class="easyui-tree"></ul>
    </div>
    <div id="content" region="center" split="true" border="false">
        <div id="my-pannel" class="easyui-panel" style="width:100%;height:100%;padding:10px;background:#fafafa;">
        </div>
    </div>
</div>
<!-- 右键菜单 -->
<div id="right-menu" class="easyui-menu" style="width:120px;">
    <div id="addRes" data-url="<%=path%>/manager/resource/add.html" data-options="iconCls:'icon-add'">添加资源</div>
    <div id="updRes" data-url="<%=path%>/manager/resource/edit.html" data-options="iconCls:'icon-edit'">修改资源</div>
    <div id="delRes" data-options="iconCls:'icon-remove'">删除资源</div>
</div>
<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    $().ready(function () {
        $('#resource-tree').tree({
            url: "<%=path%>/manager/resource/getResouces.html",
            onContextMenu: function (e, node) {
                e.preventDefault();
                //同时让当前右键菜单对应的tree的节点变成选中状态,这样右键菜单的onclick就能获取选中的tree节点的内容了
                $(this).tree('select', node.target);

                //初始化右键菜单，移除所有的display none状态
                $("#right-menu").find("div").each(function () {
                    $(this).css("display", "block");
                });
                if (node.resourceType == "0") {
                    //0说明是总资源，则此时右键菜单只有添加资源的选项
                    $("#delRes").css("display", "none");
                    $("#updRes").css("display", "none");
                }
                if (node.resourceType == "2") {
                    //说明是二级资源，则此时不允许添加资源
                    $("#addRes").css("display", "none");
                }
                $("#right-menu").menu('show', {
                    left: e.pageX,
                    top: e.pageY,
                    onClick: function (item) {
                        if (item != null) {
                            getOption(item, node);
                        }
                    }
                });
            }
        });
    });
    //执行操作的方法
    function getOption(item, node) {
        var id = item.id;
        if (id == "addRes") {
            var url = $("#" + id).attr("data-url");
            var nodeId = node.id;
            url += "?id=" + nodeId;
            addIframe("添加资源", url);
        }
        if (id == "updRes") {
            var url = $("#" + id).attr("data-url");
            var nodeId = node.id;
            url += "?id=" + nodeId;
            addIframe("修改资源", url);
        }
        if (id == "delRes") {
            if (node.resourceType == "1") {
                $.messager.confirm("删除确认", "请注意，您当前选中的是一级资源，该操作会把所有该资源下的子资源同时删除，是否确定这样做？", function (r) {
                    if (r) {
                        deletes(node.id);
                    }
                });
            } else {
                $.messager.confirm("删除确认", "是否确定删除？", function (r) {
                    if (r) {
                        deletes(node.id);
                    }
                });
            }
        }
    }

    function addIframe(title, url) {
        var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
        $('#my-pannel').panel({
            content: content,
            title: title
        });
    }

    function deletes(id) {
        $.ajax({
            url: "<%=path%>/manager/resource/delete.html",
            type: "post",
            data: {
                "id": id
            },
            success: function (data) {
                if (data == "success") {
                    $.messager.alert("操作提示", "操作成功！", "info", function () {
                        reloadMain(self);
                    });
                }
            }
        })
    }
</script>
</body>
</html>