<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PostMessageView</title>
<link rel=stylesheet href="<c:url value='../css/PostMessage.css' />" >
<script>

function postMsg() {
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
			<a class="btnTag" href="<c:url value='/notification/post' />" >
				<button class="notiBtn">작성글</button>
			</a>
			<a class="btnTag" href="<c:url value='/notification/comment' />" >
				<button class="notiBtn">작성댓글</button>
			</a>
			<a class="btnTag" href="<c:url value='/notification/notiList' />" >
				<button class="notiBtn">댓글 알림</button>
			</a>
			<a class="btnTag" href="<c:url value='/notification/messageView' />" >
				<button class="choiseBtn">쪽지함</button>
			</a>
			<br/>
			<div class="postBox">
				<div class="postBoxTitle" > 쪽지 보내기 </div>
				<form name="form" method="POST" action="<c:url value='/notification/messagePost'/>">
					<input hidden="hidden" />
					<textarea class="postBoxText" id="messageText" 
						name="messageText" autocomplete="off" placeholder="쪽지 내용을 입력하세요!" ></textarea>
				</form>
				<input type="button" class="postBtn" value="전송" onClick="postMsg()" />	
				
			</div>		
		</div>	
	</span>
</body>
</html>