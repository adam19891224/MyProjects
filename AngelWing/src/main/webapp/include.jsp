<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">
	function reloadMain(window){
		if(window != top){
			reloadMain(window.parent);
		}
		window.location.reload();
	}
</script>