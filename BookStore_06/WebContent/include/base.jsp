<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 所有页面要引入的资源，css，jQuery -->
<!-- base标签连接需要整成动态获取而不是指定的
http://localhost:8080/BookStore_02/
协议：//主机名：端口号/项目路径/
--><!-- http://localhost:8080/BookStore_02/ -->
<base href="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>