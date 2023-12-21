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
<<<<<<< HEAD
	//form.method()="POST";
	form.submit();
}
=======
	form.submit();
}
function postRemove() {
	return confirm("정말 삭제하시겠습니까?");		
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
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
</script>
<head>
<meta charset="UTF-8">
<title>FindPost</title>
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<div class="main">
<<<<<<< HEAD
		<!-- <button class="cancle" onclick="postList('<c:url value='find/findpost' />')">수정</button> -->
	  	<button class="cancle"><a href="<c:url value='find/findpost' />">수정</a></button> <!-- 수정누르면 postview로 이동 -->
	  	&nbsp;
	   	<button class="register" type="submit" form="postform" onclick="postList('<c:url value='find/findlist' />')">완료</button> 
	  	<div>작성글 확인<p>
	  		<table>
	  			<tr>
		  		 <td>(삭제해야할 부분)게시글번호:${findpost.findpostID} 익명:${findpost.isAnonymous}</td>
		  		</tr>
=======
	   	<a href="<c:url value='/find/findlist' />"><input type="button" value="완료"></a>
	   	<a href="#" onclick="postRemove();"><input type="button" value="삭제"></a>
	<!-- 	<a href="<c:url value='/community/delete'>
				   <c:param name='commId' value='${community.id}'/>
			 	 </c:url>" onclick="return communityRemove();">삭제(미구현)</a> &nbsp; -->
	  	<div>작성글 확인<p>
	  		<table>
		  		<tr>
		  			<td>
		  			 <c:choose>
					    <c:when test="${findpost.isAnonymous eq 'true'}">
					      익명
					    </c:when>
					    <c:otherwise>
					      ${findpost.userID}
					    </c:otherwise>
					  </c:choose>
					</td>
				</tr>
		  		<tr><td></td></tr>
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
		  		<tr>
		  		 <td>제목:${findpost.title}</td>
		  		</tr>
		  		<tr>
		  		 <td>우대사항:${findpost.prefer}</td>
		  		</tr>
		  		<tr>
<<<<<<< HEAD
		  			<td>내성향 ${lifepattern}</td>
=======
		  			<td>내성향: ${findpost.mycontent}</td>
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
		  		</tr>
		  		<tr>
		  			<td>내가 작성한 글: ${findpost.matecontent} </td>
		  		</tr>
	  		</table>
	  		<p>
	  		<table>
		  		<tr>
<<<<<<< HEAD
		  		 	<td>댓글 작성</td>
		  		</tr>
		  		<tr>
		  			<td>
		  			<textarea cols="150" rows="5" placeholder="댓글을 입력하세요	" name="comment"></textarea>
		  			</td>
		  		</tr>
=======
		  			<td>
		  			<input placeholder="댓글을 입력하세요" style="background-color:#FEF5F0; border-color:#8B2842" type="text"  name="comment" maxlength="500">
					<input type="submit" value="등록" style="background-color:#8B2842; color:white; border-color:white">
		  			</td>
		  		</tr>
		  		<!-- 작성된 댓글 표시
		  		<c:forEach var="comment" items="${}">
		    	<tr>
		    		<td>                    
		    		    <a href="<c:url value='//'>
						<c:param name='freepostID' value="${free.freepostID}"/>
						</c:url>" style="color: #8B2842; text-decoration: none;">
				  		<h4>${free.title}</h4></a>
		            	<h5>${free.content}</h5>
		            	<hr>
		    		</td>
		    	</tr>
		    	</c:forEach>-->
>>>>>>> db76e475bc2d191eccb977f44b093eee6671fe12
	  		</table>
	  	</div>
	</div>
</body>
</html>