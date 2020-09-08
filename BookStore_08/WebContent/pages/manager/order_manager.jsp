<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Management System</title>
<%@include file="/include/base.jsp" %>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.png" width="20%" >
			<span class="wel_word">Order Management</span>
			<%@include file="/include/book-manager.jsp" %>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>OrderId</td>
				<td>Date</td>
				<td>Total</td>
				<td>Details</td>
				<td>Status</td>
			</tr>
			<c:forEach items="${allOrder }" var="order">
				<tr>
					<td>${order.orderId }</td>
					<td>${order.createDate }</td>
					<td>$${order.totalMoney }</td>
					<td><a href="manager/OrderManagerServlet?method=detail&orderId=${order.orderId }">Details</a></td>
					<td>
						<c:choose>
							<c:when test="${order.status == 0 }">
								Unshipped.
								<a href="manager/OrderManagerServlet?method=deliver&orderId=${order.orderId}">
									Click to ship
								</a>
							</c:when>
							<c:when test="${order.status == 1 }">
									Shipped
							</c:when>
							<c:when test="${order.status == 2 }">
									Delivered
							</c:when>
						</c:choose>	
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div id="bottom">
		<span>
			PengBookStore.Copyright &copy;2020
		</span>
	</div>
</body>
</html>