<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NoiseView</title>
<link rel=stylesheet href="<c:url value='./css/Noise.css' />"  type="text/css">
<script>
	function postCheck() {
		if (confirm("이 방이 소음에 관련한 장소인지 확신하십니까?")) {
			form.submit();
		}
	}
</script>
</head>
<body>
	<%@ include file="../Sidebar.jsp" %>
	<%@ include file="../Header.jsp" %>
	<div class="container">
		<h1 class="naviText"> 쉿! 게시판 </h1><br/>
		<div class="item">
			<div class="itemMyNoise"> 내가 받은 민원 : ${myNoiseCount} </div>	
			<div class="itemText">신고자의 익명은 보장되며, <br/>신고는 한달의 1번만 가능합니다.<br/>소음 민원을 제출하기 전에, <br/>이 호수가 소음 이슈와 관련이 있는지 확인하세요.<br/></div>
			<form class="itemForm" name="form" method="POST" action="<c:url value='/noise'/>">
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
			<div class="rankTitle">소음 랭킹</div><br/>			
			<c:forEach var="ranking" items="${noiseRanking}">
					<div class="rankBox">${ranking.roomInfo} ( ${ranking.count} )</div>
			</c:forEach> 
			
		</div>
	</div>
</body>
</html>