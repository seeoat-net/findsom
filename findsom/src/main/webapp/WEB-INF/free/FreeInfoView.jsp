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
</head>
<body>
	<div>
		<table>
		 <c:forEach var="free" items="${infoList}">
		   	<tr>
		   		<td>                    
		   		    <a href="<c:url value='/free/freeupdate'>
					<c:param name='freepostID' value="${free.freepostID}"/>
					</c:url>" style="color: #8B2842; text-decoration: none;">
			  		<h4>${free.title}</h4></a>
		           	<h5>${free.content}</h5>
		           	<hr>
		   		</td>
		   	</tr>
	   	</c:forEach>
		</table>
	</div>
</body>
</html>