<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 用户未登录，且点击order detail得到的thisOrder也为空，显示如下菜单 -->
<c:if test="${empty user && empty thisOrder}">
	<div>
		<a class="head_href" href="pages/user/login.jsp">Login</a> | 
		<a class="head_href" href="pages/user/regist.jsp">Regist</a> &nbsp;&nbsp; 
		<a class="head_href" href="pages/cart/cart.jsp">Cart</a>
		<a class="head_href" href="pages/manager/manager.jsp">Backend</a>
	</div>
</c:if>
<!-- 用户未登录，且点击order detail得到的thisOrder不为空，显示如下菜单 -->
<c:if test="${empty user && !empty thisOrder}">
	<div>
	<!-- 管理员发送显示图书列表请求 -->
		<a class="head_href" href="manager/BookManagerServlet?method=page">Book Manager</a>
		<a class="head_href" href="manager/OrderManagerServlet?method=list">Order Manager</a>
		<a class="head_href" href="index.jsp">Homepage</a>
	</div>
</c:if>
<!-- 用户登录成功，显示如下菜单 -->
<c:if test="${!empty user }">
	<div>
		<span>Welcome<span class="um_span">${user.username }</span>to University Bookstore</span>
		<a class="head_href" href="pages/cart/cart.jsp">Cart</a>
		<a class="head_href" href="client/OrderClientServlet?method=list">My Orders</a>
		<a class="head_href" href="UserServlet?method=logout">Logout</a>&nbsp;&nbsp;
		<a class="head_href" href="index.jsp">Homepage</a>
	</div>
</c:if>