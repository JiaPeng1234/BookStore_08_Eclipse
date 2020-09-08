<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<%@include file="/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		$(".delBtn").click(function(){
			var textEle = $(this).parents("tr").children(":first").text();
			if(!confirm("Do you want to delete " + textEle + "?")){
				return false;
			}
		});
		
		$(".changeInput").change(function(){
			var id = $(this).attr("updateid");
			var count = $(this).val();
			var href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/";
			// location.href = href+"client/CartServlet?method=update&id="+id+"&count="+count;
			$.getJSON(href+"client/CartServlet?method=updateAjax&id="+id+"&count="+count, function(data){
				$(".b_count").text(data.totalCount);
				$(".b_price").text(data.totalMoney);
				$("#count_"+id).val(data.updateCount);
				// Subtotal money of this cartitem
				$("#price_"+id).text(data.itemTotalPrice);
			});
		
		});
		
		$("#clearBtn").click(function(){
			if(!confirm("Do you want to clear the cart?"))
				return false;
		});
		
	});
</script>
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
		<span class="wel_word">Cart</span>
		<%@include file="/include/user-info.jsp" %>
	</div>
		
	<div id="main">
	
		<c:if test="${empty cart.allItems }">
			<h1>Your cart is empty. <a href="index.jsp">Go checkout our bookstore!</a></h1>
		</c:if>
		
		<c:if test="${!empty cart.allItems }">
			<table>
				<tr>
					<td>Name</td>
					<td>Quantity</td>
					<td>Price</td>
					<td>Subtotal</td>
					<td>Option</td>
				</tr>	
				<c:forEach items="${cart.allItems }" var="cartItem">	
					<tr>
						<td>${cartItem.book.title }</td>
						<td>
							<input class="changeInput" id="count_${cartItem.book.id }" type="text" style="width: 35px" name="count" updateid="${cartItem.book.id }" value="${cartItem.count }" />
						</td>
						<td>${cartItem.book.price }</td>
						<td id="price_${cartItem.book.id }">${cartItem.totalPrice }</td>
						<td><a class="delBtn" href="client/CartServlet?method=delete&id=${cartItem.book.id }">Delete</a></td>
					</tr>	
				</c:forEach>
			</table>
		
			<div class="cart_info">
				<span class="cart_span"><span class="b_count">${cart.totalCount }</span>items in cart</span>
				<span class="cart_span">Total<span class="b_price">${cart.totalMoney }</span>$</span>
				<span class="cart_span"><a id="clearBtn" href="client/CartServlet?method=clear">Clear cart</a></span>
				<span class="cart_span"><a href="client/OrderClientServlet?method=checkout">Checkout</a></span>
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