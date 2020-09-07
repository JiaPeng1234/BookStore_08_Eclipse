<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bookstore Homepage</title>
<%@include file="/include/base.jsp"%>
<script type="text/javascript">
	$(function(){
		$(".addCartBtn").click(function(){
			var bookid = $(this).attr("updateId");
			$.getJSON("client/CartServlet?method=addAjax&id="+bookid,function(data){
				$("#booktitleTip").html("You have just added " + "<span style='color:red;'>" + data.title + "</span>" + " to your cart");
				$("#totalCountTip").text(data.totalCount);
			});
		});
	});
</script>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.png" width="20%">
		<span class="wel_word">Online Bookstore</span>
		<!-- 这是操作菜单 -->
		<%@include file="/include/user-info.jsp"%>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/BookClientServlet?method=pageByPrice" method="get">
					<input type="hidden" name="method" value="pageByPrice">
					Price: <input id="min" type="text" name="min" value="${param.min }">
					&nbsp; $ - <input id="max" type="text" name="max" value="${param.max }">
					 $ <input type="submit" value="Go" />
				</form>
			</div>
			<div style="text-align: center">
				<span >
					You have <span id="totalCountTip">
						<c:out value="${cart.totalCount }" default="0"></c:out>
					</span>	items in your cart
				</span>
				<div>
					<span id="booktitleTip">&nbsp;</span>
				</div>
			</div>

			<c:forEach items="${page.pageData }" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.imgPath }" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">Bookname:</span> <span class="sp_bookname">${book.title }</span>
						</div>
						<div class="book_author">
							<span class="sp1">Author:</span> <span class="sp2">${book.author }</span>
						</div>
						<div class="book_price">
							<span class="sp1">Price:</span> <span class="sp2">${book.price }</span>
						</div>
						<div class="book_sales">
							<span class="sp1">Sales:</span> <span class="sp2">${book.sales }</span>
						</div>
						<div class="book_amount">
							<span class="sp1">In stock:</span> <span class="sp2">${book.stock }</span>
						</div>
						<div class="book_add">
							<button updateId="${book.id }" class="addCartBtn">add to cart</button>
<!-- 							<a style="color: blue;" -->
<%-- 								href="client/CartServlet?method=add&id=${book.id }">add to --%>
<!-- 								cart</a> -->
						</div>
					</div>
				</div>
			</c:forEach>

		</div>

		<!-- 页码抽取  -->
		<%@include file="/include/page.jsp"%>

	</div>

	<div id="bottom">
		<span> PengBookStore.Copyright &copy;2020 </span>
	</div>
</body>
</html>