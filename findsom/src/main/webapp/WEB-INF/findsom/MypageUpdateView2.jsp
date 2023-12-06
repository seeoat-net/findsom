<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.User" %>
<%@ page import="model.service.UserManager" %>
<%
    // 세션에서 User 객체 가져오기
    User user = (User) request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signup2View</title>
<link rel=stylesheet href="<c:url value='../css/Signup.css' />">
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
								<select style="width: 240" name="isMorningPerson">
										<option value="morning">아침형</option>
										<option value="night">저녁형</option>
								</select>
								</td>
							</tr>
							<tr height="40">
								<td width="130">흡연자/비흡연자</td>
								<td width="250" style="padding-left: 10">
								<select style="width: 240" name="isSmoker">
										<option value="smoker">흡연자</option>
										<option value="nonSmoker">비흡연자</option>
								</select>
								</td>
							</tr>
							<tr height="40">
								<td width="130">입사기간</td>
								<td width="250" style="padding-left: 10">
								<select style="width: 240" name="employmentPeriod">
										<option value="semester">학기중</option>
										<option value="vacation">방학까지</option>
								</select>
								</td>
							</tr>
							<tr height="40">
								<td width="130">MBTI</td>
								<td width="250" style="padding-left: 10">
								<select style="width: 240" name="mbti">
										<option value="istj">ISTJ</option>
										<option value="isfj">ISFJ</option>
										<option value="infj">INFJ</option>
										<option value="intj">INTJ</option>
										<option value="istp">ISTP</option>
										<option value="isfp">ISFP</option>
										<option value="infp">INFP</option>
										<option value="intp">INTP</option>
										<option value="estp">ESTP</option>
										<option value="esfp">ESFP</option>
										<option value="enfp">ENFP</option>
										<option value="entp">ENTP</option>
										<option value="estj">ESTJ</option>
										<option value="esfj">ESFJ</option>
										<option value="enfj">ENFJ</option>
										<option value="entj">ENTJ</option>
								</select>
								</td>
							</tr>
							<tr height="40">
								<td width="130">샤워시간</td>
								<td width="250" style="padding-left: 10">
								<select style="width: 240" name="showerTime">
										<option value="morning">아침</option>
										<option value="night">밤</option>
								</select>
								</td>
							</tr>
							<tr height="40">
								<td width="130">기상시간(알람)</td>
								<td width="250" style="padding-left: 10">
								<select style="width: 240" name="wakeUpTime">
										<option value="one">알람 한개</option>
										<option value="many">알람 여러개</option>
								</select>
								</td>
							</tr>
							<tr height="40">
								<td width="130">잠버릇</td>
								<td width="250" style="padding-left: 10">
								<select multiple style="width: 240" name="sleepHabits">
										<option value="none">없음</option>
										<option value="teethGrinding">이갈이</option>
										<option value="snoring">코골이</option>
										<option value="ear">잠귀 밝음</option>
								</select>
								</td>
							</tr>
							<!-- 
							<tr height="40">
								<td width="130">잠버릇</td>
								<td width="250" style="padding-left: 10">
								<input type="checkbox" name="sleepHabits" value="teethGrinding">이갈이
								<input type="checkbox" name="sleepHabits" value="snoring">코골이
								<input type="checkbox" name="sleepHabits" value="ear">잠귀 밝음
								</td>
							</tr>
							 -->
							<tr height="40">
								<td width="130">친목</td>
								<td width="250" style="padding-left: 10">
								<select style="width: 240" name="hasFriendship">
										<option value="yesFriendship">친목O</option>
										<option value="noFriendship">친목X</option>
								</select>
								</td>
							</tr>
							<tr height="40">
								<td width="130">방에서 이어폰 착용</td>
								<td width="250" style="padding-left: 10">
								<select style="width: 240" name="hasEarphones">
										<option value="yesEarphones">이어폰O</option>
										<option value="noEarphones">이어폰X</option>
								</select>
								</td>
							</tr>
							<tr height="40">
								<td width="130">청결도</td>
								<td width="250" style="padding-left: 10">
								<select style="width: 240" name="cleanliness">
										<option value="yesclean">청결유지</option>
										<option value="noclean">더러워도됨</option>
								</select>
								</td>
							</tr>
							<tr height="40">
								<td width="130">방 안 취식</td>
								<td width="250" style="padding-left: 10">
								<select style="width: 240" name="eatInRoom">
										<option value="yesEatInRoom">방 안 취식O</option>
										<option value="noEatInRoom">방 안 취식X</option>
								</select>
								</td>
							</tr>
							<tr height="40">
								<td width="130">나이</td>
								<td width="250" style="padding-left: 10">
								<input type="text" style="width: 240" name="age"
									placeholder="숫자로만 입력. 예) 21" required></td>
							</tr>
							<tr height="40">
								<td width="130">우대사항</td>
								<td width="250" style="padding-left: 10">
								<input type="text" style="width: 240" name="preferences"
									placeholder="없으면 '없음' 입력" required></td>
							</tr>
							<tr height="40">
								<td width="130">침대 1층/2층 선호</td>
								<td width="250" style="padding-left: 10">
								<select style="width: 240" name="bedPreference">
										<option value="1">1층</option>
										<option value="2">2층</option>
								</select>
								</td>
							</tr>
						</table> <br>
						<table>
							<tr>
								<td><input type="button" id="btn" value="완료"
									onClick="userList('<c:url value='/findsom/SignupCompleteView.jsp' />')"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>