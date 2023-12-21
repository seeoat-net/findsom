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
<<<<<<< HEAD
	<span>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
  	<div>
  		<form method="post" name="search" action="<c:url value='/find/findsearch' />">
  		<div class="search">	 	
  			<input style="background-color:#FEF5F0; border-color:#8B2842" type="text" name="searchText" maxlength="100">
			<input type="submit" value="검색" style="background-color:#8B2842; color:white; border-color:white">
		</div>
		</form>   
		<button ><a href="<c:url value='/find/findpost' />">✏️</a></button>
		<div class="list">
			<table>
		    <c:forEach var="find" items="${findList}">
		    	<tr>
		    		<td>
		    		 	<h2>${find.title}</h2>
		            	<p>${find.prefer}</p>
		    		</td>
		    	</tr>
		    </c:forEach>
		    </table>
		</div>
  	</div>
=======
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<span>
  	<div>	 	
  		<form method="post" name="search" action="<c:url value='/find/findsearch' />">
  			<input type="text" name="searchText" style="background-color:#FEF5F0; border-color:#8B2842"  maxlength="100">
			<input type="submit" value="검색" style="background-color:#8B2842; color:white; border-color:white">
			<span class="create"><a href="<c:url value='/find/findpost' />">✏️</a></span>
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
			  		<h4>${find.title}</h4></a>
	            	<h5>${find.prefer}</h5>
	            	<hr>
	    		</td>
	    	</tr>
	    </c:forEach>
	    </table>
	</div>
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
  	</span>
</body>
</html>