<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FreePost</title>
<link rel=stylesheet href="<c:url value='../css/mainView.css' />">
<link rel=stylesheet href="<c:url value='../css/freepost.css' />">
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
  		<a href="../find/FindMainView.jsp">구인 게시판</a>
  	</span>
  	<div class="line2"></div>
  	<span  class="match">매칭 게시판</span>
  	<div class="line3"></div>
  	<span  class="shit">쉿! 게시판</span>
  	<div class="line4"></div>
  	<span  class="free">
  		<a href="FreeMainView.jsp">자유 게시판</a>
	</span> 
	<div class="main">
	  	<button class="cancle" onclick="location.href='FreeMainView.jsp';">취소</button>
	  	<button class="register">등록</button>
	  	<select class="category"> 
		  <option value="">게시판 선택</option> 
		  <option value="info">이용정보</option> 
		  <option value="purchase">공동구매</option> 
		  <option value="share">나눔</option>
		  <option value="other">기타</option> 
		</select>
	  	<label class="anonymous"><input type="checkbox" name="isAnonymous" value="false"><b>익명</b></label> <!-- value값은 선택되었을 때 서버로 전송되는 값 -->
	  	<form class="post">
	  		제목:<input type="text" >
	  		<hr>
		    <textarea cols="150" rows="20" placeholder="내용을 입력하세요	"></textarea>
		</form>
	</div>
</body>
</html>