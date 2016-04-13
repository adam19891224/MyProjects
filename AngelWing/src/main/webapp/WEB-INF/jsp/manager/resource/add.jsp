<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/include.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/resources/manager/resource/css/add.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/black/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/icon.css">
</head>
<body>
<table class="text-table">
	<input type="hidden" id="parentId" dataName="${resource.resourceName}" value="${resource.resourceId}"/>
	<tr>
		<td>父资源</td>
		<td><input id="parentName" class="easyui-textbox" readonly="true" data-options="iconCls:'icon-ok'" style="width:180px;"></td>
	</tr>
	<tr class="tr-double">
		<td>资源名</td>
		<td><input id="resName" class="easyui-textbox" data-options="iconCls:'icon-edit', prompt:'请填写资源名称...'" style="width:180px;"></td>
	</tr>
	<tr>
		<td>资源路径</td>
		<td><input id="resUrl" class="easyui-textbox" data-options="iconCls:'icon-edit', prompt:'请填写资源路径...'" style="width:180px;"></td>
	</tr>
	<tr class="tr-double">
		<td>资源排序</td>
		<td><input id="resOrder" class="easyui-textbox" data-options="iconCls:'icon-edit', prompt:'请填写资源排序号...'" style="width:180px;"></td>
	</tr>
	<tr>
		<td>资源图标</td>
		<td><input id="resIcon" class="easyui-textbox" data-options="iconCls:'icon-edit', prompt:'资源图标...'" style="width:180px;"></td>
	</tr>
	<tr>
		<td>资源描述</td>
		<td><input id="resDesc" class="easyui-textbox" data-options="multiline:true, prompt:'资源描述...'" style="width:180px;height:100px"></td>
	</tr>
	<tr class="tr-double">
		<td colspan="2">
			<a href="#" onclick="save()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" style="outline: none;">保存</a>
			<a href="#" onclick="cancle()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="outline: none;">撤销</a>
		</td>
	</tr>
</table>
<input type="hidden" id="token" value="${token}" />
<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

	$().ready(function(){
		
		var parName = $("#parentId").attr("dataName");
		if(parName != ""){
			$("#parentName").textbox("setValue", parName);
		}else{
			$("#parentName").textbox("setValue", "总资源");
		}
	});

	function save(){
		var parId = $("#parentId").val();
		var parName = $("#parentName").textbox("getValue");
		var reName = $("#resName").textbox('getValue');
		var reUrl = $("#resUrl").textbox('getValue');
		var resDesc = $("#resDesc").textbox('getValue');
		var resSort = $("#resOrder").textbox('getValue');
		var resIcon = $("#resIcon").textbox('getValue');
		var resourceType = "1";
		if(parName == "" || parName == null || parName.match(" ")){
			alert("请选择父资源");
			return false;
		}
		if(reName == "" || reName == null || reName.match(" ")){
			alert("请填写资源名");
			return false;
		}
		if(reUrl == "" || reUrl == null || reUrl.match(" ")){
			alert("请填写资源路径");
			return false;
		}
		if(parId != ""){
			resourceType = "2";
		}
		$.ajax({
			url : "<%=path%>/manager/resource/save.html",
			type : "post",
			data : {
				"resourceName" : reName,
				"resourceUrl" : reUrl,
				"parentResourceId" : parId,
				"resourceDescription" : resDesc,
				"resourceOrder" : resSort,
				"resourceType" : resourceType,
				"resourceIcon" : resIcon,
				"token" : $("#token").val()
			},
			success : function(data){
				if(data == "ok"){
					$.messager.alert("操作提示", "操作成功！", "info", function () {
						reloadMain(self);
			        });
				}else{
					$.messager.alert("操作提示", data);
				}
			}
		});
	}
	
	function cancle(){
		$(".easyui-textbox").textbox('setValue', "");
	}
</script>
</body>
</html>