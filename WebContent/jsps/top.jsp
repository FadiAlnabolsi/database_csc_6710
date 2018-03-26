<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		background: #4682B4;
	}
	a {
		text-transform:none;
		text-decoration:none;
	}
	a:hover {
		text-decoration:underline;
	}
</style>
  </head>

  <body>
<h1 style="text-align: center;">CSC6710 Project1</h1>
<div style="font-size: 10pt;">
	<c:choose>
		<c:when test="${empty sessionScope.session_user }">
			<ul>
				<li><a href="<c:url value='/jsps/user/login.jsp'/>" target="body">Login</a></li>
				<li><a href="<c:url value='/jsps/user/regist.jsp'/>" target="body">Register</a></li>
				<li><a href="<c:url value='/jsps/user/initiatetable.jsp'/>" target="_parent">Initialize Database</a>
				</li>
			</ul>
		</c:when>
		<c:otherwise>
			Hello：${sessionScope.session_user.username }<br />
			<ul>
				<li><a href="<c:url value='/jsps/menu.jsp'/>" target="body">Go to Menu</a></li>
				<li><a href="<c:url value='/jsps/user/login.jsp'/>" target="body">Logout</a></li>
				<li><a href="<c:url value='/jsps/item2.jsp'/>" target="body">Show all other users</a></li>
				<li><a href="<c:url value='/jsps/item.jsp'/>" target="body">Query Result</a></li>
				<li><a href="<c:url value='/jsps/tags/searchtags.jsp'/>" target="body">Search Blogs Via Tags</a></li>
				<li><a href="<c:url value='/jsps/user/newFile.jsp'/>" target="body">Insert New Blog</a></li>
			</ul>
		</c:otherwise>
	</c:choose>

</div>
  </body>
</html>
