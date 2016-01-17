<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html> 
<head>
	<title>你点我读</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="./jquery/jquery.mobile-1.4.5.min.css">
    <script type="text/javascript" src="./jquery/jquery.min.js"></script>
    <script type="text/javascript" src="./jquery/jquery.mobile-1.4.5.min.js"></script>
	<style>
		.ui-listview .account-item {
			position: absolute;
			right: 1em;
			margin: 0;
			text-align: right;
			text-valign : center;
		}
		
		
	</style>
</head>
<body>
	<div data-role="page">
		
		<div data-role="header" data-position="fixed" data-theme="b">
			<a href="#" data-rel="back" data-ajax="false" data-icon="back" data-iconpos="notext">返回</a>
			
			<h1>我</h1>
			<a href="<c:url value='/store.do'/>" data-ajax="false" class="ui-btn-right" data-icon="home" data-iconpos="notext">首页</a>
		</div>
		
		<div data-role="content" style="padding:0; margin:0;border:0;">
			
			<ul data-role="listview" data-inset="true">
				<li>用户名<span class="account-item">${user.username}</span></li>
			</ul>
			
			<ul data-role="listview" data-inset="true">
				
			  	<li>学习等级<span class="account-item"><c:choose><c:when test="${user.usedHits < 200}">初级</c:when><c:when test="${user.usedHits < 500}">中级</c:when><c:when test="${user.usedHits < 800}">高级</c:when><c:otherwise>点读达人</c:otherwise></c:choose></span></li>
				<li>点读次数<span class="account-item">${user.usedHits}</span></li>
			</ul>
			
			<ul data-role="listview" data-inset="true">
				<li><a href="<c:url value='/favorite.do'/>" data-ajax="false" data-icon="shop">收藏夹</a></li>
			</ul>
			
			<a href="<c:url value='/logout'/>" data-ajax="false" data-role="button">退出登录</a>
		
		</div>
		 
	</div>
</body>
</html>