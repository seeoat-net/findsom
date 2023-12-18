<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FindPost</title>
<link rel=stylesheet href="<c:url value='../css/main.css' />">
<link rel=stylesheet href="<c:url value='../css/findpost.css' />">
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
	  	
		<form class="mainpost" method="post" name="createpost" action="<c:url value='/find/findpost'/>" id="postform">
			<input type="hidden" name="userID" value="<%= userID %>">
	  		<table>
	  			<tr>
	  				<td>
	  				<label class="anonymous"><input type="checkbox" name="isAnonymous" value="true"><b>익명</b></label> <!-- value값은 선택되었을 때 서버로 전송되는 값 -->
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
	  		</table>			<!-- onclick="<c:url value='/find/findlist'/>" -->
	  		<button class="cancle" ><a href="<c:url value='/find/findlist' />">취소</a></button>
			<button class="register" type="submit" id="btn" value="등록">등록</button>
		</form>
	</div>
</body>
</html>