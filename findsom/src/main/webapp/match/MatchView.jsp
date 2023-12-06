<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MatchView</title>
<link rel=stylesheet href="<c:url value='../css/Match.css' />" >
<script>

function postMatch() {
	//form.action = targetUri;
	//form.method = "POST";
	form.submit();
}

</script>
</head>

<body>
	<span>
		<h1>매칭 게시판</h1>
		<div>원하는 생활패턴을 선택하고 룸메이트를 찾아보세요!</div>
	  	<h3>룸메이트의 생활패턴</h3>
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
				<span>mbti</span>
			    <input type="text" id="mbti" name="mbti" />
			</div>
		</div>
		<input type="button" value="매칭" onClick="postMatch()">
		</form>
	</span>
	<div class="resultArea">
		<c:forEach var="user" items="${matchingResult}">
			<div class="resultBox">
				<a href="<c:url value='/match/detail'><c:param name='userId' value='${user.userID}'/></c:url>">
				<h1>${user.nickname}</h1>
				<h3>${user.cnt}</h3>
			</div>
		</c:forEach>
	</div>	
</body>
</html>