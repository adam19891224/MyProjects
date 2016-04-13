<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="<%=path%>/favicon.ico" mce_href="<%=path%>/favicon.ico" type="image/x-icon">
<link rel="icon" href="<%=path%>/favicon.ico" mce_href="<%=path%>/favicon.ico" type="image/x-icon">  
<title>你好!</title>
<link rel="stylesheet" href="<%=path%>/resources/home/main/css/main.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/black/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/icon.css">
</head>
<body>
	<div class="easyui-layout" style="width:100%; height:100%;">
		<div id="north" region="north" style="padding: 5px; height: 100px;">
			<div id="north-left">
			</div>
			<div id="north-right">
				<div id="right-left">
				</div>
				<div id="right-right">
					<span>当前用户：  ${user.userName}</span>
					<span><a href="javascript:" onclick="logout()">登出</a></span>
				</div>
			</div>
		</div>
		<div id="west" region="west" split="true" title="菜单导航" style="width: 15%;">
			<div id="easyui-accord" class="easyui-accordion" data-options="fit: true, border: false">
				<%-- 
				<div title="About Accordion" iconCls="icon-ok" style="overflow:auto;padding:10px;">
					<a href="javascript:addTab('百度','http://www.baidu.com','icon-search')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" style="width: 100%; outline: none;">百度</a>
					<a href="javascript:addTab('百度','http://www.baidu.com','icon-search')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" style="width: 100%; outline: none;">百度</a>
					<a href="javascript:addTab('百度','http://www.baidu.com','icon-search')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" style="width: 100%; outline: none;">百度</a>
				</div>
				--%>
			</div>
		</div>
		<div id="content" region="center">
			<div id="main-window" class="easyui-tabs" fit="true" border="false"></div>
		</div>
		<div id="south" region="south" style="padding:5px; height: 25px;">
		</div>
	</div>
<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$().ready(function(){
		$.getJSON("<%=path%>/loadWest.html", function(data) {
			data = eval("(" + data + ")");
			for(var a = 0; a < data.length; a++){
				var dom = data[a];
				var sunDiv = "";
				var sunList = dom.sunList;
				for(var b = 0; b < sunList.length; b++){
					var sun = sunList[b];
					sunDiv += "<a href=\"javascript:addTab('" + sun.menuName + "','<%=path%>" + sun.menuURL + "','" + sun.menuIcon + "')\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'" + sun.menuIcon + "'\" style=\"width: 100%; outline: none;\">" + sun.menuName + "</a>";
				}
				$("#easyui-accord").accordion('add',{
                    title: dom.menuName,
                    content: sunDiv,
                    selected : false,
					iconCls : "icon-search"
                });
			}
		});
	});
	function addTab(title, url, icon){
		if ($('#main-window').tabs('exists', title)){
			$('#main-window').tabs('select', title);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
			$('#main-window').tabs('add',{
				title : title,
				content : content,
				iconCls : icon,
				closable : true
			});
		}
	}
	
	function logout(){
		$.messager.confirm('退出','是否真的确定要退出系统？',function(r){
		    if(r){
		        window.location = "<%=path%>/logout.html";
		    }
		});
	}
</script>
</body>
</html>