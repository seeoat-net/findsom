<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
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
<head>
<meta charset="UTF-8">
<title>FindCreatePost</title>
<link rel=stylesheet href="<c:url value='/css/Post.css' />" type="text/css">
</head>
<body>
	<span class="span">
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %> 
	<div>
		<form class="mainpost" method="post" name="createpost" action="<c:url value='/find/findpost'/>" id="postform">
		<h2>구인게시판 글쓰기</h2>
		<div class="right">
			<a href="<c:url value='/find/findlist' />"><button style="background-color:#8B2842; color:white; border-color:#8B2842">취소</button></a>
			<button type="submit" id="btn" value="등록" style="background-color:#8B2842; color:white; border-color:#8B2842"><b>등록</b></button>
			<input type="hidden" name="userID" value="<%= userID %>">
		</div>
	  		<table>
	  			<tr>
	  				<td>
	  				<label class="anonymous"><input type="checkbox" name="isAnonymous" value="true"><b>익명</b></label>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  				제목:<input type="text" name="title" >
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  				우대사항:<input type="text" name="prefer" >
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  				<p>내 성향: ${lifepattern}</p>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  				<textarea cols="150" rows="15" placeholder="내용을 입력하세요	" name="matecontent"></textarea>
	  				</td>
	  			</tr>
	  		</table>		
		</form>
	</div>
	</span>
</body>
</html>