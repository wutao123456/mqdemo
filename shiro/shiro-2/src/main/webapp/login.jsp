<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/12/16
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>login page</h4>
<form action="/shiro/login" method="post">
    username:<input type="text" name="username">
    password:<input type="password" name="password">
    <input type="submit" name="submit">
</form>
</body>
</html>
