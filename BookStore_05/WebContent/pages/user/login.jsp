<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bookstore login</title>
<%@include file="/include/base.jsp" %>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.png" width="20%" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">Welcome ;)</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>Login</h1>
								<a href="pages/user/regist.jsp">Register new member</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">
								${empty msg? "Enter username and password":msg}
								<!-- jsp expression:《%=request.getAttribute("msg")==null?"Enter username and password":request.getAttribute("msg")%> -->
								</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="get">
									<input type="hidden" name="method" value="login"/>
									<label>User Name: </label>
									<input class="itxt" type="text" placeholder="Enter username" autocomplete="off" tabindex="1" name="username" 
									value="${param.username}"/>
									<br />
									<br />
									<label>Password: </label>
									<input class="itxt" type="password" placeholder="Enter password" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="Login" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				PengBookStore.Copyright &copy;2020
			</span>
		</div>
</body>
</html>