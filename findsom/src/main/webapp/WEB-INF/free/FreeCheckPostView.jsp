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
	return confirm("정말 삭제하시겠습니까?");		
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
</script>
<head>
<meta charset="UTF-8">
<title>FreeCheckPost</title>
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<div class="main">
		<a href="<c:url value='/free/freelist' />"><input type="button" value="완료"></a> &nbsp;
		<a href="#" onclick="postRemove();"><input type="button" value="삭제"></a>
	<!-- 	<a href="<c:url value='/community/delete'>
				   <c:param name='commId' value='${community.id}'/>
			 	 </c:url>" onclick="return communityRemove();">삭제(미구현)</a> &nbsp; -->
	  	<div>작성글 확인<p>
	  		<table>
		  		<tr>
		  			<td>
		  			 <c:choose>
					    <c:when test="${freepost.isAnonymous eq 'true'}">
					      작성자:익명
					    </c:when>
					    <c:otherwise>
					      작성자:${freepost.userID}
					    </c:otherwise>
					  </c:choose>
					</td>
				</tr>
		  		<tr>
		  		 <td>제목:${freepost.title}</td>
		  		</tr>
		  		<tr>
		  		 <td>카테고리:${freepost.category}</td>
		  		</tr>
		  		<tr>
		  			<td>내가 작성한 글: ${freepost.content} </td>
		  		</tr>
	  		</table>
	  		<p>
	  		<table>
		  		<tr>
		  			<td>
		  			<input placeholder="댓글을 입력하세요" style="background-color:#FEF5F0; border-color:#8B2842" type="text"  name="comment" maxlength="500">
					<input type="submit" value="등록" style="background-color:#8B2842; color:white; border-color:white">
		  			</td>
		  		</tr>
		  		<!-- 작성된 댓글 표시
		  		<c:forEach var="free" items="${purchaseList}">
		    	<tr>
		    		<td>                    
		    		    <a href="<c:url value='/free/freeupdate'>
						<c:param name='freepostID' value="${free.freepostID}"/>
						</c:url>" style="color: #8B2842; text-decoration: none;">
				  		<h4>${free.title}</h4></a>
		            	<h5>${free.content}</h5>
		            	<hr>
		    		</td>
		    	</tr>
		    	</c:forEach>-->
	  		</table>
	  	</div>
	</div>
</body>
</html>