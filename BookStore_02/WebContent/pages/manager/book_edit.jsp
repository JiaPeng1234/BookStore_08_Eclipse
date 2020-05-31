<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Edits</title>
<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.png" width="20%" >
			<span class="wel_word">Book Edits</span>
			<%@include file="/include/book-manager.jsp" %>
		</div>
		
		<div id="main">
			<form action="book_manager.html">
				<table>
					<tr>
						<td>Name</td>
						<td>Price</td>
						<td>Author</td>
						<td>Sales</td>
						<td>In stock</td>
						<td colspan="2">Option</td>
					</tr>		
					<tr>
						<td><input name="book_name" type="text" value="Brief History of Time"/></td>
						<td><input name="book_price" type="text" value="30.00"/></td>
						<td><input name="book_author" type="text" value="Hawking"/></td>
						<td><input name="book_sales" type="text" value="200"/></td>
						<td><input name="book_stock" type="text" value="300"/></td>
						<td><input type="submit" value="Submit"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
			<span>
				PengBookStore.Copyright &copy;2020
			</span>
		</div>
</body>
</html>