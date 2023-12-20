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
<title>FreeMain</title>
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<span>
		<div>	 	
	  		<input style="background-color:#FEF5F0; border-color:#8B2842" type="text"  name="searchText" maxlength="100">
			<input type="submit" value="검색" style="background-color:#8B2842; color:white; border-color:white">
			<span class="create"><a href="<c:url value='/free/freepost' />">✏️</a></span>
		</div>
		<p>
		<div class="category">
			<span class="info"><b>이용정보</span> &nbsp;
			<span class="purchase"><b>공동구매</span>&nbsp;
			<span class="share"><b>나눔</span>&nbsp;
			<span class="other"><b>기타</span>
		</div>
		<div>
			<table>
			 <c:forEach var="free" items="${freeList}">
		    	<tr>
		    		<td>                    
		    		    <a href="<c:url value='/free/freeupdate'>
						<c:param name='freepostID' value="${free.freepostID}"/>
						</c:url>" style="color: #8B2842; text-decoration: none;">
				  		<h4>${free.title}</h4></a>
		            	<h5>${free.content}</h5>
		            	<hr style="width: 100%;">
		    		</td>
		    	</tr>
		    </c:forEach>
			</table>
		</div>
	</span>
</body>
</html>