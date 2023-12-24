<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.dto.User" %>
<%@ page import="model.manager.UserManager" %>
<%
    // 세션에서 User 객체 가져오기
    User user = (User) request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signup2View</title>
<link rel=stylesheet href="<c:url value='../css/Signup2.css' />">
<script>
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
						<table>
							<tr>
								<td colspan=3 style="text-align: center;"><h2>회원가입 -
										생활패턴 체크</h2></td>
							</tr>
							<tr height="40">
								<td colspan=3 style="font-size: 14px; text-align: center;">
								본인의 생활패턴에 해당하는 것을 모두 선택해주세요!<br>
								이 정보는 룸메이트 구인, 매칭 시에 활용됩니다.
								</td>
							</tr>
							<tr height="40">
							    <td width="130">아침형/저녁형</td>
							    <td width="250" style="padding-left: 10">
							        <label><input type="checkbox" name="isMorningPerson" value="morning"> 아침형</label>
							        <label><input type="checkbox" name="isMorningPerson" value="night"> 저녁형</label>
							    </td>
							</tr>
							<tr height="40">
								<td width="130">흡연자/비흡연자</td>
								<td width="250" style="padding-left: 10">
									<label><input type="checkbox" name="isSmoker" value="smoker"> 흡연자</label>
							        <label><input type="checkbox" name="isSmoker" value="nonSmoker"> 비흡연자</label>
								</td>
							</tr>
							<tr height="40">
								<td width="130">입사기간</td>
								<td width="250" style="padding-left: 10">
									<label><input type="checkbox" name="employmentPeriod" value="semester"> 학기중</label>
							        <label><input type="checkbox" name="employmentPeriod" value="vacation"> 방학까지</label>
								</td>
							</tr>
							<tr height="40">
								<td width="130">MBTI</td>
								<td width="250" style="padding-left: 10">
									<input id="input_txt" style="width: 240" name="mbti" 
										placeholder="[필수] 소문자로 입력. ex) istj" required>
								</td>
							</tr>
							<tr height="40">
								<td width="130">샤워시간</td>
								<td width="250" style="padding-left: 10">
									<label><input type="checkbox" name="showerTime" value="morningShower"> 아침</label>
							        <label><input type="checkbox" name="showerTime" value="nightShower"> 밤</label>
								</td>
							</tr>
							<tr height="40">
								<td width="130">기상시간(알람)</td>
								<td width="250" style="padding-left: 10">
									<label><input type="checkbox" name="wakeUpTime" value="one"> 알람 한개</label>
							        <label><input type="checkbox" name="wakeUpTime" value="many"> 알람 여러개</label>
								</td>
							</tr>
							<tr height="40">
								<td width="130">잠버릇</td>
								<td width="250" style="padding-left: 10">
									<label><input type="checkbox" name="sleepHabits" value="teethGrinding"> 이갈이</label>
							        <label><input type="checkbox" name="sleepHabits" value="snoring"> 코골이</label>
							        <label><input type="checkbox" name="sleepHabits" value="ear"> 잠귀 밝음</label>
								</td>
							</tr>
							<tr height="40">
								<td width="130">친목</td>
								<td width="250" style="padding-left: 10">
									<label><input type="checkbox" name="hasFriendship" value="yesFriendship"> 친목O</label>
							        <label><input type="checkbox" name="hasFriendship" value="noFriendship"> 친목X</label>
								</td>
							</tr>
							<tr height="40">
								<td width="130">방에서 이어폰 착용</td>
								<td width="250" style="padding-left: 10">
									<label><input type="checkbox" name="hasEarphones" value="yesEarphones"> 이어폰O</label>
							        <label><input type="checkbox" name="hasEarphones" value="noEarphones"> 이어폰X</label>
								</td>
							</tr>
							<tr height="40">
								<td width="130">청결도</td>
								<td width="250" style="padding-left: 10">
									<label><input type="checkbox" name="cleanliness" value="yesclean"> 청결유지</label>
							        <label><input type="checkbox" name="cleanliness" value="noclean"> 더러워도됨</label>
								</td>
							</tr>
							<tr height="40">
								<td width="130">방 안 취식</td>
								<td width="250" style="padding-left: 10">
									<label><input type="checkbox" name="eatInRoom" value="yesEatInRoom"> 방 안 취식O</label>
							        <label><input type="checkbox" name="eatInRoom" value="noEatInRoom"> 방 안 취식X</label>
								</td>
							</tr>
							<tr height="40">
								<td width="130">나이</td>
								<td width="250" style="padding-left: 10">
									<input id="input_txt" type="text" style="width: 240" name="age" 
										placeholder="[필수] 숫자로만 입력. 예) 21" required>
								</td>
							</tr>
							<tr height="40">
								<td width="130">침대 1층/2층 선호</td>
								<td width="250" style="padding-left: 10">
									<label><input type="checkbox" name="bedPreference" value="1"> 1층</label>
							        <label><input type="checkbox" name="bedPreference" value="2"> 2층</label>
								</td>
							</tr>
						</table> <br>
						<table>
							<tr>
								<td><input type="button" id="btn" value="완료" 
								onClick="userList('<c:url value='/user/registerLifePattern'/>')"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>