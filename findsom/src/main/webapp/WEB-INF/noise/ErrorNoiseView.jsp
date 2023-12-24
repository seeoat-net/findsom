<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ErrorNoiseView</title>
<link rel=stylesheet href="<c:url value='./css/Noise.css' />"  type="text/css">
</head>
<body>
<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<div class="container">
		<h1 class="naviText"> 쉿! 게시판 </h1><br/>
		<div class="item">
			<div class="itemMyNoise"> 
				방을 배정받지 않은 상태에서는 [쉿! 게시판] 이용이 불가합니다. <br/>
				방을 배정받았다면 마이페이지에서 사용자 정보를 수정해주세요!
			</div>	
		</div>
	</div>
</body>
</html>