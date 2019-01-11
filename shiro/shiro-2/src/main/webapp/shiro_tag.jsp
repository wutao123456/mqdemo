<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/12/16
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>tag</title>
</head>
<body>
<h4>success page</h4>
welcome:<shiro:principal></shiro:principal>
<br/>
<shiro:hasRole name="user">
    <a href="user.jsp">to user</a><br/>
</shiro:hasRole>

<shiro:hasRole name="admin">
    <a href="admin.jsp">to admin</a><br/>
</shiro:hasRole>

<a href="/shiro/logout">logout</a>
</body>
</html>
