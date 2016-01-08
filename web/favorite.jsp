<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html> 
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="./jquery/jquery.mobile-1.4.5.min.css">
    <link rel="stylesheet" type="text/css" href="./css/custom.css">
    <script type="text/javascript" src="./jquery/jquery.min.js"></script>
    <script type="text/javascript" src="./jquery/jquery.mobile-1.4.5.min.js"></script>
	<script type="text/javascript">
	function cancelFavorite(bookId) {
		$.ajax({
			type: "post",
			dataType: "json",
			url: "<c:url value='/cancelFavorite.do'/>", 
			data: {
				bookId: bookId
			},
			
			success: function(result){
				$("#info").text(result.message);
				$("#infoDialog").popup("open");
				
				if (result.code == 0) {
					$("#book-" + bookId).remove();
					$("#book-listview").listview( "refresh" );
				}
			}    
		});
	}
	
	</script>
</head>
<body>
	<div data-role="page" class="book-page">
		 
		<div data-role="header" data-position="fixed" data-theme="b">
			<a href="<c:url value='/store.do'/>" data-ajax="false" data-icon="home" data-iconpos="notext">首页</a>
			<h1>收藏夹</h1>
			<a href="#menu" data-role="button" class="ui-btn-right" data-icon="bars" data-iconpos="notext">选项</a>
		</div>
		
		<div data-role="content" class="book-content">
			<ul data-role="listview" id="book-listview" class="book-listview" data-inset="true">
				<c:forEach var="book" items="${books}">
				<li id="book-${book.id}">
					<a href="<c:url value='/book.do'/>?bookId=${book.id}" data-ajax="false">
				    	<img src="${book.faceImageUrl}" class="ui-li-thumb">
				    	<h2>${book.name}</h2>
				    	<p>${book.introduction}</p>
				    	<span class="book-item-btn" onclick="cancelFavorite('${book.id}'); return false;">取消收藏</span>
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
			<ul data-role="listview">
				<li data-role="divider" data-theme="a">菜单</li>
				<li><a href="<c:url value='/store.do'/>" data-ajax="false" data-icon="home">首页</a></li>
		        <li><a href="<c:url value='/account.do'/>"  data-ajax="false" data-icon="user">我</a></li>
			</ul>
		</div>
	</div>
</body>
</html>