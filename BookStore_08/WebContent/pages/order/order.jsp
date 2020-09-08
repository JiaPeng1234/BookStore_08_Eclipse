<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Orders</title>
<%@include file="/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.png" width="20%" >
			<span class="wel_word">My Orders</span>
			<%@include file="/include/user-info.jsp" %>
	</div>
	
	<div id="main">
		<c:if test="${empty myOrders }">
			<h1>Your don't have any orders yet. <a href="index.jsp">Go checkout our bookstore!</a></h1>
		</c:if>
		<c:if test="${!empty myOrders }">
			<table>
				<tr>
					<td>OrderId</td>
					<td>Date</td>
					<td>Total</td>
					<td>Status</td>
					<td>Detail</td>
				</tr>
				<c:forEach items="${myOrders }" var="order">
					<tr>
						<td>${order.orderId }</td>
						<td>${order.createDate }</td>
						<td>$${order.totalMoney }</td>
						<td>
							<c:choose>
								<c:when test="${order.status == 0 }">
									Unshipped
								</c:when>
								<c:when test="${order.status == 1 }">
									<a style="font-size: 16px" href="client/OrderClientServlet?method=receive&orderId=${order.orderId }" >Confirm receipt</a> 
								</c:when>
								<c:when test="${order.status == 2 }">
									Delivered
								</c:when>
							</c:choose>
						</td>
						<td><a href="client/OrderClientServlet?method=detail&orderId=${order.orderId }">Details</a></td>
					</tr>	
				</c:forEach>
			</table>
		</c:if>	
		
	
	</div>
	
	<div id="bottom">
		<span>
			PengBookStore.Copyright &copy;2020
		</span>
	</div>
</body>
</html>