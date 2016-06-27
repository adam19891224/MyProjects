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
    <table id="treegrid" toolbar="#tb" title="博文类目" class="easyui-treegrid" style="width:100%">
    </table>
    <div id="tb">
        <a href="javascript:" style="outline: none;" onclick="save();" class="easyui-linkbutton"
           data-options="iconCls:'icon-save'">保存类目</a>
        <a href="javascript:" style="outline: none;" onclick="update();" class="easyui-linkbutton"
           data-options="iconCls:'icon-edit'">修改类目</a>
        <a href="javascript:" style="outline: none;" onclick="reloadData();" class="easyui-linkbutton"
           data-options="iconCls:'icon-reload'">刷新</a>
    </div>
    <!-- 右键菜单 -->
    <div id="right-menu" class="easyui-menu" style="width:120px;">
        <div id="appRes" data-options="iconCls:'icon-add'">追加子资源</div>
        <div id="updRes" data-options="iconCls:'icon-edit'">修改资源</div>
        <div id="delRes" data-options="iconCls:'icon-remove'">删除资源</div>
    </div>
</div>
<div id="addNewDialog" class="easyui-dialog" title="添加新类型" style="width:400px;height:auto;"
     data-options="iconCls:'icon-save',resizable:true,modal:true">
    <div id="getAddTitle" style="width: 360px; height: 50px; line-height: 50px; margin-left: 10px; float:left;">
    </div>
    <div id="getAddMain" style="width: 360px; height: 50px; margin-left: 10px; float:left;">
        类别名称: <input id="getTypeName" class="easyui-textbox" data-options="iconCls:'icon-add'" style="width:260px">
    </div>
    <div id="getAddButton" style="width: 360px; height: 50px; margin-left: 10px; float:left;">
        <input type="hidden" id="getParentId" >
        <a id="addButton" href="javascript:" style="float:right;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提交</a>
    </div>
</div>
<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/easyui-formatter.js"></script>
<script type="text/javascript">
    var jsonUrl = "<%=path%>/applications/article/type/getJson.html";
    var saveUrl = "<%=path%>/applications/article/type/add.html";
    var updateUrl = "<%=path%>/applications/article/type/add.html";
    var deleteUrl = "<%=path%>/applications/article/deletes.html";

    $().ready(function () {
        $("#treegrid").treegrid({
            url : jsonUrl,
            rownumbers : true,
            idField : 'id',
            treeField : 'text',//需要展示的列，这里的树形结构需要展示的是text列
            columns:[[
                {title:'类别名称', field:'text', width:500, align:'center', editor:'textbox'}
            ]],
            onContextMenu: function (e, node) {
                e.preventDefault();
                //同时让当前右键菜单对应的tree的节点变成选中状态,这样右键菜单的onclick就能获取选中的tree节点的内容了
                $(this).treegrid('select', node.id);

                //初始化右键菜单，移除所有的display none状态
                $("#right-menu").find("div").each(function () {
                    $(this).css("display", "block");
                });
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

        $('#addNewDialog').dialog("close");

        $("#addButton").click(function(){
            save();
        });
    });
    //执行操作的方法
    function getOption(item, node) {
        var id = item.id;
        if (id == "updRes") {
            edit();
        }
        if (id == "delRes") {
            var children = $('#treegrid').treegrid('getChildren', node.id);
            if (children && children.length > 0) {
                $.messager.alert('警告','当前资源是一个父级资源，请先删除它的所有子资源!','error');
            } else {
                if(node.id = "000"){
                    $.messager.alert('警告','当前资源是初始化资源，不能删除!','error');
                    return false;
                }
                $.messager.confirm("删除确认", "是否确定删除？", function (r) {
                    if (r) {
                        deletes(node.id);
                    }
                });
            }
        }
        if(id == "appRes"){
            append();
        }
    }

    function reloadData() {
        $("#treegrid").treegrid("reload");
    }

    var editingId = "";
    function edit(){
        if(editingId != ""){
            $('#treegrid').treegrid('cancelEdit', editingId);
        }
        var row = $('#treegrid').treegrid('getSelected');
        if (row){
            editingId = row.id;
            $('#treegrid').treegrid('beginEdit', editingId);
        }
    }

    function update(){
        var row = $('#treegrid').treegrid('getEditor', {index:editingId,field:"text"});
        var text = $(row.target).combobox('getText');
        var obj = {};
        obj.typeId = editingId;
        obj.typeName = text;
        $.ajax({
            url : "<%=path%>/applications/article/type/update.html",
            type : "post",
            data : obj,
            success : function(data){
                if(data == "success"){
                    $.messager.alert('信息','修改成功!','info');
                    $("#treegrid").treegrid("reload");
                }else{
                    $('#treegrid').treegrid('cancelEdit', editingId);
                    $.messager.alert('信息','修改失败!','error');
                }
                editingId = "";
            }
        });
    }

    function append(){
        var row = $('#treegrid').treegrid('getSelected');
        if(row){
            var res = getJSON(row);
            $('#getAddTitle').html(res);
            $("#getParentId").val(row.id);
            $("#getTypeName").textbox("setValue", "");
            $('#addNewDialog').window("open");
        }
    }

    function getJSON(node){
        var parent = $('#treegrid').treegrid('getParent', node.id);
        var res = "";
        if(parent){
            res = getJSON(parent);
        }
        res += "<span>" + node.text + "</span> >> ";
        return res;
    }

    function save(){
        var parentId = $("#getParentId").val();
        var name = $("#getTypeName").textbox("getValue");
        var obj = new Object();
        obj.typeName = name;
        obj.typeParentId = parentId;
        $.ajax({
            url : "<%=path%>/applications/article/type/save.html",
            type : "post",
            data : obj,
            success : function(data){
                $('#addNewDialog').dialog("close");
                if(data == "success"){
                    $.messager.alert('信息','添加成功!','info');
                    $("#treegrid").treegrid("reload");
                }else{
                    $.messager.alert('信息','添加失败!','error');
                }
                editingId = "";
            }
        });
    }

    function deletes(id){
        $.ajax({
            url : "<%=path%>/applications/article/type/delete.html",
            type : "post",
            data : {
                "id" : id
            },
            success : function(data){
                if(data == "success"){
                    $.messager.alert('信息','删除成功!','info');
                    $("#treegrid").treegrid("reload");
                }else{
                    $.messager.alert('信息','删除失败!','error');
                }
                editingId = "";
            }
        });
    }

</script>
</body>
</html>