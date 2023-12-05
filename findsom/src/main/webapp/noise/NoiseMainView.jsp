<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel=stylesheet href="<c:url value='../css/Noise.css' />" >
<script>
function postCheck(targetUri) {
	if (confirm("이 방이 소음에 관련한 장소인지 확신하십니까?")) {
		form.submit();
	}
}

function postNoise(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<body>
	<h1>쉿! 게시판</h1>
	<h3>내가 받은 민원 : ${myNoiseCount} </h3>
	<div class="container">
		<div class="item">
			<form name="form" method="POST" action="<c:url value='/noise/report/form'/>">
				<select name="residence" id="residence">
				    <option value="1">1기숙사</option>
				    <option value="2">2기숙사</option>
			  	</select>
			  	<select name="floor" id="floor">
				    <option value="01">1층</option>
				    <option value="02">2층</option>
				    <option value="03">3층</option>
				    <option value="04">4층</option>
				    <option value="05">5층</option>
				    <option value="06">6층</option>
				    <option value="07">7층</option>
				    <option value="08">8층</option>
				    <option value="09">9층</option>
				    <option value="10">10층</option>
			  	</select>
			  	<select name="room" id="room">
				    <option value="01">1호</option>
				    <option value="02">2호</option>
				    <option value="03">3호</option>
				    <option value="04">4호</option>
				    <option value="05">5호</option>
				    <option value="06">6호</option>
				    <option value="07">7호</option>
				    <option value="08">8호</option>
				    <option value="09">9호</option>
				    <option value="10">10호</option>
			  	</select>
				<input type="button" value="신고" onClick="postCheck()">
			</form>
		</div>
		
		<div class="rankArea">
			<h3>소음 랭킹</h3>
			<c:forEach var="ranking" items="${noiseRanking}">
				<div class="rankBox">
					<h1>${ranking.roomInfo} ( ${ranking.count} )</h1>
				</div>
			</c:forEach>
			
		</div>
	
	</div>

</body>
</html>