<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Member</title>
<%@include file="/include/base.jsp"%>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 27px;
	}
</style>
<script type="text/javascript">
	$(function() {
		//2. 图片验证码点击则更新
		$("#codeImg").click(function(){
			var url = "code.jpg?t="+Math.random();
			$(this).prop("src",url);
		});
		
		//3. 验证用户名是否已用
		$(".itxt[name='username']").blur(function(){
			var regUserName = /^[a-zA-Z0-9_-]{5,18}$/;
			var username = $(".itxt[name='username']").val();
			// 如果用户名不是非法的，再ajax验证其是否已经被用过
			if(regUserName.test(username)){
				$.get("UserServlet?method=checkuser&username="+username,function(data){
					$(".errorMsg").html(data);
				});
			}else{
				$(".errorMsg").text("Unvalid username!");
			}
		});
		
		//1. 验证用户名，密码，确认密码，email是否符合规定
		$("#sub_btn").click(function(){
			//2. 验证成功，提交表单
			  //获取用户输入的所有值
			  var username = $(".itxt[name='username']").val();
			  var password = $(".itxt[name='password']").val();
			  var repwd = $(".itxt[name='repwd']").val();
			  var email = $(".itxt[name='email']").val();
			  var code = $(".itxt[id='code']").val();
			  
			  //用户名规则： 长度>5 <8  不能写非法字符  合法：（字母，数字，_，-）
			  //密码规则： 长度>5 <18 不能写非法字符  合法（字母，数字，_，-）
			  //确认密码规则： 和密码相同
			  //email：xxx@aa.com
			  //前端保障验证码输入值即可，后端验证一不一样
			  
			  //创建正则表达式：作用：验证是否符合表达式规则
			  var regUserName = /^[a-zA-Z0-9_-]{5,18}$/;
			  var regPwd = /^[a-zA-Z0-9_-]{5,18}$/;
			  var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			  
			  if(!regUserName.test(username)){
				  $(".errorMsg").text("Unvalid username!");
				  return false;
			  }
			  if(!regPwd.test(password)){
				  $(".errorMsg").text("Unvalid password!");
				  return false;
			  }
			  if(repwd != password){
				  $(".errorMsg").text("Passwords don't match!");
				  return false;
			  }
			  if(!regEmail.test(email)){
				  $(".errorMsg").text("Unvalid email!");
				  return false;
			  }
			  if(code == ""){
				  $(".errorMsg").text("Enter security code!");
				  return false;
			  }
			  
			//3. 验证失败，交代为什么失败
			
		});
	});
</script>
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
								<h1>New Member</h1>
								<span class="errorMsg">
								${msg}
								</span>
							</div>
							<br />
							<div class="form">
								<form action="UserServlet?method=regist" method="post">
									<label>User Name:</label>
									<input class="itxt" type="text" placeholder="Enter User Name" autocomplete="off" tabindex="1" name="username" id="username" 
									value="${param.username }"/>
									<br />
									<br />
									<label>Password:</label>
									<input class="itxt" type="password" placeholder="Enter Password" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>Confirm: </label>
									<input class="itxt" type="password" placeholder="Confirm Password" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>E-Mail:</label>
									<input class="itxt" type="text" placeholder="Enter E-Mail Adress" autocomplete="off" tabindex="1" name="email" id="email" 
									value="${param.email}"/>
									<br />
									<br />
									<label>Security Code:</label>
									<input class="itxt" type="text" style="width: 130px;" id="code" name="code"/>
									<img alt="" src="code.jpg" id="codeImg" style="float: right; margin-right: 20px; height: 33px; width: 80px">									
									<br />
									<br />
									<input type="submit" value="Submit" id="sub_btn" />
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