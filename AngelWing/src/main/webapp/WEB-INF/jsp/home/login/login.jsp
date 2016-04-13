<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="<%=path%>/favicon.ico" mce_href="<%=path%>/favicon.ico" type="image/x-icon">
<link rel="icon" href="<%=path%>/favicon.ico" mce_href="<%=path%>/favicon.ico" type="image/x-icon">  
<title>请登录!</title>
<link rel="stylesheet" href="<%=path%>/resources/home/login/css/login.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/black/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/easyui/themes/icon.css">
	<script type="text/javascript">
		if (window != top)
			top.location.href = location.href;
	</script>
</head>
<body>
	<div class="main">
		<div id="login-div" class="main-login">
			<div class="login-left">
				<div class="left-img">
					<img src="<%=path%>/resources/home/login/images/login-left.jpg" />
				</div>
			</div>
			<div class="login-right">
				<form id="login-form" action="">
					<div class="right-name">
						用&nbsp;&nbsp;&nbsp;户:&nbsp;&nbsp;<input type="text" id="userName" placeholder="请填写用户名" />
					</div>
					<div class="right-password">
						密&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;<input type="password" id="userPassword" placeholder="请填写密码" />
					</div>
					<div class="right-checkcode">
						验证码:&nbsp;&nbsp;<input style="width: 100px;" type="text" id="userCheckcode" placeholder="请填写验证码" />
						<div class="checkcode-div">
							<img src="<%=path%>/validate/getImage.html" onclick="changeValidateCode(this)" title="点击图片刷新验证码"/>
							<div class="checkcode-div-right">
								<img src="" id="checkCodeImg" />
							</div>
						</div>
					</div>
					<div class="right-button">
						<div id="login-button" class="button-div" data-url="<%=path%>/dologin.html">登&nbsp;&nbsp;录</div>
						<!-- <div class="button-div" data-url="<%=path%>/toRegister.html"></div> -->
					</div>
				</form>
			</div>
		</div>
	</div>
<!-- 引入loading的div -->
<%@ include file="/WEB-INF/jsp/common/loading.jsp" %>
<script type="text/javascript" src="<%=path%>/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$().ready(function(){
		$("#login-div").css({ 
			"margin-top" : ($(window).height() - $("#login-div").outerHeight())/2 + $(document).scrollTop() + "px" 
		});
		
		$("#login-button").click(function(){
			var $this = $(this);
			var userName = $("#userName").val();
			var userPassword = $("#userPassword").val();
			//用user的nick暂时代替验证码的输入
			var checkCode = $("#userCheckcode").val();
			
			//显示等待框和背景变灰的div
			$("#loading-div").css("display", "block");
			$("#loading-screen").css("display", "block");
			$.ajax({
				url : $this.attr("data-url"),
				type : "post",
				data : {
					"userName" : userName,
					"userPassword" : userPassword,
					"userNick" : checkCode
				},
				success : function(data){
					if(data == "success"){
						window.location = "main.html";
					}else if(data == "passwordError"){
						$("#loading-div").css("display", "none");
						$("#loading-screen").css("display", "none");
						$.messager.alert("错误！", "用户名或密码错误，请认真检查！", "error");
					}else if(data == "checkCodeError"){
						$("#loading-div").css("display", "none");
						$("#loading-screen").css("display", "none");
						$.messager.alert("错误！", "验证码输入错误，请重新输入或更换一张", "error");
					}else{
						window.location = "error.html";
					}
				},
				error : function(data){
					$("#loading-div").css("display", "none");
					$("#loading-screen").css("display", "none");
					alert(data);
				}
			});
			
		});
	});
	
	function changeValidateCode(obj){
		//获取当前的时间作为参数，无具体意义    
		var timenow = new Date().getTime();    
		//每次请求需要一个不同的参数，否则可能会返回同样的验证码    
		//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。    
		obj.src = "<%=path%>/validate/getImage.html?date=" + timenow;  
	}

	//监听回车
	$(".login-right").bind("keydown",function(e){
		// 兼容FF和IE和Opera
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
			//回车执行提交
			$("#login-button").click();
		}
	});

	//验证码文本框键盘松开事件
	$("#userCheckcode").on("keyup", function(e){
		checkIsOk($(this).val());
	})

	function checkIsOk(code){
		//验证输入正确性
		$.ajax({
			url : "<%=path%>/checkCode.html",
			data : {
				"code" : code
			},
			type : "get",
			success : function(data){
				data = eval("(" + data + ")");
				if(data){
					$("#checkCodeImg").attr("src", "<%=path%>/resources/home/login/images/login-yes.png");
				}else{
					$("#checkCodeImg").attr("src", "<%=path%>/resources/home/login/images/login-no.png");
				}
				$("#checkCodeImg").css("display", "block");
			}
		});
	}

</script>
</body>
</html>