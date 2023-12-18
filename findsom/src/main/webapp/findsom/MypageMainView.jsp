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
<title>MypageMainView</title>
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
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">매칭 게시판</a>
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
				<form name="form" method="POST">
				<table style="width: 100%">
					<tr>
						<td>
							<table>
								<tr>
									<td colspan=3 style="text-align: center;"><h2>사용자 기본 정보</h2></td>
								</tr>
								<tr height="40">
									<td width="130">이메일</td>
									<td width="250" style="padding-left: 10"><%=user.getEmail()%>
									<input type="hidden" name="email" value="<%= user.getEmail() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">사용자 ID</td>
									<td width="250" style="padding-left: 10"><%=user.getUserId()%>
									<input type="hidden" name="userId" value="<%= user.getUserId() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">비밀번호</td>
									<td width="250" style="padding-left: 10"><%=user.getPassword()%>
									<input type="hidden" name="password" value="<%= user.getPassword() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">전화번호</td>
									<td width="250" style="padding-left: 10"><%=user.getPhone()%>
									<input type="hidden" name="phone" value="<%= user.getPhone() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">이름</td>
									<td width="250" style="padding-left: 10"><%=user.getName()%>
									<input type="hidden" name="name" value="<%= user.getName() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">닉네임</td>
									<td width="250" style="padding-left: 10"><%=user.getNickname()%>
									<input type="hidden" name="nickname" value="<%= user.getNickname() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">기숙사</td>
									<td width="250" style="padding-left: 10"><%=user.isRecruite()%>
									<input type="hidden" name="isRecruite" value="<%= user.isRecruite() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">기숙사 정보</td>
									<td width="250" style="padding-left: 10"><%=user.getRoomInfo()%>
									<input type="hidden" name="roomInfo" value="<%= user.getRoomInfo() %>">
									</td>
								</tr>
								
								<!-- 생활패턴 부분 
								<tr height="40">
									<td width="130">아침형/저녁형</td>
									<td width="250" style="padding-left: 10"><%=lifePattern.getIsMorningPerson()%>
									<input type="hidden" name="isMorningPerson" value="<%= lifePattern.getIsMorningPerson() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">흡연자/비흡연자</td>
									<td width="250" style="padding-left: 10"><%=lifePattern.getIsSmoker()%>
									<input type="hidden" name="isSmoker" value="<%= lifePattern.getIsSmoker() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">입사기간</td>
									<td width="250" style="padding-left: 10"><%=lifePattern.getEmploymentPeriod()%>
									<input type="hidden" name="employmentPeriod" value="<%= lifePattern.getEmploymentPeriod() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">MBTI</td>
									<td width="250" style="padding-left: 10"><%=lifePattern.getMbti()%>
									<input type="hidden" name="mbti" value="<%= lifePattern.getMbti() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">샤워시간</td>
									<td width="250" style="padding-left: 10"><%=lifePattern.getShowerTime()%>
									<input type="hidden" name="showerTime" value="<%= lifePattern.getShowerTime() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">기상시간(알람)</td>
									<td width="250" style="padding-left: 10"><%=lifePattern.getWakeUpTime()%>
									<input type="hidden" name="wakeUpTime" value="<%= lifePattern.getWakeUpTime() %>">
									</td>
								</tr>
								
								
								<tr height="40">
									<td width="130">잠버릇</td>
									<td width="250" style="padding-left: 10"><%=lifePattern.getTeethGrinding()%>
									<input type="hidden" name="sleepHabits" value="<%= lifePattern.getTeethGrinding() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">잠버릇</td>
									<td width="250" style="padding-left: 10"><%=lifePattern.getSnoring()%>
									<input type="hidden" name="sleepHabits" value="<%= lifePattern.getSnoring() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">잠버릇</td>
									<td width="250" style="padding-left: 10"><%=lifePattern.getEar()%>
									<input type="hidden" name="sleepHabits" value="<%= lifePattern.getEar() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">친목</td>
									<td width="250" style="padding-left: 10"><%=lifePattern.getHasFriendship()%>
									<input type="hidden" name="hasFriendship" value="<%= lifePattern.getHasFriendship() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">방에서 이어폰 착용</td>
									<td width="250" style="padding-left: 10"><%=lifePattern.getHasEarphones()%>
									<input type="hidden" name="hasEarphones" value="<%= lifePattern.getHasEarphones() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">청결도</td>
									<td width="250" style="padding-left: 10"><%=lifePattern.getCleanliness()%>
									<input type="hidden" name="cleanliness" value="<%= lifePattern.getCleanliness() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">방 안 취식</td>
									<td width="250" style="padding-left: 10"><%=lifePattern.getEatInRoom()%>
									<input type="hidden" name="eatInRoom" value="<%= lifePattern.getEatInRoom() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">나이</td>
									<td width="250" style="padding-left: 10"><%=lifePattern.getAge()%>
									<input type="hidden" name="age" value="<%= lifePattern.getAge() %>">
									</td>
								</tr>
								<tr height="40">
									<td width="130">침대 1층/2층 선호</td>
									<td width="250" style="padding-left: 10"><%=lifePattern.getBedPreference()%>
									<input type="hidden" name="bedPreference" value="<%= lifePattern.getBedPreference() %>">
									</td>
								</tr>
								-->
							</table> <br>
							<table>
								<tr>
									<td><input type="button" id="btn" value="수정하기"
										onClick="userList('<c:url value='/user/updateForm' />')"></td>
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
