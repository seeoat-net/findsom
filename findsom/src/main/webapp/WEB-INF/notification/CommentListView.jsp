<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CommentListView</title>
<link rel=stylesheet href="<c:url value='../css/PostList.css' />" >
<script>

function clickBtn(targetUri) {
	form.action = targetUri;
	form.method="GET";
	form.submit();
}

</script>
</head>
<body>
	<span>
		<%@ include file="../Sidebar.jsp" %>
		<%@ include file="../Header.jsp" %>
		<div class="container">
			<h1 class="naviText"> 알림함 </h1><br/>
			<button class="notiBtn" onClick="clickBtn('<c:url value='/notification/post'/>')">작성글</button>
			<button class="choiseBtn" onClick="clickBtn('<c:url value='/notification/comment'/>')">작성댓글</button>
			<button class="notiBtn" onClick="clickBtn('<c:url value='/notification/notiList'/>')">댓글 알림</button>
			<button class="notiBtn" onClick="clickBtn('<c:url value='/notification/messageView'/>')">쪽지함</button>
			<br/>
			
			<!-- 댓글 목록 리스트 쫙 -->
			<div class="itemBox">
				<div class="itemBoxTitle" > 구인 게시판 </div>
				<c:forEach items="${commentList}" var="comment">
				    <div>
				        <p>${comment.userID}: ${comment.content}</p>
    				</div>
				</c:forEach>
			</div>		
		</div>	
	</span>
</body>
</html>