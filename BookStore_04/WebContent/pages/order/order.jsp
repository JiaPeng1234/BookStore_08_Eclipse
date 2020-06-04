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
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.png" width="20%" >
			<span class="wel_word">My Orders</span>
			<%@include file="/include/user-info.jsp" %>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>Date</td>
				<td>Amount</td>
				<td>Status</td>
				<td>Detail</td>
			</tr>		
			<tr>
				<td>2015.04.23</td>
				<td>90.00</td>
				<td>Unshipped</td>
				<td><a href="#">Details</a></td>
			</tr>	
			
			<tr>
				<td>2015.04.20</td>
				<td>20.00</td>
				<td>Shipped</td>
				<td><a href="#">Details</a></td>
			</tr>	
			
			<tr>
				<td>2014.01.23</td>
				<td>190.00</td>
				<td>completed</td>
				<td><a href="#">Details</a></td>
			</tr>		
		</table>
		
	
	</div>
	
	<div id="bottom">
		<span>
			PengBookStore.Copyright &copy;2020
		</span>
	</div>
</body>
</html>