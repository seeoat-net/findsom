<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PostMessageMainView</title>
<link rel=stylesheet href="<c:url value='../css/PostList.css' />" >
</head>
<body>
	<span>
		<%@ include file="../Sidebar.jsp" %>
		<%@ include file="../Header.jsp" %>
		<div class="container">
			<h1 class="naviText"> 알림함 </h1><br/>
			<a class="btnTag" href="<c:url value='/notification/post' />" >
				<button class="notiBtn">작성글</button>
			</a>
			<a class="btnTag" href="<c:url value='/notification/comment' />" >
				<button class="notiBtn">작성댓글</button>
			</a>
			<a class="btnTag" href="<c:url value='/notification/notiList' />" >
				<button class="notiBtn">댓글 알림</button>
			</a>
			<a  class="btnTag" href="<c:url value='/notification/messageView' />" >
				<button class="choiseBtn" >쪽지함</button>
			</a>
			<br/>
            <div class="itemBox">
                <div class="itemBoxTitle">보낸 쪽지함</div>
                <c:forEach var="message" items="${sentMessages}">
                    <div class="item">
                        <div class="itemTitle">받는 사람: ${message.receiverID}</div>
                        <div>내용: ${message.messageText}</div>
                    </div>
                </c:forEach>
            </div>
			<div class="itemBox">
				<div class="itemBoxTitle" > 받은 쪽지함 </div>
				<c:forEach var="message" items="${receivedMessages}">
                    <div class="item">
                        <div class="itemTitle">보낸 사람: ${message.senderID}</div>
                        <div>내용: ${message.messageText}</div>
                    </div>
                </c:forEach>
			</div>	
	</span>
</body>
</html>