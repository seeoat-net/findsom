<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
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
			<button class="choiseBtn" onClick="clickBtn('<c:url value='/notification/post'/>')">작성글</button>
			<button class="notiBtn" onClick="clickBtn('<c:url value='/notification/comment'/>')">작성댓글</button>
			<button class="notiBtn" onClick="clickBtn('<c:url value='/notification/notiList'/>')">댓글 알림</button>
			<button class="notiBtn" onClick="clickBtn('<c:url value='/notification/messageView'/>')">쪽지함</button>
			<br/>
			
			<div class="itemBox">
				<div class="itemBoxTitle" > 구인 게시판 </div>
				<c:forEach var="post" items="${findpostlist}">
					<div class="item" onClick="clickBtn('<c:url value='/find/findcheck'><c:param name='findpostID' value='${post.postId}'/></c:url>')">
						<div class="itemTitle">${post.title}</div>${post.contents}
					</div>
				</c:forEach>
			</div>	
			<div class="itemBox">
				<div class="itemBoxTitle" > 자유 게시판 </div>
				<c:forEach var="post" items="${freepostlist}">
					<div class="item" onClick="clickBtn('<c:url value='/free/freecheck'><c:param name='freepostID' value='${post.postId}'/></c:url>')">
						<div class="itemTitle">${post.title}</div>${post.contents}
					</div>
				</c:forEach>
			
			</div>		
		</div>	
	</span>
</body>
</html>