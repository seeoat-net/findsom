<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.dto.MatchDetailDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MatchDetailDTO matchingDetail = (MatchDetailDTO) request.getAttribute("matchingDetailResult");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MAtchDetailView</title>
<link rel=stylesheet href="<c:url value='../css/MatchDetail.css' />" >
<!--  <script>
    function postMsg() {
        var form = document.createElement("form");
        form.method = "POST";
        form.action = "<c:url value='/notification/messagePost'/>";

        var input = document.createElement("input");
        input.type = "hidden";
        input.name = "receiverID";
        // URL의 쿼리 파라미터에서 matchingUserID 값을 가져옵니다.
        input.value = new URLSearchParams(window.location.search).get('matchingUserID');

        form.appendChild(input);
        document.body.appendChild(form);

        form.submit();
        return false; // 이벤트 전파 방지
    }
</script>-->

<script>
    function postMsg() {
        window.location.href = "<c:url value='/notification/writeMessage'/>?receiverID=" + new URLSearchParams(window.location.search).get('matchingUserID');
    }
</script>

</head>

<body>
<span>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<div class="container">
		<div class="itemProfile">
			<h1> ${matchingDetail.nickname}님 </h1>
			<button class="postBtn" onclick="postMsg()" style="background-color:#8B2842; color:white; border-color:#8B2842">쪽지</button>
		</div>
		<div class="patternListBox">
			<h3>${matchingDetail.nickname}의 생활패턴</h3>
			<ul class="patternListBox2">
				<c:forEach var="pattern" items="${matchingDetail.lifePatternList}">
					<div class="postItem"><li>${pattern}</li></div>
				</c:forEach>
			</ul>
		</div>
		<div class="middleLine"></div>
		<div class="postList">
			<h3>${matchingDetail.nickname}의 구인 게시글</h3>
			<c:forEach var="findPost" items="${matchingDetail.finds}">
				<div class="postItem">
					<div class="postTitle">${findPost.title}</div>
					${findPost.prefer}
				</div>
			</c:forEach>
		</div>
	</div>
</span>
</body>
</html>