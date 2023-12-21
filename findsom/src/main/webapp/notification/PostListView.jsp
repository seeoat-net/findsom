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
		<h1 class="naviText"> 알림함 </h1>
		<div class="box">
			<div class="container">
				<div class="btns">
					<button class="notiBtn" onClick="clickBtn('<c:url value='/'/>')">작성글</button>
					<button class="notiBtn" onClick="clickBtn('<c:url value='/'/>')">작성댓글</button>
					<button class="notiBtn" onClick="clickBtn('<c:url value='/'/>')">댓글 알림</button>
					<button class="notiBtn" onClick="clickBtn('<c:url value='/'/>')">쪽지함</button>
				</div>
			</div>
		</div>
	</span>
</body>
</html>