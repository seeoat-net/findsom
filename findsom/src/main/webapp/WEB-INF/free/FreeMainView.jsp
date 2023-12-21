<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script>
<<<<<<< HEAD
function postList(targetUri) {
	form.action = targetUri;
	form.submit();
=======
function postList(targetUri, buttonClass) {
    form.action = targetUri;
    form.submit();
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
}
</script>
<head>
<meta charset="UTF-8">
<title>FreeMain</title>
<<<<<<< HEAD
<link rel=stylesheet href="<c:url value='../css/main.css' />">
<link rel=stylesheet href="<c:url value='../css/freemain.css' />">
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
			<div class="search">	 	
	  			<input style="background-color:#FEF5F0; border-color:#8B2842" type="text"  name="searchText" maxlength="100">
				<input type="submit" value="검색" style="background-color:#8B2842; color:white; border-color:white">
			</div>
			<span class="create"><a href="<c:url value='/free/freepost' />">✏️</a></span>
			<div class="category">
				<span class="info"><b>이용정보</span>
				<span class="purchase"><b>공동구매</span>
				<span class="share"><b>나눔</span>
				<span class="other"><b>기타</span>
			</div>
		</div>
  	<div class="bell"></div>
=======
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<span>
	<div>	 	
		<form method="post" name="search" action="<c:url value='/free/freesearch' />">
  			<input type="text" name="searchText" style="background-color:#FEF5F0; border-color:#8B2842"  maxlength="100">
			<input type="submit" value="검색" style="background-color:#8B2842; color:white; border-color:white">
			<span class="create"><a href="<c:url value='/free/freepost' />">✏️</a></span>
	</div>
	<p>
	<div class="category">
		<input type="button" value="이용정보" style="background-color:#8B2842; color:white; border-color:white" class="info">
		<input type="button" value="공동구매" style="background-color:#8B2842; color:white; border-color:white" class="purchase">
		<input type="button" value="나눔" style="background-color:#8B2842; color:white; border-color:white" class="share">
		<input type="button" value="기타" style="background-color:#8B2842; color:white; border-color:white" class="other">
	</div>
	 <!-- <%@ include file="FreePurchaseView.jsp" %> 
		<c:choose>
			<c:when test="${param.buttonClass eq 'info'}">
		        <%@ include file="FreeInfoView.jsp" %>
		        <input type="button" value="test1">
		    </c:when>
		    <c:when test="${param.buttonClass eq 'purchase'}">
		        <%@ include file="FreePurchaseView.jsp" %>
		        <input type="button" value="test2">
		    </c:when>
		    <c:when test="${param.buttonClass eq 'share'}">
		        <%@ include file="FreeShareView.jsp" %>
		        <input type="button" value="test3">
		    </c:when>
		    <c:otherwise>
		        <%@ include file="FreeOtherView.jsp" %>
		    </c:otherwise>
		</c:choose>-->
		<table>
		 <c:forEach var="free" items="${freeList}">
		   	<tr>
		   		<td>                    
		   		    <a href="<c:url value='/free/freecheck'>
					<c:param name='freepostID' value="${free.freepostID}"/>
					</c:url>" style="color: #8B2842; text-decoration: none;">
			  		<h4>${free.title}</h4></a>
		           	<h5>${free.content}</h5>	
		           	<hr>
		   		</td>
		   	</tr>
	   	</c:forEach>
		</table>
	</span>
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
</body>
</html>