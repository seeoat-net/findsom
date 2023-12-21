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
<title>FreeMain</title>
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<span>
	<div>	 	
		<form method="post" name="search" action="<c:url value='/free/freesearch' />">
  			<input type="text" name="searchText" style="background-color:#FEF5F0; border-color:#8B2842"  maxlength="100">
			<input type="submit" value="검색" style="background-color:#8B2842; color:white; border-color:white">
			<span class="create"><a href="<c:url value='/free/freepost' />">✏️</a></span>
	</div>
	<p>
	<div class="category">
		<input type="button" value="이용정보" style="background-color:#8B2842; color:white; border-color:white" class="info">
		<input type="button" value="공동구매" style="background-color:#8B2842; color:white; border-color:white" class="purchase">
		<input type="button" value="나눔" style="background-color:#8B2842; color:white; border-color:white" class="share">
		<input type="button" value="기타" style="background-color:#8B2842; color:white; border-color:white" class="other">
	</div>
	 <!-- <%@ include file="FreePurchaseView.jsp" %> 
		<c:choose>
			<c:when test="${param.buttonClass eq 'info'}">
		        <%@ include file="FreeInfoView.jsp" %>
		        <input type="button" value="test1">
		    </c:when>
		    <c:when test="${param.buttonClass eq 'purchase'}">
		        <%@ include file="FreePurchaseView.jsp" %>
		        <input type="button" value="test2">
		    </c:when>
		    <c:when test="${param.buttonClass eq 'share'}">
		        <%@ include file="FreeShareView.jsp" %>
		        <input type="button" value="test3">
		    </c:when>
		    <c:otherwise>
		        <%@ include file="FreeOtherView.jsp" %>
		    </c:otherwise>
		</c:choose>-->
		<table>
		 <c:forEach var="free" items="${freeList}">
		   	<tr>
		   		<td>                    
		   		    <a href="<c:url value='/free/freecheck'>
					<c:param name='freepostID' value="${free.freepostID}"/>
					</c:url>" style="color: #8B2842; text-decoration: none;">
			  		<h4>${free.title}</h4></a>
		           	<h5>${free.content}</h5>	
		           	<hr>
		   		</td>
		   	</tr>
	   	</c:forEach>
		</table>
	</span>
</body>
</html>