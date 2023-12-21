<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script>
<%
String userID = (String) session.getAttribute("userId");
%>
<<<<<<< HEAD
=======
function postList(targetUri) {
	form.action = targetUri;
	form.submit();
}
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
</script>
<head>
<meta charset="UTF-8">
<title>FreePost</title>
<<<<<<< HEAD
<link rel=stylesheet href="<c:url value='../css/main.css' />">
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
=======
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
	<div class="main">
	  	<!--<button class="cancle" onclick="">취소</button>
	    <button class="register" type="submit">등록</button> -->
	  	<form class="post" method="post" action="<c:url value='/free/freepost'/>">
	  		<input type="hidden" name="userID" value="<%= userID %>">
	  		<table>
	  			<tr>
	  				<td>
	  				<label ><input type="checkbox" name="isAnonymous" value="true"><b>익명</b></label>
	  				</td>
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
	  				<td>제목:<input type="text" name="title"></td>
	  			</tr>
	  			<tr>
	  				<td><textarea cols="150" rows="20" placeholder="내용을 입력하세요	"  name="content"></textarea></td>
	  			</tr>
<<<<<<< HEAD
	  			<tr>
	  				<td>
	  					<input type="button" value="취소"> &nbsp;
		  			  	<input type="submit" value="등록" >
	  				</td>
	  			</tr>
	  		</table>
=======
	  		</table>
	  		<button class="cancle" ><a href="<c:url value='/free/freelist' />" >취소</a></button>
			<button class="register" type="submit" id="btn" value="등록">등록</button>
	  		<!-- <input type="button" value="취소"> &nbsp;
		  	<input type="submit" value="등록" >-->
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
		</form>
	</div>
</body>
</html>