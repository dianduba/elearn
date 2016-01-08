<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!doctype html>
<html> 
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	
    <link rel="stylesheet" type="text/css" href="./jquery/jquery.mobile-1.4.5.min.css">
    <link rel="stylesheet" type="text/css" href="./css/custom.css">
    <script type="text/javascript" src="./jquery/jquery.min.js"></script>
    <script type="text/javascript" src="./jquery/jquery.mobile-1.4.5.min.js"></script>
    <script type="text/javascript" src="./jquery/jquery.qrcode.min.js"></script>
	
	<script type="text/javascript">
	$(function(){
		$('#qrcode').qrcode({width: 160,height: 160,text: "http://${pageContext.request.serverName}:${pageContext.request.serverPort}<c:url value='/store.do'/>"});
	});
	
	function favorite(bookId) {
		$.ajax({
			type: "post",
			dataType: "json",
			url: "<c:url value='/favoriteBook.do'/>", 
			data: {
				bookId: bookId
			},
			
			success: function(result){
				$("#info").text(result.message);
				$("#infoDialog").popup("open");
			}    
		});
	}
	
	</script>
	
</head>
<body>
	<div data-role="page" id="demo-page" class="book-page">
	 	 
		<div data-role="header" data-position="fixed" data-theme="b">
			<a href="<c:url value='/account.do'/>" data-ajax="false" data-icon="user" data-iconpos="notext">账户</a>
			
			<h1>你点我读</h1>
			<a href="#menu" data-role="button" class="ui-btn-right" data-icon="bars" data-iconpos="notext">选项</a>
		</div>
	 
		<div data-role="content" class="book-content">
			<ul data-role="listview" class="book-listview" data-inset="true">
				<li data-role="divider">共${fn:length(books)}本点读教材</li>
				<c:forEach var="book" items="${books}">
				<li>
					<a href="<c:url value='/book.do'/>?bookId=${book.id}" data-ajax="false">
				    	<img src="${book.faceImageUrl}" class="ui-li-thumb">
				    	<h2>${book.name}</h2>
				    	<p>${book.introduction}</p>
				    	
				    	<shiro:user> 
				    	<span class="book-item-btn" onclick="favorite('${book.id}'); return false;">收藏</span>
				    	</shiro:user>
				    </a>
				    
				</li>	
		        </c:forEach>
		         
		    </ul>
		
		</div>
		
		<div id="infoDialog" style="width:280px" data-role="popup" data-theme="b" data-overlay-theme="b" data-dismissible="false">
    		<div data-role="header" data-theme="a">
    			<h1>提示信息</h1>
    		</div>
    		<div class="ui-content" role="main">
    			<p id="info"></p>
        		<a class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b" style="margin-left:180px;" href="#" data-rel="back">关闭</a>
    		</div>
		</div>

		
		
		<div data-role="panel" id="menu" data-position="left" data-position-fixed="true" data-theme="b" data-display="overlay">
			<ul data-role="listview" >
				<li data-role="divider" data-theme="a">菜单</li>
		        <li><a href="<c:url value='/favorite.do'/>"  data-ajax="false" data-icon="shop">收藏夹</a></li>
		        <li><a href="<c:url value='/account.do'/>"  data-ajax="false" data-icon="user">我</a></li>
			</ul>
			
			<div style="padding-top:100px; text-align:center; margin:0 auto;">
				<p>你点我读主页二维码</p>
				<div id="qrcode" ></div>
			</div>
		</div>
		
		
	</div>
	
	
</body>
</html>