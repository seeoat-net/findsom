<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script>
function postList(targetUri, buttonClass) {
    form.action = targetUri;
    form.submit();
}
</script>
<head>
<meta charset="UTF-8">
<title>FreeOther</title>
<link rel=stylesheet href="<c:url value='/css/Post.css' />" type="text/css">
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<span class="span">
	<h2>자유게시판-기타</h2>	
	<div class="center">	 	
		<form method="post" name="search" action="<c:url value='/free/freesearch' />">
			<input type="text" name="searchText" style="background-color:#FEF5F0; border-color:#8B2842"  maxlength="100">
			<input type="submit" value="검색" style="background-color:#8B2842; color:white; border-color:#8B2842">&emsp;
			<span class="create"><a href="<c:url value='/free/freepost' />"><b>✏️작성</b></a></span>
		</form>
	</div>
	<p>
	<div>
		<table class="table">
		 <c:forEach var="free" items="${otherList}">
		   	<tr>
		   		<td>                    
		   		    <a href="<c:url value='/free/freecheck'>
					<c:param name='freepostID' value="${free.freepostID}"/>
					</c:url>" style="color: #8B2842; text-decoration: none;">
			  		<b>${free.title}</b></a><br>
		           	${free.content}	
		           	<hr>
		   		</td>
		   	</tr>
	   	</c:forEach>
		</table>
	</div>
	</span>
</body>
</html>