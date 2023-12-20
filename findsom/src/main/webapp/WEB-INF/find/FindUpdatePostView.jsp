<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FindPost</title>
<script>
<%
//JSP 페이지에서 세션에서 userID 가져오기
String userID = (String) session.getAttribute("userId");
%>
function postList(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %> 
	<div>
		<form class="mainpost" method="post" name="createpost" action="<c:url value='/find/findupdate'/>" id="postform">
			<input type="hidden" name="userID" value="${findpost.userID}">
	  		<table>
	  			<tr>
	  				<td>
	  				익명여부: ${findpost.isAnonymous}
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  				제목:<input type="text" name="title" value='${findpost.title}'>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  				우대사항:<input type="text" name="prefer" value="${findpost.prefer}">
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  				<p>내 성향: ${lifepattern}</p>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  				<textarea cols="150" rows="15"  name="matecontent" value="${findpost.matecontent}"></textarea>
	  				</td>
	  			</tr>
	  		</table>			
	  		<button class="cancle" ><a href="<c:url value='/find/findlist' />">취소</a></button>
			<button class="register" type="submit" id="btn" value="완료">완료</button>
		</form>
	</div>
</body>
</html>