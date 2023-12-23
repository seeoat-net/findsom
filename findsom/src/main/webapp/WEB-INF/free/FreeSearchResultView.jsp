<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FreeSearch</title>
<link rel=stylesheet href="<c:url value='/css/Post.css' />" type="text/css">
</head>
<body>
  	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<span class="span">
	<h2>자유게시판 검색결과</h2>
  	<div class="right">
  		<a href="<c:url value='/free/freelist' />"><input type="button" name="searchText" value="완료" style="background-color:#8B2842; color:white; border-color:#8B2842"></a>
	</div>
	<div>
		<table class="table">
	    <c:forEach var="search" items="${searchPost}">
	    	<tr>
	    		<td>
	    			<a href="<c:url value='/free/freecheck'>
					<c:param name='freepostID' value="${search.freepostID}"/>
					</c:url>" style="color: #8B2842; text-decoration: none;">
			  		<b>${search.title}</b></a><br>
	            	&nbsp;${search.content}
	            	<hr>
	    		</td>
	    	</tr>
	    </c:forEach>
	    </table>
	</div>
  	</span>
</body>
</html>