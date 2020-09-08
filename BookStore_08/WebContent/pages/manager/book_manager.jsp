<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Management System</title>
<%@include file="/include/base.jsp"%>

<script type="text/javascript">
	$(function() {
		$(".delBtn").click(function() {
			let td = $(this).parent().parent().children(":first");
			if (!confirm("Do you want to delete " + td.text() + "?")) {
				return false;
			}
		});
	});
</script>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.png" width="20%">
		<span class="wel_word">Book Management</span>
		<%@include file="/include/book-manager.jsp"%>
	</div>
	<!-- ${requestScope.list } -->
	<div id="main">
		<table>
			<tr>
				<td>Name</td>
				<td>Price</td>
				<td>Author</td>
				<td>Sales</td>
				<td>In stock</td>
				<td colspan="2">Option</td>
			</tr>
			<c:forEach items="${requestScope.page.pageData }" var="book">
				<tr>
					<td>${book.title }</td>
					<td>${book.price }</td>
					<td>${book.author }</td>
					<td>${book.sales }</td>
					<td>${book.stock }</td>
					<td><a
						href="manager/BookManagerServlet?method=getBook&id=${book.id }&pn=${page.pageNo}">Modify</a></td>
					<td><a class="delBtn"
						href="manager/BookManagerServlet?method=delete&id=${book.id }&pn=${page.pageNo}">Delete</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp">Add books</a></td>
			</tr>
		</table>
		
		<!-- 页码抽取  -->
		<%@include file="/include/page.jsp"%>
		
	</div>

	<div id="bottom">
		<span> PengBookStore.Copyright &copy;2020 </span>
	</div>
</body>
</html>