<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script>
function postList(targetUri) {
	form.action = targetUri;
	form.method="POST"
	form.submit();
}
function postRemove() {
    var form = document.createElement("form");
    form.method = "POST";
    form.action = "<c:url value='/free/freelist' />";
    
    var input = document.createElement("input");
    input.type = "hidden";
    input.name = "freepostID";
    input.value = "${freepost.freepostID}";
    
    form.appendChild(input);
    document.body.appendChild(form);
    
    form.submit();
    return false; // 이벤트 전파 방지
}
function postMsg() {
    window.location.href = "<c:url value='/notification/writeMessage'/>?receiverID=" + new URLSearchParams(window.location.search).get('matchingUserID');
}
</script>
<head>
<meta charset="UTF-8">
<title>FreeCheckPost</title>
<link rel=stylesheet href="<c:url value='/css/Post.css' />" type="text/css">
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<span class="span">
	<h2>자유게시판 작성 글 확인</h2>
	<div class="right">
		<a href="<c:url value='/free/freelist' />"><input type="button" value="완료" style="background-color:#8B2842; color:white; border-color:#8B2842"></a> &nbsp;
		<a href="#" onclick="postRemove();"><input type="button" value="삭제" style="background-color:#8B2842; color:white; border-color:#8B2842"></a>
	</div>  	
	<div>
		<table>
	  		<tr>
	  			<td>
	  			<c:choose>
				    <c:when test="${freepost.isAnonymous eq 'true'}">
				      <p>작성자: 익명 &emsp;<button class="postBtn" onclick="postMsg()" style="background-color:#8B2842; color:white; border-color:#8B2842">쪽지</button></p>
				    </c:when>
				    <c:otherwise>
				      <p>작성자: ${freepost.userID} &emsp;<button class="postBtn" onclick="postMsg()" style="background-color:#8B2842; color:white; border-color:#8B2842">쪽지</button></p>
				    </c:otherwise>
				  </c:choose>
				</td>
			</tr>
			
	  		<tr>
	  		 	<td>제목: ${freepost.title}</td>
	  		</tr>
	  		<tr>
	  			<td>
	  			<p>내가 작성한 글: ${freepost.content}</p>
	  			</td>
	  		</tr>
	 	</table>
	 	<p><hr>
	 		  		<table>

		  		<!-- 댓글 표시 -->
				<c:forEach items="${comments}" var="comment">
				    <div>
				        <p>${comment.userID}: ${comment.content}</p>
    				</div>
				</c:forEach>


				<!-- 댓글 등록 폼 -->
				<form action="<c:url value='/free/comment' />" method="POST">
    				<input type="hidden" name="freepostID" value="${param.freepostID}">
				 	<input placeholder="댓글을 입력하세요" style="background-color:#FEF5F0; border-color:#8B2842" type="text" name="content" maxlength="500">
    				<input type="submit" value="등록" style="background-color:#8B2842; color:white; border-color:white">
				</form>
	  		</table>
	 </div>
</span>
</body>
</html>