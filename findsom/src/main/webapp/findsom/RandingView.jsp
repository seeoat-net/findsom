<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RandingView</title>
<link rel=stylesheet href="<c:url value='../css/Randing.css' />" >
<script>
function login() {
	if (form.userId.value == "") {
		alert("사용자 ID를 입력하십시오.");
		form.userId.focus();
		return false;
	} 
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}
	form.submit();
}

function userCreate(targetUri) {
	form.action = targetUri;
	form.method="POST";		// register form 요청
	form.submit();
}
</script>
</head>
<body>
<br>
<!-- login form  -->
<form class="fixed" name="form" method="POST" action="<c:url value='/user/login' />">
  <table style="width:100%">
	<tr>
	  <td width="20"></td>
	  <td>		
	    <!-- 로그인이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${loginFailed}">
	  	  <br><font color="red"><c:out value="${exception.getMessage()}" /></font><br>
	    </c:if>
	    <br>
	    <table>
	  	  <tr>
			<td>
				<input id="input" type="text" name="userId" placeholder="사용자 ID">
			</td>
		  </tr>
	  	  <tr>
			<td>
				<input id="input" type="password" name="password" placeholder="PASSWORD">
			</td>
		  </tr>
	    </table>
	    <br>
	    <table style="width:100%">
		  <tr>
			<td align=left>
			<input type="button" id="btn1" value="로그인" onClick="userCreate(
								'<c:url value='/user/login'/>')"> &nbsp;
			<input type="button" id="btn2" value="회원가입" onClick="userCreate(
								'<c:url value='/findsom/SignupView.jsp'/>')">
			</td>						
		  </tr>
		  <!-- <tr height="40"><td>(관리자 로그인: admin/admin)</td></tr> -->
	    </table>
	  </td>
	</tr>
  </table>  
</form>
</body>
</html>
