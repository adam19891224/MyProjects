<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=path%>/resources/manager/resource/css/edit.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/black/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/icon.css">
</head>
<body>
<table class="text-table">
	<input type="hidden" id="resId" value="${resource.resourceId}"/>
	<tr class="tr-double">
		<td>资源名</td>
		<td><input id="resName" class="easyui-textbox" data-options="iconCls:'icon-edit', prompt:'请填写资源名称...'" style="width:230px;"></td>
	</tr>
	<tr>
		<td>资源路径</td>
		<td><input id="resUrl" class="easyui-textbox" data-options="iconCls:'icon-edit', prompt:'请填写资源路径...'" style="width:230px;"></td>
	</tr>
	<tr class="tr-double">
		<td>资源排序</td>
		<td><input id="resOrder" class="easyui-textbox" data-options="iconCls:'icon-edit', prompt:'请填写资源排序号...'" style="width:230px;"></td>
	</tr>
	<tr>
		<td>资源图标</td>
		<td><input id="resIcon" class="easyui-textbox" data-options="iconCls:'icon-edit', prompt:'资源图标...'" style="width:230px;"></td>
	</tr>
	<tr>
		<td>资源描述</td>
		<td><input id="resDesc" class="easyui-textbox" data-options="multiline:true, prompt:'资源描述...'" style="width:230px;height:100px"></td>
	</tr>
	<tr class="tr-double">
		<td colspan="2">
			<a href="#" onclick="update()" style="outline: none" class="easyui-linkbutton" data-options="iconCls:'icon-add'">保存</a>
			<a href="#" onclick="cancle()" style="outline: none" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
		</td>
	</tr>
</table>
<input type="hidden" id="token" value="${token}" />
<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$().ready(function(){
		var name = "${resource.resourceName}";
		var url = "${resource.resourceUrl}";
		var order = "${resource.resourceOrder}";
		var desc = "${resource.resourceDescription}";
		var icon = "${resource.resourceIcon}";
		
		$("#resName").textbox("setValue", name);
		$("#resUrl").textbox("setValue", url);
		$("#resOrder").textbox("setValue", order);
		$("#resDesc").textbox("setValue", desc);
		$("#resIcon").textbox("setValue", icon);
	});
	
	function update(){
		var resId = $("#resId").val();
		var reName = $("#resName").textbox('getValue');
		var reUrl = $("#resUrl").textbox('getValue');
		var resSort = $("#resOrder").textbox('getValue');
		var resDesc = $("#resDesc").textbox('getValue');
		var resIcon = $("#resIcon").textbox("getValue");
		
		if(reName == "" || reName == null || reName.match(" ")){
			alert("请填写资源名");
			return false;
		}
		$.ajax({
			url : "<%=path%>/manager/resource/update.html",
			type : "post",
			data : {
				"resourceName" : reName,
				"resourceUrl" : reUrl,
				"resourceOrder" : resSort,
				"resourceId" : resId,
				"resourceDescription" : resDesc,
				"resourceIcon" : resIcon,
				"token" : $("#token").val()
			},
			success : function(data){
				if(data == "success"){
					$.messager.alert("操作提示", "修改成功！", "info", function () {
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