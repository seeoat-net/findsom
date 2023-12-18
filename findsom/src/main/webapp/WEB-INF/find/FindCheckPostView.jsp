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
<link rel=stylesheet href="<c:url value='../css/main.css' />">
<link rel=stylesheet href="<c:url value='../css/findcheck.css' />">
</head>
<body>
	<div class="leftline"></div>
	<div class="rightline"></div>
	<div class="somsom"></div>
	<span  class="title">찾아주겠솜🏠</span>
	<div class="bell"></div>
	<div class="line"></div>
	<span  class="mypage">마이페이지</span>
	<div class="line1"></div>
	<span  class="find">
  		<a href="<c:url value='/find/findlist' />">구인 게시판</a>
  	</span>
  	<div class="line2"></div>
  	<span  class="match">매칭 게시판</span>
  	<div class="line3"></div>
  	<span  class="shit">쉿! 게시판</span>
  	<div class="line4"></div>
  	<span  class="free">
  		<a href="<c:url value='/free/freelist' />">자유 게시판</a>
	</span> 
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