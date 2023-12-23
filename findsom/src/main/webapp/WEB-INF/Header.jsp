<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<link rel=stylesheet href="<c:url value='/css/Header.css' />" type="text/css">
</head>
<body>
	<div class="headerWrap" >
		<div></div>
		<div class="headerTitle">찾아주겠솜🏠</div>
		<a href="<c:url value='/notification/post' />" >
			<img class="bellImg" alt="../images/bell.png" src="../images/bell.png" />
		</a>

	</div>
</body>
</html>