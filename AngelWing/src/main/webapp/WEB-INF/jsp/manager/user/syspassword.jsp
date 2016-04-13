<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/manager/user/css/syspassword.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/black/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/icon.css">
</head>
<body>
<table class="syspassword-table">
	<tr>
		<td>
			系统用户名：
		</td>
		<td>
			<input id="username" class="easyui-textbox" data-options="iconCls:'icon-edit'" style="width:300px">
		</td>
	</tr>
	<tr class="table-double">
		<td>
			系统旧密码：
		</td>
		<td>
			<input id="userpass" class="easyui-textbox" data-options="iconCls:'icon-edit'" style="width:300px">
		</td>
	</tr>
	<tr>
		<td>
			系统新密码：
		</td>
		<td>
			<input id="newpass1" type="password" class="easyui-textbox" data-options="iconCls:'icon-edit'" style="width:300px">
		</td>
	</tr>
	<tr class="table-double">
		<td>
			再次输入新密码：
		</td>
		<td>
			<input id="newpass2" type="password" class="easyui-textbox" data-options="iconCls:'icon-edit'" style="width:300px">
		</td>
	</tr>
</table>
<div class="syspassword-div">
	<a href="javascript:" id="saveButton" style="outline: none;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
	<a href="javascript:" id="clearButton" style="outline: none;" class="easyui-linkbutton" data-options="iconCls:'icon-clear'">撤销</a>
</div>
<input id="token" value="${token}" type="hidden"/>
<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$().ready(function(){
		
		var name = "${admin.userName}";
		$("#username").textbox("setValue", name);
		
		$("#saveButton").click(function(){
			var bool = checkNull();
			if(!bool){
				$.messager.alert("警告", "不能输入空信息", "info", function(){
					return false;
				});
			}else{
				bool = checkOldPassword();
				if(!bool){
					$.messager.alert("警告", "旧密码输入不正确，请重新输入", "info", function(){
						return false;
					});
				}else{
					bool = validataNewPassword();
					if(!bool){
						$.messager.alert("警告", "两次密码输入不一致", "info", function(){
							return false;
						});
					}else{
						submitForm();
					}
				}
			}
		});
		
		$("#clearButton").click(function(){
			$("#userpass").textbox("setValue", "");
			$("#newpass1").textbox("setValue", "");
			$("#newpass2").textbox("setValue", "");
		});
	});
	
	function submitForm(){
		var name = $("#username").textbox("getValue");
		var newpass1 = $("#newpass1").textbox("getValue");
		$.ajax({
			url : "<%=path%>/manager/user/updatepassword.html",
			type : "post",
			data : {
				"userName" : name,
				"userPassword" : newpass1,
				"token" : $("#token").val()
			},
			success : function(data){
				if(data == "success"){
					$.messager.alert("成功", "系统账户密码成功，为了安全，请重新登录", "info", function(){
						window.parent.location = "<%=path%>/logout.html";
					});
				}else{
					$.messager.alert("警告", "系统账户密码修改失败", "info", function(){
						return false;
					});
				}
			}
		});
	}
	
	function checkNull(){
		var name = $("#username").textbox("getValue");
		var oldpass = $("#userpass").textbox("getValue");
		var newpass1 = $("#newpass1").textbox("getValue");
		var newpass2 = $("#newpass2").textbox("getValue");
		if(name == ""){
			return false;
		}
		if(oldpass == ""){
			return false;
		}
		if(newpass1 == ""){
			return false;
		}
		return newpass2 != "";

	}
	
	function validataNewPassword(){
		var newPass1 = $("#newpass1").textbox("getValue");
		var newPass2 = $("#newpass2").textbox("getValue");
		return newPass1 == newPass2;
	}
	
	function checkOldPassword(){
		var password = $("#userpass").textbox("getValue");
		var bool;
		$.ajax({
			url : "<%=path%>/manager/user/checkpassword.html",
			type : "post",
			async : false,
			data : {
				"password" : password
			},
			success : function(data){
				bool = data == "success";
			}
		});
		return bool;
	}
</script>
</body>
</html>