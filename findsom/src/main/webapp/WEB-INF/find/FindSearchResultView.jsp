<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FindSearch</title>
</head>
<body>
  	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
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
</body>
</html>