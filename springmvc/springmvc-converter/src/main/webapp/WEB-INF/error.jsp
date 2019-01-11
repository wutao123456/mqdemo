<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/12/8
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>error</title>
</head>
<body>
<!--jsp页面isELIgnored默认为true,所以接受不到model传过来的值,只需将isELIgnored设为false即可-->
<h4>Error Page</h4>
<p>${message}  ${exception}</p>
</body>
</html>
