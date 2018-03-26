<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
<%This jsp shows your followers and your followings, and allow you to add following, and search the current user"%>--%>


<%@ page import="user.dao.UserDao" %>
<%@ page import="user.domain.User" %>

<%@ page import="java.sql.*, com.mysql.jdbc.Driver"%>

<%@ page import="com.mysql.*" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>Login</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>

  <body>
  <h1>Show current following and followers</h1>

  <form action="<c:url value='/SearchFollow'/>" method="post">
  <%
    String dbName = "sampledb2";
    String userName = "john";
    String password = "pass1234";
    String hostname = "sampledb2.cpvy4fmhbooi.us-west-2.rds.amazonaws.com";
    String port = "3306";

    Connection con = null;
    PreparedStatement ps = null;

    UserDao userdao = new UserDao();
    User user1 = null;

    user1 = (User)session.getAttribute("session_user");
    String username = user1.getUsername();

    try
    {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      con = DriverManager.getConnection(
        "jdbc:mysql://" + hostname + ":" + port + "/" + dbName, userName, password);

      String sql = "SELECT following_name FROM user_follows WHERE follower_name = ?";
      ps = con.prepareStatement(sql);
      ps.setString(1,username);
      ResultSet rs = ps.executeQuery();
      %>

      <p>Your Following List :
        <select name="following2" id="following2">
          <%
            while(rs.next())
            {
              String fname = rs.getString("following_name");
          %>
          <option value="<%=fname %>"><%=fname %></option>
          <%
            }
          %>
      </select>
    </p>
    <%
      }
    catch(SQLException sqe)
    {
      out.println(sqe);
    }

    try
    {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      con = DriverManager.getConnection(
        "jdbc:mysql://" + hostname + ":" + port + "/" + dbName, userName, password);

      String sql = "SELECT follower_name FROM user_follows WHERE following_name = ?";
      ps = con.prepareStatement(sql);
      ps.setString(1,username);

      ResultSet rs = ps.executeQuery();
    %>

    <p>Your Follower List :
    <select name="follower2" id="follower2">
    <%
      while(rs.next())
      {
      String fname = rs.getString("follower_name");
    %>
    <option value="<%=fname %>"><%=fname %></option>
    <%
      }
    %>
    </select>
    </p>

    <%
      }
      catch(SQLException sqe)
      {
        out.println(sqe);
      }

      try
      {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection(
          "jdbc:mysql://" + hostname + ":" + port + "/" + dbName, userName, password);

        String sql = "SELECT * FROM user where not username=?";
        ps = con.prepareStatement(sql);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
    %>

    Search the user: <input type="submit" value="Search">

    <p>Choose a person to follow :
    <select name="cand" id="cand">
    <%
      while(rs.next())
      {
        String fname = rs.getString("username");
    %>
    <option value="<%=fname %>"><%=fname %></option>
    <%
      }
    %>
    </select>
    </p>
    <%
      }
      catch(SQLException sqe)
      {
        out.println(sqe);
      }
    %>
    </form>
    <form action="<c:url value='/AddFollowing'/>" method="post">
      Enter the name whom you want to follow <input type="text" name="following">
      <input type="submit" value="Add">
    </form>

    <a href="<c:url value='/jsps/top.jsp'/>" target="_parent">Go back to Menu</a>&nbsp;&nbsp;
  </body>
</html>
