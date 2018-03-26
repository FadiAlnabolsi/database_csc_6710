<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<h3 align="center"> Blog Posts </h3>
	<table border="1" width="70%" align="center">
	<tr>
		<th>id</th>
		<th>subject</th>
		<th>description</th>
		<th>tag</th>
	</tr>
	<c:forEach items="${results}" var="result">
		<tr>
			<td><a href="<c:url value='/singleblogpost?blogid=${result[0]}'/>" target="body">${result[0]}</a></td> <!-- ID -->
			<td>${result[1]}</td> <!-- Subject -->
			<td>${result[2]}</td> <!-- Description-->
			<td>${result[3]}</td> <!-- Tag -->
		</tr>
	</c:forEach>
</table>
</body>
</html>
