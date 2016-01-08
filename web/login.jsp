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
    		margin: 0px 0px;
    		border-width: 0px 1px 1px;
			padding: 5px;
			
    	}
    	
    	#username-label + div {
    		border-width: 1px 1px 1px;
    		border-top-left-radius: 6px;
			border-top-right-radius: 6px;
    	}
    	
    	#password-label + div {
    		border-bottom-left-radius: 6px;
			border-bottom-right-radius: 6px;
    	}
    	
    	.login-content .ui-shadow-inset {
    		box-shadow : none;
    	}
    	
    	.login-content .ui-corner-all {
    		border-radius: 0px;
    	}
    	
    	
    	
    	.login-content .ui-checkbox {
    		margin-top:15px;
    	}
    	
	    .login-content .ui-checkbox label.ui-btn {
    		border: 0px;
    	}
    	
    	.login-content .ui-checkbox .ui-btn-icon-left::after {
    		left: 0px;
    	}
    	
    	.login-content .ui-checkbox label.ui-btn-icon-left{
    		 padding-left: 1.6em;
    		 background-color:none;
    	}
    
    </style>
	<script>
		$().ready(function() {
			$("#login").validate();
		});

	</script>
</head>
<body>
	<div class="page">
		<div data-role="header" data-position="fixed" data-theme="b">
			<a href="#" data-role="button" data-rel="back" data-icon="back" data-iconpos="notext">取消</a>
			<h1>你点我读</h1>
			
		</div>
		<div data-role="content" class="login-content">
			
			<form method="post" id="login" action="" data-ajax="false">
			  	<label for="username" id="username-label" class="ui-hidden-accessible">用户名：</label>
			  	<input type="text" name="username" id="username" placeholder="手机号码" class="required">
			  	<label for="password" id="password-label" class="ui-hidden-accessible">密码：</label>
			  	<input type="password" name="password" id="password" placeholder="密码" class="required">
			  	<label for="rememberMe">记住我</label>
			  	<input type="checkbox" name="rememberMe"  id="rememberMe" checked>
			  	
			  	<a href="javascript:$('#login').submit();" style="margin-top:20px; box-shadow: none; color:white;  border-radius:6px; background-color:#38c;" data-ajax="false" data-role="button">登录</a>
			</form>
			
			
			
			<span style="position : absolute; width:100%; left: 0px; bottom : 30px; text-align: center">还没帐号？<a href="<c:url value='/register.jsp'/>" data-ajax="false">立即注册</a></span>
		</div>
		
	</div>
</body>
</html>