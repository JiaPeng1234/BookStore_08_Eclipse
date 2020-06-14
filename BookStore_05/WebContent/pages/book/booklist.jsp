<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bookstore Homepage</title>
<%@include file="/include/base.jsp"%>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.png" width="20%" >
			<span class="wel_word">Online Bookstore</span>
			<!-- 这是操作菜单 -->
			<%@include file="/include/user-info.jsp" %>
	</div>
	<div id="main">
	
	
		<div id="book">
			<div class="book_cond">
				<form action="client/BookClientServlet?method=pageByPrice" method="post">
						Price: <input id="min" type="text" name="min" value="${param.min }"> &nbsp; $ - 
						<input id="max" type="text" name="max" value="${param.max }"> $ 
						<input type="submit" value="Go" />
				</form>
			</div>
			<div style="text-align: center">
				<span>You have 3 items in your cart</span>
				<div>
					You have just added <span style="color: red">Brief History of Time</span> to your cart
				</div>
			</div>
			
			<c:forEach items="${page.pageData }" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.imgPath }" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">Bookname:</span>
							<span class="sp2">${book.title }</span>
						</div>
						<div class="book_author">
							<span class="sp1">Author:</span>
							<span class="sp2">${book.author }</span>
						</div>
						<div class="book_price">
							<span class="sp1">Price:</span>
							<span class="sp2">$${book.price }</span>
						</div>
						<div class="book_sales">
							<span class="sp1">Sales:</span>
							<span class="sp2">${book.sales }</span>
						</div>
						<div class="book_amount">
							<span class="sp1">In stock:</span>
							<span class="sp2">${book.stock }</span>
						</div>
						<div class="book_add">
							<button>add to cart</button>
						</div>
					</div>
				</div>
			</c:forEach>
			
		</div>
		
		<!-- 页码抽取  -->
		<%@include file="/include/page.jsp"%>
	
	</div>
	
	<div id="bottom">
		<span>
			PengBookStore.Copyright &copy;2020
		</span>
	</div>
</body>
</html>