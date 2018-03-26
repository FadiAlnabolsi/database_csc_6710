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
			<th>date</th>
		</tr>
		<tr>
			<td>${blog[0]}</a></td> <!-- ID -->
			<td>${blog[1]}</td> <!-- Subject -->
			<td>${blog[2]}</td> <!-- Description-->
			<td>${blog[3]}</td> <!-- Description-->
		</tr>
	</table>

	<h3 align="center">Comments </h3>
	<table border="1" width="70%" align="center">
		<tr>
			<th>Name</th>
			<th>Subject</th>
			<th>Description</th>
		</tr>
		<c:forEach items="${comments}" var="comment">
			<tr>
				<td>${comment[0]}</td> <!-- Username -->
				<td>${comment[1]}</td> <!-- Subject -->
				<td>${comment[2]}</td> <!-- Description-->
			</tr>
		</c:forEach>
	</table>

	<h3 align="center">Leave Comment </h3>
	<table border="1" width="70%" align="center">
		<tr>
			<th>Subject</th>
			<th>Description</th>
			<th></th>
		</tr>
		<form action="<c:url value='/addCommentToBlog?blogId=${blog[0]}&username=${sessionScope.session_user.username}'/>" method="post">
			<td><input type="text" name="subject" /></td> <!-- Subject -->
			<td><input type="text" name="description" /></td> <!-- description -->
			<td><input type="submit" value="add comment"/></td>
		</form>
	</table>
</body>
</html>
