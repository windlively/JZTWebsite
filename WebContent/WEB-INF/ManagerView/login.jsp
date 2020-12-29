<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>后台管理页面</title>
		<style type="text/css">
			body{
				background-image: url('WebView/img/login_bg.jpg');
				background-position: top;
			}
			.body{
				position:relative;
				width:100%;
				height:100%;
				
			}
			.login_box{
				display: none;
				position:relative;
				margin-top: 120px;
				margin-left: auto;
				margin-right: auto;
				width:400px;
				height:210px;
				text-align:center;
				line-height:50px;
				background-color: #addcc8;
				box-shadow: #dbecc2 5px 5px 6px;
			}
			form{
				height: 100%;
			}
			h2{
				text-align: center;
				font-family: "microsoft yahei";
				font-weight: lighter;
				line-height: 60px;
				font-size:32px;
				letter-spacing: 4px;
			}
			.login_box div{
				height: 70px;
				line-height: 70px;
			}
			.input>input{
				height:22px;
				background-color: #dbecc2;
				border: none;
			}
			.button>input{
				background-color: #fed2b5;
				border: none;
				padding: 4px 8px;
			}
			.button>input:hover {
				background-color: #ffe3c6;
			}
		}
		</style>
	</head>
	<body>
		<div class="body">
		<h2>西科大记者团后台管理登录页面</h2>
			<div class="login_box">
				<form action="checkLogin" method="post">
					<div class="input">
						<span>管理员账号&nbsp;&nbsp;&nbsp;</span><input name="adminName" type="text"/>
					</div>
					<div class="input">
						<span>管理员密码&nbsp;&nbsp;&nbsp;</span><input name="adminPassword" type="password"/>
					</div>
					<div class="button">
						<input type="reset" value="重置" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="提交"/>
					</div>
				</form>
			</div>
		</div>
		<script type="text/javascript" src="WebView/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript">
			$(function(){
				$(".login_box").fadeIn(1000);
			})
		</script>
	</body>
</html>