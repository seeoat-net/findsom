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
	//form.method()="POST";
	form.submit();
}
</script>
<head>
<meta charset="UTF-8">
<title>FindPost</title>
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<div class="main">
		<!-- <button class="cancle" onclick="postList('<c:url value='find/findpost' />')">수정</button> -->
	  	<button class="cancle"><a href="<c:url value='find/findpost' />">수정</a></button> <!-- 수정누르면 postview로 이동 -->
	  	&nbsp;
	   	<button class="register" type="submit" form="postform" onclick="postList('<c:url value='find/findlist' />')">완료</button> 
	  	<div>작성글 확인<p>
	  		<table>
	  			<tr>
		  		 <td>(삭제해야할 부분)게시글번호:${findpost.findpostID} 익명:${findpost.isAnonymous}</td>
		  		</tr>
		  		<tr>
		  		 <td>제목:${findpost.title}</td>
		  		</tr>
		  		<tr>
		  		 <td>우대사항:${findpost.prefer}</td>
		  		</tr>
		  		<tr>
		  			<td>내성향 ${lifepattern}</td>
		  		</tr>
		  		<tr>
		  			<td>내가 작성한 글: ${findpost.matecontent} </td>
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