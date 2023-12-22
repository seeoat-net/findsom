<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<link rel=stylesheet href="<c:url value='/css/Header.css' />" type="text/css">
<script>

function toNoti(targetUri) {
	form.action = targetUri;
	form.method = "GET";
	form.submit();
}

</script>
</head>
<body>
	<div class="headerWrap" >
		<div></div>
		<div class="headerTitle">찾아주겠솜🏠</div>
		<img class="bellImg" 
			alt="../images/bell.png" src="../images/bell.png" 
			onClick="toNoti('<c:url value='/notification/post'/>')"
		/>
	</div>
</body>
</html>