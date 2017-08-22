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
		<h1>俱乐部会员信息</h1>
		<a href="<%=basePath%>/pages/admin/insert.jsp"><input type="button" value="添加会员"></a>
		<table width="600px" border="1px" cellspacing="0px" cellpadding="0px">
			<tr>
				<th>编号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>家庭住址</th>
				<th>E-mail</th>
				<th>操作</th>
			</tr>			
			<c:forEach items="${lists}" var="entry">
				<tr>
					<th>${entry.mid}</th>
					<th>${entry.mname}</th>
					<th>
						<c:if test="${entry.mgender==1}">男</c:if>
						<c:if test="${entry.mgender==0}">女</c:if>
					</th>
					<th>${entry.mage}</th>
					<th>${entry.address}</th>
					<th>${entry.memail}</th>
					<th>
						<a onclick="javasript:if(confirm('确定删除？')==false) return false;" href="<%=basePath%>/update_front.shtml?mid=${entry.mid}&mname=${entry.mname}&mgender=${entry.mgender}&mage=${entry.mage}&address=${entry.address}&memail=${entry.memail}">修改</a>
						<a onclick="javasript:if(confirm('确定删除？')==false) return false;" href="<%=basePath%>/delete.shtml?mid=${entry.mid}">删除</a>
					</th>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>	
</html>
