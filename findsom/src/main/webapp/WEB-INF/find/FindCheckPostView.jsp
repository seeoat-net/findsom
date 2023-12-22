<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<script>
<%
//JSP 페이지에서 세션에서 userID 가져오기
String userID = (String) session.getAttribute("userID");
%>
function postList(targetUri) {
	form.action = targetUri;
	form.submit();
}
function postRemove() {
    var form = document.createElement("form");
    form.method = "POST";
    form.action = "<c:url value='/find/findlist' />";
    
    var input = document.createElement("input");
    input.type = "hidden";
    input.name = "findpostID";
    input.value = "${findpost.findpostID}";
    
    form.appendChild(input);
    document.body.appendChild(form);
    
    form.submit();
    return false; // 이벤트 전파 방지
}
</script>
<head>
<meta charset="UTF-8">
<title>FindCheckPost</title>
<link rel=stylesheet href="<c:url value='/css/Post.css' />" type="text/css">
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<span class="span">
	<h2>구인게시판 작성 글 확인</h2>
	<div class="right">
	   	<a href="<c:url value='/find/findlist' />"><input type="button" value="완료" style="background-color:#8B2842; color:white; border-color:#8B2842"></a>
	   	<a href="#" onclick="postRemove();"><input type="button" value="삭제" style="background-color:#8B2842; color:white; border-color:#8B2842"></a>
	</div>  	
	<div>
		<table>
	  		<tr>
	  			<td>
	  			 <c:choose>
				    <c:when test="${findpost.isAnonymous eq 'true'}">
				      <p>작성자: 익명 &emsp;<input type="button" value="쪽지" style="background-color:#8B2842; color:white; border-color:#8B2842"></p>
				    </c:when>
				    <c:otherwise>
				      <p>작성자: ${findpost.userID} &emsp;<input type="button" value="쪽지" style="background-color:#8B2842; color:white; border-color:#8B2842"></p> 
				    </c:otherwise>
				  </c:choose>
				</td>
			</tr>

	  		<tr>
	  		 	<td>제목: ${findpost.title}</td>
	  		</tr>
	  		<tr>
	  		 	<td>우대사항: ${findpost.prefer}</td>
	  		</tr>
	  		<tr>
	  			<td>
	  			<p>내성향: ${findpost.mycontent}</p>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td>
	  			<p>내가 작성한 글: ${findpost.matecontent}</p>
	  			</td>
	  		</tr>
	 	</table>
	 	<p><hr>
	 	<table>
	  		<tr>
	  			<td>
	  			<input placeholder="댓글을 입력하세요" style="background-color:#FEF5F0; border-color:#8B2842" type="text"  name="comment" maxlength="500">
				<input type="submit" value="등록" style="background-color:#8B2842; color:white; border-color:#8B2842">
	  			</td>
	  		</tr>
	  	</table>
	</div>
	</span>
</body>
</html>