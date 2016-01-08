<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<div data-role="panel" id="menu" data-position="left" data-display="overlay">
	<ul data-role="listview" data-divider-theme="a">
		<li><a href="<c:url value='/store.do'/>" data-ajax="false" data-icon="home">首页</a></li>
        <li><a href="<c:url value='/favorite.do'/>"  data-ajax="false" data-icon="shop">收藏夹</a></li>
        <li><a href="<c:url value='/account.do'/>"  data-ajax="false" data-icon="user">我</a></li>
        <li><a href="<c:url value='/help.jsp'/>" data-ajax="false" data-icon="help">帮助</a></li>
        <shiro:user>
	 	<li><a href="<c:url value='/logout'/>"  data-ajax="false" data-icon="power">注销</a></li>
	 	</shiro:user>
	</ul>
</div>
		
