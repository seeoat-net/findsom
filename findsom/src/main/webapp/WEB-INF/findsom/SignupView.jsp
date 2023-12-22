<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SignupView</title>
<link rel=stylesheet href="<c:url value='../css/Signup2.css' />">
<script>
function userCreate() {
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
	<div align="center" id="container">
		<h1 style="color: #8B2842;">찾아주겠솜🏠</h1>
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
								<td colspan=2 style="text-align: center;"><h2>회원가입</h2></td>
							</tr>
							<tr height="40">
								<td width="130">이메일</td>
								<td width="250" style="padding-left: 10"><input type="text" id="input_txt"
									style="width: 240" name="email" placeholder="이메일" required
									<c:if test="${registerFailed}">value="${user.email}"</c:if>>
								</td>
							</tr>
							<tr height="40">
								<td width="130">사용자 ID</td>
								<td width="250" style="padding-left: 10"><input type="text" id="input_txt"
									style="width: 240;" name="userId" value="${user.userId}"
									placeholder="사용자 ID를 입력해주세요." required></td>
							</tr>
							<tr height="40">
								<td width="130">비밀번호</td>
								<td width="250" style="padding-left: 10"><input id="input_txt" 
									type="password" style="width: 240" name="password" value="${user.password}"
									placeholder="4자 이상, 영문자와 숫자의 조합" required></td>
							</tr>
							<tr height="40">
								<td width="130">비밀번호 확인</td>
								<td width="250" style="padding-left: 10"><input id="input_txt"
									type="password" style="width: 240" name="password2" value="${user.password}"
									placeholder="비밀번호를 다시 입력해주세요." required></td>
							</tr>
							<tr height="40">
								<td width="130">전화번호</td>
								<td width="250" style="padding-left: 10"><input type="text" id="input_txt"
									style="width: 240" name="phone" placeholder="전화번호를 입력해주세요."
									<c:if test="${registerFailed}">value="${user.phone}"</c:if>>
								</td>
							</tr>
							<tr height="40">
								<td width="130">이름</td>
								<td width="250" style="padding-left: 10"><input type="text" id="input_txt"
									style="width: 240" name="name" placeholder="본명을 입력해주세요."
									required
									<c:if test="${registerFailed}">value="${user.name}"</c:if>>
								</td>
							</tr>
							<tr height="40">
								<td width="130">닉네임</td>
								<td width="250" style="padding-left: 10"><input type="text" id="input_txt"
									style="width: 240" name="nickname" placeholder="닉네임을 입력해주세요."
									required
									<c:if test="${registerFailed}">value="${user.nickname}"</c:if>>
								</td>
							</tr>
							<tr height="40">
								<td width="130">기숙사</td>
								<td width="250" style="padding-left: 10">
									<label><input type="radio" name="isRecruite" value="recruiting"> 모집중</label>
							        <label><input type="radio" name="isRecruite" value="recruited"> 모집완료</label>
								</td>
							</tr>
							<tr height="40">
								<td width="130">기숙사 정보</td>
								<td width="250" style="padding-left: 10">
								<select style="width: 240" name="roomInfo">
										<option value="00000">배정받지 않음</option>
										<option value="10101">1기숙사 101호</option>
										<option value="10102">1기숙사 102호</option>
										<option value="10103">1기숙사 103호</option>
										<option value="10104">1기숙사 104호</option>
										<option value="10105">1기숙사 105호</option>
										<option value="20201">2기숙사 201호</option>
										<option value="20202">2기숙사 202호</option>
										<option value="20203">2기숙사 203호</option>
										<option value="20204">2기숙사 204호</option>
										<option value="20205">2기숙사 205호</option>
								</select>
								</td>
							</tr>
							<tr height="40">
								<td width="130">재학생 인증</td>
								<td width="250" style="padding-left: 10"><input type="file"
									style="width: 240" name="studentAuthentication" required></td>
							</tr>
							<tr height="40">
								<td colspan=3 style="font-size: 14px; text-align: center;">재학생임을
									인증할 수 있는 학생증, 재학증명서 등을 첨부해주시길 바랍니다.</td>
							</tr>
						</table> <br>
						<table>
							<tr>
								<td><input type="button" id="btn" value="다음"
									onClick="userList(
								'<c:url value='/user/registerUser'/>')"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<script>
    function checkDuplicateEmail() {
        alert("이메일 중복 확인을 수행합니다.");
    }

    function checkDuplicateNickname() {
        alert("닉네임 중복 확인을 수행합니다.");
    }
	</script>
</body>
</html>