<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList" %>
<%@page import="model.dto.MatchDTO" %>
<%
	ArrayList<MatchDTO> matchingResult = (ArrayList<MatchDTO>) request.getAttribute("matchingResult");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MatchView</title>
<link rel=stylesheet href="<c:url value='../css/Match.css' />" >
<script>

function postMatch() {
	//form.action = targetUri;
	//form.method="POST";
	form.submit();
}

</script>
</head>

<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<div class="matchBox">
		<h1>매칭 게시판</h1>
		<div>원하는 생활패턴을 선택하고 룸메이트를 찾아보세요!</div>
		<div class="mateText">룸메이트의 생활패턴</div>
		<form name="form" method="POST" action="<c:url value='/match/matching'/>">
		  	<div class="container">	
			 	<div class="item">
					<input type="checkbox" id="morning" name="lifePatterns" value="morning" />
				    <label for="morning">아침형</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="night" name="lifePatterns" value="night" />
				    <label for="night">저녁형</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="smoker" name="lifePatterns" value="smoker" />
				    <label for="smoker">흡연자</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="nonSmoker" name="lifePatterns" value="nonSmoker" />
				    <label for="nonSmoker">비흡연자</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="semester" name="lifePatterns" value="semester" />
				    <label for="semester">학기중</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="vacation" name="lifePatterns" value="vacation" />
				    <label for="vacation">방학까지</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="morningShower" name="lifePatterns" value="morningShower" />
				    <label for="morningShower">아침 샤워</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="nightShower" name="lifePatterns" value="nightShower" />
				    <label for="nightShower">저녁 샤워</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="one" name="lifePatterns" value="one" />
				    <label for="one">알람 1개</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="many" name="lifePatterns" value="many" />
				    <label for="many">알람 여러개</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="teethGrinding" name="lifePatterns" value="teethGrinding" />
				    <label for="teethGrinding">이갈이</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="snoring" name="lifePatterns" value="snoring" />
				    <label for="snoring">코골이</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="ear" name="lifePatterns" value="ear" />
				    <label for="ear">잠귀 밝음</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="yesFriendship" name="lifePatterns" value="yesFriendship" />
				    <label for="yesFriendship">친목O</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="noFriendship" name="lifePatterns" value="noFriendship" />
				    <label for="noFriendship">친목X</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="yesEarphones" name="lifePatterns" value="yesEarphones" />
				    <label for="yesEarphones">이어폰O</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="noEarphones" name="lifePatterns" value="noEarphones" />
				    <label for="noEarphones">이어폰X</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="yesclean" name="lifePatterns" value="yesclean" />
				    <label for="yesclean">청결유지</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="noclean" name="lifePatterns" value="noclean" />
				    <label for="noclean">더러워도됨</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="yesEatInRoom" name="lifePatterns" value="yesEatInRoom" />
				    <label for="yesEatInRoom">방 안 취식O</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="noEatInRoom" name="lifePatterns" value="noEatInRoom" />
				    <label for="noEatInRoom">방 안 취식X</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="1" name="lifePatterns" value="1" />
				    <label for="1">침대 1층 선호</label>
				</div>
				<div class="item">
				    <input type="checkbox" id="2" name="lifePatterns" value="2" />
				    <label for="2">침대 2층 선호</label>
				</div>
				<div class="mbti">
					<span>mbti : </span>
				    <input type="text" id="mbti" name="mbti" style="width: 70px" />
				</div>
			</div>
		</form>
		<input type="button" value="매칭" onClick="postMatch()" class="matchingBtn" />
		<div class="resultArea">
			<c:if test="${matchingResult ne null}">
				<c:forEach var="m" items="${matchingResult}">
					<a class="resultBox" href="<c:url value='/match/detail'>
						<c:param name='matchingUserID' value='${m.userID}'/>
						<c:param name='matchingUserNickname' value='${m.nickname}'/></c:url>">
						<h3>${m.nickname}</h3> ${m.printPatterns()}
					</a>
				</c:forEach>
			</c:if>
		</div>
	</div>	
</body>
</html>