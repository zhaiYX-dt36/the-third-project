<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<center>
		<h1>俱乐部会员信息注册</h1>
		<form action="<%=basePath %>/update.shtml?mid=${member.mid}" method="post">
			<table width="300px" border="1px" cellspacing="0px" cellpadding="0px">				
				<tr>
					<th>姓名:</th>
					<th><input type="text" name="mname" value="${member.mname}"></th>
				</tr>
				<tr>
					<th>性别:</th>
					<th>						
						<input type="radio" name="mgender" value="1"> 男
						<input type="radio" name="mgender" value="0"> 女											
					</th>
				</tr>
				<tr>
					<th>年龄:</th>
					<th><input type="text" name="mage" value="${member.mage}"></th>
				</tr>
				<tr>
					<th>家庭住址:</th>
					<th><input type="text" name="address" value="${member.address}"></th>
				</tr>
				<tr>
					<th>E-mail:</th>
					<th><input type="text" name="memail" value="${member.memail}"></th>
				</tr>				
					<center>						
						<input type="submit" value="提交">
						<input type="reset" value="重置">
					</center>						
			</table>
		</form>
	</center>		
</body>	
</html>
