<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String context = request.getContextPath();// /dt41_javaweb2
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+context+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="resources/js/jquery-1.4.2.min.js"></script>
<title>首页</title>
</head>
<body>
	<script type="text/javascript">
		window.location.href="<%=basePath%>/pages/admin/show_front.jsp";	
	</script>
</body>	
</html>
