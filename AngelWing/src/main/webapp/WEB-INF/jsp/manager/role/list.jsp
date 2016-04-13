<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
	<table id="datagrid" title="角色列表" class="easyui-datagrid" fitColumns="true" style="width: 100%;" pagination="true" fit="true" rownumbers="true" singleSelect="true">
	    <thead>
	        <tr>
	            <th align="center" field="roleName" width="25%" >角色名</th>
	            <th align="center" field="createDate" width="20%" formatter="dateFormatter">创建时间</th>
	            <th align="center" field="roleShiro" width="19%" >角色权限</th>
	            <th align="center" field="roleDescription" width="25%" >角色描述</th>
	            <th align="center" field="isDelete" width="10%" formatter="boolFormatter">角色是否可用</th>
	        </tr>
	    </thead>
	</table>
	<div id="tb">
		<a href="#" style="outline: none;" onclick="add();" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加角色</a>
		<a href="#" style="outline: none;" onclick="update();" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改角色</a>
		<a href="#" style="outline: none;" onclick="deletes();" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除角色</a>
	</div>
</div>
<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/easyui-formatter.js"></script>
<script type="text/javascript">
	var jsonUrl = "<%=path%>/manager/role/getJson.html";
	var addUrl = "<%=path%>/manager/role/add.html";
	var updateUrl = "<%=path%>/manager/role/edit.html";
	var deleteUrl = "<%=path%>/manager/role/deletes.html";

	$().ready(function(){
		
		$("#datagrid").datagrid({
			url : jsonUrl
		});
	});
	
	function queryParam(){
		var query = {
			
		}
	}
	
	function reloadData(){
		$("#datagrid").datagrid("reload");
	}
	
	function deletes(){
		var ids = "";
		var rows = $("#datagrid").datagrid("getSelections");
		if(rows.length < 1){
			$.messager.alert("警告", "请至少选择一行数据", "warning");
			return;
		}
		for(var i=0; i < rows.length; i++){
			ids += rows[i].roleId + ",";
		}
		$.ajax({
			url : deleteUrl,
			type : "post",
			data : {
				"ids" : ids
			},
			success : function(data){
				if(data == "success"){
					$.messager.alert("提示", "操作成功！", "info", function () {
						reloadData();
			        });
				}else{
					$.messager.alert("错误", "删除错误，请联系管理员", "error");
				}
			},
			error : function(data){
				alert(data);
			}
		})
	}
</script>
</body>
</html>