<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FindSearch</title>
<link rel=stylesheet href="<c:url value='/css/Post.css' />" type="text/css">
</head>
<body>
  	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<span class="span">
	<h2>구인게시판 검색결과</h2>
  	<div class="right">
  		<a href="<c:url value='/find/findlist' />"><input type="button" value="완료" style="background-color:#8B2842; color:white; border-color:#8B2842"></a>
	</div>
	<div>
	    <table class="table">
			<c:forEach var="search" items="${searchPost}">
	              <tr>
	                  <td>
	                      <a href="<c:url value='/find/findcheck'>
	                           <c:param name='findpostID' value="${search.findpostID}"/>
	                      </c:url>" style="color: #8B2842; text-decoration: none;">
	                      <b>${search.title}</b></a><br>
	                      &nbsp;${search.prefer}
	                      <hr>
	                   </td>
	              </tr>
	          </c:forEach>
	    </table>
	</div>
  	</span>
</body>
</html>