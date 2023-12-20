<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script>
<%
//JSP 페이지에서 세션에서 userID 가져오기
String userID = (String) session.getAttribute("userID");
%>
function postList(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
<head>
<meta charset="UTF-8">
<title>FreeCheckPost</title>
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<div class="main">
		<a href="<c:url value='/free/freelist' />"><input type="button" value="완료"></a>
	  	<div>작성글 확인<p>
	  		<table>
	  			<tr>
		  		 <td>작성자:${freepost.userID} 익명:${freepost.isAnonymous}</td>
		  		</tr>
		  		<tr>
		  		 <td>제목:${freepost.title}</td>
		  		</tr>
		  		<tr>
		  		 <td>카테고리:${freepost.category}</td>
		  		</tr>
		  		<tr>
		  			<td>내가 작성한 글: ${freepost.content} </td>
		  		</tr>
	  		</table>
	  		<p>
	  		<table>
		  		<tr>
		  		 	<td>댓글 작성</td>
		  		</tr>
		  		<tr>
		  			<td>
		  			<textarea cols="150" rows="5" placeholder="댓글을 입력하세요	" name="comment"></textarea>
		  			</td>
		  		</tr>
	  		</table>
	  	</div>
	</div>
</body>
</html>