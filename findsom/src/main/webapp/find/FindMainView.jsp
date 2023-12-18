<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FindMain</title>
<link rel=stylesheet href="<c:url value='../css/main.css' />">
<link rel=stylesheet href="<c:url value='../css/findmain.css' />">
</head>
<body>
  <div class="leftline"></div>
  <div class="rightline"></div>
	<div class="somsom"></div>
	<span  class="title">찾아주겠솜🏠</span>
	<div class="bell"></div>
  	<div class="line"></div>
  	<span  class="mypage" >마이페이지</span>
  	<div class="line1"></div>
  	<span  class="find">
  		<a href="FindMainView.jsp">구인 게시판</a>
  	</span>
  	<div class="line2"></div>
  	<span  class="match">매칭 게시판</span>
  	<div class="line3"></div>
  	<span  class="shit">쉿! 게시판</span>
  	<div class="line4"></div>
  	<span  class="free">
  		<a href="../free/FreeMainView.jsp">자유 게시판</a>
	</span> 
  	<div class="main">
  		<form method="post" name="search" action="FindSearchResultView.jsp">
  		<div class="search">	 	
  			<input style="background-color:#FEF5F0; border-color:#8B2842" type="text" name="searchText" maxlength="100">
			<input type="submit" value="검색" style="background-color:#8B2842; color:white; border-color:white">
		</div>
		</form>
		<span class="create" onclick ="location.href='FindPostView.jsp'">✏️</span>
		<div class="list">
		    <c:forEach var="find" items="${findList}">
		        <div class="find-item">
		            <h2>${find.title}</h2>
		            <p>${find.prefer}</p>
		        </div>
		        <hr>
		    </c:forEach>
		</div>
  	</div>
</body>
</html>