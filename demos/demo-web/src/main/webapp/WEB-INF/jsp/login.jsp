
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<div align="center" style="position: relative">
    <br>
    <h2>登录界面</h2>
    <br>
    <form id="login" method="get" action="/loginController/login1?userName=zhaowenxue&passdWord=123456789">
        用户名: <input type="text" name="username" placeholder="请输入用户名" required/><br><br>
        密 码: <input type="text" name="password" placeholder="请输入密码" required/><br><br>
        <input type="submit" value="登录">
        <input type="button" value="注册" onclick="window.location.href='register.jsp'">
    </form>
</div>
</body>
<style>
    body{
        background-image: url("${pageContext.request.contextPath}../picture/天空.jpg");
        background-repeat:repeat;
        background-size: 100%;
    }
</style>
</html>
