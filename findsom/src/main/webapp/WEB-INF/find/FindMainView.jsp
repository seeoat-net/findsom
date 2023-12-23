<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script>
function postList(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
<head>
<meta charset="UTF-8">
<title>FindMain</title>
<link rel=stylesheet href="<c:url value='/css/Post.css' />" type="text/css">
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<span class="span">
	<h2>구인게시판</h2>
  	<div class="center">	 	
  		<form method="post" name="search" action="<c:url value='/find/findsearch' />" >
  			<input type="text" name="searchText" style="background-color:#FEF5F0; border-color:#8B2842"  maxlength="300">
			<input type="submit" value="검색" style="background-color:#8B2842; color:white; border-color:#8B2842">&emsp;
			<span class="create"><a href="<c:url value='/find/findpost' />"><b>✏️작성</b></a></span>
		</form>		
	</div>
	<div>
		<table class="table">
	    <c:forEach var="find" items="${findList}">
	    	<tr>
	    		<td>
	    			<a href="<c:url value='/find/findcheck'>
					<c:param name='findpostID' value="${find.findpostID}"/>
					</c:url>" style="color: #8B2842; text-decoration: none;">
			  		<b>${find.title}</b></a><br>
	            	&nbsp;${find.prefer}
	            	<hr>
	    		</td>
	    	</tr>
	    </c:forEach>
	    </table>
	</div>
  	</span>
</body>
</html>