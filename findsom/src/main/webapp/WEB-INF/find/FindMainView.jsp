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
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<span>
  	<div>	 	
  		<form method="post" name="search" action="<c:url value='/find/findsearch' />">
  			<input style="background-color:#FEF5F0; border-color:#8B2842" type="text" name="searchText" maxlength="100">
			<input type="submit" value="검색" style="background-color:#8B2842; color:white; border-color:white" action="postList('<c:url value='/find/findsearch' />')">
			<span class="create"><a href="<c:url value='/find/findpost' />">✏️</a></span>
		</form>		
	</div>
	<div>
		<table class="table">
	    <c:forEach var="find" items="${findList}">
	    	<tr>
	    		<td>
	    			<a href="<c:url value='/find/findupdate'>
					<c:param name='findpostID' value="${find.findpostID}"/>
					</c:url>" style="color: #8B2842; text-decoration: none;">
			  		<h4>${find.title}</h4></a>
	            	<h5>${find.prefer}</h5>
	            	<hr>
	    		</td>
	    	</tr>
	    </c:forEach>
	    </table>
	</div>
  	</span>
</body>
</html>