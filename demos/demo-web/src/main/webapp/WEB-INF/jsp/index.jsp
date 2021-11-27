<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
</head>
<body>
    <a href="/loginController/login1?userName=zhaowenxue&passdWord=123456789">通过后端登录</a><br/>
    <a href="/userController/getUser">获得用户信息(并缓存)</a><br/>
    <a href="/userController/getUser/byCache/3">从缓存中获得用户的信息</a><br/>
    <a href="/userController/getStudent">获得学生表的信息(并缓存)</a><br/>
    <a href="/userController/findAllStuCache">获得学生表的信息(全查询)</a><br/>
    <a href="/userController/getStudent/byCache/00001">从缓存中获得学生信息</a><br/>
    <a href="/userController/test">测试读取配置信息里的参数(service)</a><br/>
    <a href="/userController/test1">测试读取配置信息里的参数(MyDataSource1--value)</a><br/>
    <a href="/userController/test2">测试读取配置信息里的参数(MyDataSource无value)</a><br/>
    <a href="/userController/test3">测试使用environment读取配置信息里的参数</a><br/>
    <a href="/loginController/toDistanceHtml" target="_blank" >去获得两点之间距离的页面</a><br/>
    <a href="/register.jsp">去注册页面</a>
</body>
</html>