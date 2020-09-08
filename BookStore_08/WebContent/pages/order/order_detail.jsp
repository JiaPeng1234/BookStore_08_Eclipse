<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Detail</title>
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
		<span class="wel_word">Order Detail</span>
		<%@include file="/include/user-info.jsp" %>
	</div>
		
	<div id="main">
	
		<c:if test="${empty orderItems }">
			<h1>Ohoh, no items in the order.</h1>
		</c:if>
		
		<c:if test="${!empty orderItems }">
			<table>
				<tr>
					<td>Bookname</td>
					<td>Quantity</td>
					<td>Price</td>
					<td>Subtotal</td>
				</tr>	
				<c:forEach items="${orderItems }" var="OrderItem">	
					<tr>
						<td>${OrderItem.title }</td>
						<td>${OrderItem.count }</td>
						<td>${OrderItem.price }</td>
						<td>${OrderItem.totalPrice }</td>
					</tr>	
				</c:forEach>
			</table>
		
			<div class="cart_info">
				<span class="cart_span">Order Number: <span class="b_count">${thisOrder.orderId }</span></span>
				<span class="cart_span">Total<span class="b_price">${thisOrder.totalMoney }</span>$</span>
			</div>
		</c:if>
	
	</div>

	<div id="bottom">
		<span>
			PengBookStore.Copyright &copy;2020
		</span>
	</div>
</body>
</html>