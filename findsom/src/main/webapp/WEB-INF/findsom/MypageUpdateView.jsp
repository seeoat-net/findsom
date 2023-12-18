<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	User user = (User)request.getAttribute("user");
	LifePattern lifePattern = (LifePattern)request.getAttribute("lifePattern");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MypageUpdateView</title>
<link rel=stylesheet href="<c:url value='../css/mainView.css' />">
<script>
function userModify() {
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}
	if (form.password.value != form.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form.name.focus();
		return false;
	}
	if (form.name.value == "") {
		alert("이름을 입력하십시오.");
		form.name.focus();
		return false;
	}
	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(emailExp.test(form.email.value)==false) {
		alert("이메일 형식이 올바르지 않습니다.");
		form.email.focus();
		return false;
	}
	var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
	if(phoneExp.test(form.phone.value)==false) {
		alert("전화번호 형식이 올바르지 않습니다.");
		form.phone.focus();
		return false;
	}
	form.submit();
}

function userList(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<body>
	<div class="d-flex" id="wrapper">
		<!-- Sidebar-->
		<div class="border-end bg-white" id="sidebar-wrapper">
			<div class="col-12 sidebar-heading border-bottom bg-beige">사용자 이름 (모집상태)</div>
			<div class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">마이페이지</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">구인 게시판</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<c:url value='/match/matching' />" >매칭 게시판</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">쉿! 게시판</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">자유 게시판</a>
			</div>
		</div>
		<!-- Page content wrapper-->
		<div>
			<!-- Top navigation-->
                <nav class="navbar navbar-expand-lg navbar-light bg-beige border-bottom">
                    <div class="container-fluid">
                    <div style="font-size: 1.2rem; color: #8B2842;">찾아주겠솜🏠</div>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" 
                        aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                                <li class="nav-item active"><a class="nav-link" href="#!">알림함</a></li>
                            </ul>
                        </div>
                    </div>
                </nav>

			<!-- Page content-->
		<div align="center" id="container" style="width: 1465px">
			<h1 style="color: #8B2842;">마이페이지</h1>
				<form name="form" method="POST"
				action="<c:url value='/user/register' />">
				<table style="width: 100%">
					<tr>
						<td>
							<!-- 회원가입이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 --> <c:if
								test="${registerFailed}">
								<font color="red"><c:out value="${exception.getMessage()}" /></font>
							</c:if>
							<table>
								<tr>
									<td colspan=3 style="text-align: center;"><h2>사용자 기본 정보 수정</h2></td>
								</tr>
								<tr height="40">
									<td colspan=3 style="font-size: 14px; text-align: center;">
									사용자 ID는 수정할 수 없습니다.
									</td>
								</tr>
								<tr height="40">
									<td width="130">이메일</td>
									<td width="250" style="padding-left: 10"><input type="text"
									style="width: 240" name="email" value="<%= user.getEmail() %>"></td>
									<td width="70"><input type="button" id="btn" value="중복확인"
										onClick="userCreate(
									'<c:url value='/user/register'/>')">
									</td>
								</tr>
								<tr height="40">
									<td width="130">사용자 ID</td>
									<td width="250" style="padding-left: 10"><input type="text"
										style="width: 240;" name="userId" value="<%= user.getUserId() %>" disabled>
										<input type="hidden" name="userId" value="<%= user.getUserId() %>">
										</td>
								</tr>
								<tr height="40">
									<td width="130">비밀번호</td>
									<td width="250" style="padding-left: 10"><input
									type="password" style="width: 240" name="password" value="${user.password}"
									placeholder="4자 이상, 영문자와 숫자의 조합" required></td>
								</tr>
								<tr height="40">
									<td width="130">비밀번호 확인</td>
									<td width="250" style="padding-left: 10"><input
									type="password" style="width: 240" name="password2" value="${user.password}"
									placeholder="비밀번호를 다시 입력해주세요." required></td>
								</tr>
								<tr height="40">
									<td width="130">전화번호</td>
									<td width="250" style="padding-left: 10"><input type="text"
										style="width: 240" name="phone" value="<%= user.getPhone() %>"></td>
									<td width="70"><input type="button" id="btn" value="중복확인"
										onClick="userCreate(
										'<c:url value='/user/register'/>')">
									</td>
								</tr>
								<tr height="40">
									<td width="130">이름</td>
									<td width="250" style="padding-left: 10"><input type="text"
										style="width: 240" name="name" value="<%= user.getName() %>"></td>
								</tr>
								<tr height="40">
									<td width="130">닉네임</td>
									<td width="250" style="padding-left: 10"><input type="text"
										style="width: 240" name="nickname" value="<%= user.getNickname() %>"></td>
									<td width="70"><input type="button" id="btn" value="중복확인"
										onClick="userCreate(
										'<c:url value='/user/register'/>')">
									</td>
								</tr>
								<tr height="40">
									<td width="130">기숙사</td>
									<td width="250" style="padding-left: 10">
									<select style="width: 240" name="isRecruite">
										<option value="recruiting">모집중</option>
										<option value="recruited">모집완료</option>
									</select>
									</td>
								</tr>
								<tr height="40">
									<td width="130">기숙사 정보</td>
									<td width="250" style="padding-left: 10">
									<select style="width: 240" name="roomInfo">
										<option value="none">배정받지 않음</option>
										<option value="1-101">1기숙사 101호</option>
										<option value="1-102">1기숙사 102호</option>
										<option value="1-103">1기숙사 103호</option>
										<option value="1-104">1기숙사 104호</option>
										<option value="1-105">1기숙사 105호</option>
										<option value="2-201">2기숙사 201호</option>
										<option value="2-202">2기숙사 202호</option>
										<option value="2-203">2기숙사 203호</option>
										<option value="2-204">2기숙사 204호</option>
										<option value="2-205">2기숙사 205호</option>
									</select>
									</td>
								</tr>
							</table> <br>
							<table>
								<tr>
									<td><input type="button" id="btn" value="완료"
										onClick="userList('<c:url value='/user/updateUser' />')"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</form>
		</div>
		</div>
	</div>
</body>
</html>