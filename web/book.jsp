<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!doctype html>
<html> 
<head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="./jquery/jquery.mobile-1.4.5.min.css">
	<script type="text/javascript" src="./jquery/jquery.min.js"></script>
	<script type="text/javascript" src="./jquery/jquery.mobile-1.4.5.min.js"></script>
	<script type="text/javascript" src="./jquery/jquery.qrcode.min.js"></script>
	<script type="text/javascript" src="./js/book.js"></script>
    
    <script type="text/javascript">
		var book;
		var bookId = "${book.id}";         
		$(function(){			
			book = new Book(bookId, "<c:url value='/'/>", $("#can")[0]);
			book.loadPage(-1);
						
			$("#can").on("swipeleft",function(){
				book.nextPage();				
			});
			
			$("#can").on("swiperight",function(){
				book.previousPage();
			});
			
			<shiro:user>
			$("#can").on("taphold",function(){
				favoriteBook();
			});
			</shiro:user>
			
			$('#qrcode').qrcode({width: 200,height: 200,text: "http://${pageContext.request.serverName}:${pageContext.request.serverPort}<c:url value='/book.do'/>?bookId=${book.id}"});

		});
		
		function favoriteBook() {
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
	<div data-role="page">
		<div data-role="header" data-position="fixed" data-theme="b">
			<a href="#" data-rel="back" data-ajax="false" data-icon="back" data-iconpos="notext">返回</a>
			<h1>${book.name}</h1>
			<a href="#menu" data-role="button" class="ui-btn-right" data-icon="bars" data-iconpos="notext">选项</a>
			
		</div>
		<div data-role="content" style="padding:0; margin:0;border:0;">	
    		<canvas id="can">Canvas is not supported</canvas>
    	</div>
    	
    	<div data-role="panel" id="menu" data-position="left" data-position-fixed="true" data-theme="b" data-display="overlay">
			<ul data-role="listview">
				<li data-role="divider" data-theme="a">菜单</li>
				<li><a href="<c:url value='/store.do'/>" data-ajax="false" data-icon="home">首页</a></li>
		        <li><a href="<c:url value='/favorite.do'/>" data-ajax="false" data-icon="shop">收藏夹</a></li>
		        <li><a href="<c:url value='/account.do'/>" data-ajax="false" data-icon="user">我</a></li>
		        <li><a href="#" onclick='$("#qrDialog").popup("open");' data-ajax="false" data-icon="user">二维码</a></li>
		        
		        <shiro:user>
		        <li><a href="#" onclick="favoriteBook()"  data-ajax="false">收藏此书</a></li>
		        <li data-role="divider" data-theme="a">我的收藏</li>
		        <c:forEach var="item" items="${books}">
				<li><a href="<c:url value='/book.do'/>?bookId=${item.id}" data-ajax="false" data-icon="home">${item.name}</a></li>
				</c:forEach>
				</shiro:user>
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
		
		<div id="qrDialog" style="width:280px" data-role="popup" data-theme="b" data-overlay-theme="b" data-dismissible="false">
    		<div data-role="header" data-theme="a">
    			<h1>${book.name}</h1>
    		</div>
    		<div class="ui-content" role="main">
    			<p>扫下面的二维码，分享点读教材《${book.name}》。</p>
    			<div id="qrcode" style="text-align:center; margin:0 auto;"></div>
        		<a class="ui-btn ui-corner-all ui-shadow ui-btn-b" href="#" data-rel="back">关闭</a>
    		</div>
		</div>
		
    </div>
    
         
</body>	
</html>