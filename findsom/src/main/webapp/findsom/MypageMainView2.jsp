<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<div align="center" id="container">
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
									<td colspan=3 style="text-align: center;"><h2>생활패턴</h2></td>
								</tr>
								<tr height="40">
									<td width="130">아침형/저녁형</td>
									<td width="250" style="padding-left: 10">${lifepattern.isMorningPerson}
									</td>
								</tr>
								<tr height="40">
									<td width="130">흡연자/비흡연자</td>
									<td width="250" style="padding-left: 10">${lifepattern.isSmoker}
									</td>
								</tr>
								<tr height="40">
									<td width="130">입사기간</td>
									<td width="250" style="padding-left: 10">
									${lifepattern.employmentPeriod}</td>
								</tr>
								<tr height="40">
									<td width="130">MBTI</td>
									<td width="250" style="padding-left: 10">
									${lifepattern.mbti}</td>
								</tr>
								<tr height="40">
									<td width="130">샤워시간</td>
									<td width="250" style="padding-left: 10">
									${lifepattern.showerTime}</td>
								</tr>
								<tr height="40">
									<td width="130">기상시간(알람)</td>
									<td width="250" style="padding-left: 10">
									${lifepattern.wakeUpTime}</td>
								</tr>
								<tr height="40">
									<td width="130">잠버릇</td>
									<td width="250" style="padding-left: 10">
									${lifepattern.sleepHabits}</td>
								</tr>
								<tr height="40">
									<td width="130">친목</td>
									<td width="250" style="padding-left: 10">
									${lifepattern.hasFriendship}</td>
								</tr>
								<tr height="40">
									<td width="130">방에서 이어폰 착용</td>
									<td width="250" style="padding-left: 10">
									${lifepattern.hasEarphones}</td>
								</tr>
								<tr height="40">
									<td width="130">청결도</td>
									<td width="250" style="padding-left: 10">
									${lifepattern.cleanliness}</td>
								</tr>
								<tr height="40">
									<td width="130">방 안 취식</td>
									<td width="250" style="padding-left: 10">
									${lifepattern.eatInRoom}</td>
								</tr>
								<tr height="40">
									<td width="130">나이</td>
									<td width="250" style="padding-left: 10">
									${lifepattern.age}</td>
								</tr>
								<tr height="40">
									<td width="130">우대사항</td>
									<td width="250" style="padding-left: 10">
									${lifepattern.preferences}</td>
								</tr>
								<tr height="40">
									<td width="130">침대 1층/2층 선호</td>
									<td width="250" style="padding-left: 10">
									${lifepattern.bedPreference}</td>
								</tr>
							</table> <br>
							<table>
								<tr>
									<td><input type="button" id="btn" value="수정하기"
										onClick="userList('<c:url value='/findsom/Signup2View.jsp' />')"></td>
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