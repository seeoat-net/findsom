<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.User" %>
<%
	User user = (User)request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SideBar</title>
<link rel=stylesheet href="<c:url value='/css/Sidebar.css' />">
</head>
<body>
<!-- Sidebar-->
	<div class="sidebarWrap">
		<div class="userItem">
			<img class="userImg" alt="../images/somsom.png" src="../images/somsom.png" />
			${user.getNickname()} <br> ${user.isRecruite()}
		</div>
		<div class="sidebarItem"></div>
		<a class="sidebarItem" href="<c:url value='/user/mypageMain' />">마이페이지</a>
		<hr class="sidebarLine">
		<a class="sidebarItem" href="<c:url value='/find/findlist' />">구인 게시판</a>
		<hr class="sidebarLine">
		<a class="sidebarItem" href="<c:url value='/match/matching' />" >매칭 게시판</a>
		<hr class="sidebarLine">
		<a class="sidebarItem" href="<c:url value='/noise' />">쉿! 게시판</a>
		<hr class="sidebarLine">
		<a class="sidebarItem" href="<c:url value='/free/freelist' />">자유 게시판</a>	
		<a class="sidebarItemFree" href="<c:url value='/free/freeinfo' />">이용 정보</a>
		<a class="sidebarItemFree" href="<c:url value='/free/freepurchase' />">공동 구매</a>
		<a class="sidebarItemFree" href="<c:url value='/free/freeshare' />">나눔</a>
		<a class="sidebarItemFree" href="<c:url value='/free/freeother' />">기타</a>
	</div>
</body>
