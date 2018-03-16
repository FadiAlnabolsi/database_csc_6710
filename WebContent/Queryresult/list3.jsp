<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--
	This list.jsp gets user list who satisfy the follower and following requirement, then creates a table which shows the user information
	-->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<h3 align="center"> The information of users who satisfy the following conditions </h3>
	<table border="1" width="70%" align="center">
	<tr>
		<th>username</th>
		<th>email</th>

		
	</tr>
<c:forEach items="${UserFollowList}" var="user">
	<tr>
		<td>${user.username }</td>
		<td>${user.email }</td>

	</tr>
</c:forEach>
</table>
</body>
</html>