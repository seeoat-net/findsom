<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<<<<<< HEAD
<title>FindMain</title>
=======
<title>FindSearch</title>
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
</head>
<body>
  	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
<<<<<<< HEAD
  	<div class="main">
  		<div class="search">	 	
  			<input style="background-color:#FEF5F0; border-color:#8B2842" type="text" name="searchText" maxlength="100">
			<input type="submit" value="검색" style="background-color:#8B2842; color:white; border-color:white">
		</div>
		<div class="list">글 목록 보여주기</div>
  	</div>
=======
	<span>
  	<div>
  		<a href="<c:url value='/find/findlist' />"><input type="button" name="searchText" value="완료" style="background-color:#FEF5F0; border-color:#8B2842"  maxlength="100"></a>
	</div>
	<div>
		검색 결과<p>
		   <c:choose>
	        <c:when test="${not empty searchPost}">
	            <table class="table">
	                <c:forEach var="search" items="${searchPost}">
	                    <tr>
	                        <td>
	                            <a href="<c:url value='/find/findcheck'>
	                                <c:param name='findpostID' value="${search.findpostID}"/>
	                            </c:url>" style="color: #8B2842; text-decoration: none;">
	                                <h4>${search.title}</h4></a>
	                            <h5>${search.prefer}</h5>
	                            <hr>
	                        </td>
	                    </tr>
	                </c:forEach>
	            </table>
	        </c:when>
	        <c:otherwise>
            <p>${message}</p>
        	</c:otherwise>
    	</c:choose>
	</div>
  	</span>
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
</body>
</html>