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
<title>FreePost</title>
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
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
	  		</table>
	  		<button class="cancle" ><a href="<c:url value='/free/freelist' />" >취소</a></button>
			<button class="register" type="submit" id="btn" value="등록">등록</button>
	  		<!-- <input type="button" value="취소"> &nbsp;
		  	<input type="submit" value="등록" >-->
		</form>
	</div>
</body>
</html>