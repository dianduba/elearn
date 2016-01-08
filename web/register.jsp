<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html> 
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="./jquery/jquery.mobile-1.4.5.min.css">
    <script type="text/javascript" src="./jquery/jquery.min.js"></script>
    <script type="text/javascript" src="./jquery/jquery.mobile-1.4.5.min.js"></script>
    <script type="text/javascript" src="./jquery/jquery.validate.min.js"></script>
	<script type="text/javascript" src="./js/messages_cn.js"></script>
	<style>
		.login-content {
    	    margin-top: 16px;
    		margin-left: 16px;
    		margin-right: 16px;
    	}
    
    	.login-content .ui-input-text {
    		border-width: 1px;
			padding: 5px;
			
			box-shadow: none; 
			border-radius:6px;
    	}
    	
    	#password-label + div {
    		margin-bottom: 0px;
    		border-width: 1px 1px 1px;
    		border-top-left-radius: 6px;
			border-top-right-radius: 6px;
			border-bottom-left-radius: 0px;
			border-bottom-right-radius: 0px;
    	}
    	
    	#confirmPassword-label + div {
    		margin-top: 0px;
    		border-width: 0px 1px 1px;
    		border-bottom-left-radius: 6px;
			border-bottom-right-radius: 6px;
			border-top-left-radius: 0px;
			border-top-right-radius: 0px;
    	}
    	
    	
	</style>
	<script>
		$().ready(function() {
			$("#register").validate({
				rules:{
					username: {
						required : true,
						minlength : 11,
						maxlength : 13,
						digits : true
					},
					password : {
						required : true,
						minlength : 6
					},
					confirmPassword : {
						required : true,
						equalTo: "#password"
					}
				}
			});
		});

	</script>
</head>
<body>
	<div class="page">
		<div data-role="header" data-position="fixed" data-theme="b">
			<a href="#" data-role="button" data-rel="back" data-icon="back" data-iconpos="notext">取消</a>
			<h1>注册</h1>
			
		</div>
		<div data-role="content" class="login-content">
			<form method="post" id="register" action="<c:url value='/register.do'/>" data-ajax="false">
			  	<label for="username" class="ui-hidden-accessible">用户名：</label>
			  	<input type="text" name="username" id="username" value="${user.username}" placeholder="您的手机号码"  >
			  	<label for="password" id="password-label" class="ui-hidden-accessible">密码：</label>
			  	<input type="password" name="password" id="password" value="${user.password}" placeholder="创建密码" >
			  	
			  	<label for="confirmPassword" id="confirmPassword-label" class="ui-hidden-accessible">确认密码：</label>
			  	<input type="password" name="confirmPassword" id="confirmPassword" value="" placeholder="再次输入密码" >
			  	
			  	<a href="javascript:$('#register').submit();" data-ajax="false" data-role="button" style="margin-top:20px; color:white; background-color:#38c;">创建账户</a>
			</form>
		</div>
		
	</div>
</body>
</html>