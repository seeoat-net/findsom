<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>FreeCheckPost</title>
<link rel=stylesheet href="<c:url value='../css/main.css' />">
<link rel=stylesheet href="<c:url value='../css/freecheck.css' />">
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
		<!-- <button class="cancle" onclick="postList('<c:url value='find/findpost' />')">수정</button>
	  	<button class="cancle"><a href="<c:url value='find/findpost' />">수정</a></button> <!-- 수정누르면 postview로 이동 
	  	&nbsp;
	   	<button class="register" type="submit" form="postform" onclick="postList('<c:url value='find/findlist' />')">완료</button> -->
	  	<div>작성글 확인<p>
	  		<table>
		  		<tr>
		  		 <td>제목:${freepost.title}</td>
		  		</tr>
		  		<tr>
		  		 <td>익명:${freepost.isAnonymous}</td>
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
		  		<tr>
		  			<td>
		  				<input type="button" value="수정"> &nbsp;
		  			  	<input type="submit" value="완료" >
		  			</td>
		  		</tr>
	  		</table>
	  	</div>
	</div>
</body>
</html>