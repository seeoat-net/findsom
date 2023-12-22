<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script>
<%
String userID = (String) session.getAttribute("userId");
%>
function postList(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
<head>
<meta charset="UTF-8">
<title>FreeCreatePost</title>
<link rel=stylesheet href="<c:url value='/css/Post.css' />" type="text/css">
</head>
<body>
	<span>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<div class="main">
		<form class="post" method="post" action="<c:url value='/free/freepost'/>">
	  	<h2>자유게시판 글쓰기</h2>
	  	<div class="right">	  	
	  		<a href="<c:url value='/free/freelist' />" ><button style="background-color:#8B2842; color:white; border-color:#8B2842">취소</button></a>
			<button type="submit" id="btn" value="등록" style="background-color:#8B2842; color:white; border-color:#8B2842">등록</button>
	  	</div>
	  		<input type="hidden" name="userID" value="<%= userID %>">
	  		<table>
	  			<tr>
	  				<label><input type="checkbox" name="isAnonymous" value="true"><b>익명</b></label>
	  			</tr>
	  			<tr>
	  				<td>
	  				<select  name="category" > 
					  <option value="">게시판 선택</option> 
					  <option value="info" >이용정보</option> 
					  <option value="purchase" >공동구매</option> 
					  <option value="share">나눔</option>
					  <option value="other">기타</option> 
					</select>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  				<p>제목:<input type="text" name="title"></p>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td><textarea cols="150" rows="20" placeholder="내용을 입력하세요	"  name="content"></textarea></td>
	  			</tr>
	  		</table>
		</form>
	</div>
	</span>
</body>
</html>