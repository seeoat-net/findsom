<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FreeSearch</title>
</head>
<body>
  	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<span>
  	<div>
  		<a href="<c:url value='/free/freelist' />"><input type="button" name="searchText" value="완료" style="background-color:#FEF5F0; border-color:#8B2842"  maxlength="100"></a>
	</div>
	<div>
		검색 결과<p>
		<table class="table">
	    <c:forEach var="search" items="${searchPost}">
	    	<tr>
	    		<td>
	    			<a href="<c:url value='/free/freecheck'>
					<c:param name='freepostID' value="${search.freepostID}"/>
					</c:url>" style="color: #8B2842; text-decoration: none;">
			  		<h4>${search.title}</h4></a>
	            	<h5>${search.content}</h5>
	            	<hr>
	    		</td>
	    	</tr>
	    </c:forEach>
	    </table>
	</div>
  	</span>
</body>
</html>