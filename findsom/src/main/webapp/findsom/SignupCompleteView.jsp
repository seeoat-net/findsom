<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignupCompleteView</title>
<link rel=stylesheet href="<c:url value='../css/Signup.css' />">
</head>
<body>
	<div align="center" id="container">
		<h1 style="color: #8B2842;">찾아주겠솜🏠</h1>
		<form name="form" method="POST"
			action="<c:url value='/user/register' />">
			<table style="width: 100%">
				<tr>
					<td>
						<table>
							<tr>
								<td colspan=3 style="text-align: center;"><h1>축하합니다</h1>
								<h2>회원가입 신청이 완료되었습니다!</h2>
								<h2>관리자가 재학생 인증을 승인하면,</h2>
								<h2>'찾아주겠솜' 서비스를 이용하실 수 있습니다.</h2>
								<h2>(재학생 인증은 최대 2일 소요됩니다!)</h2>
								</td>
							</tr>
						</table>
						<table>
							<tr>
								<td><input type="button" id="btn" value="메인으로"
									onClick="userList('<c:url value='/user/list' />')"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>