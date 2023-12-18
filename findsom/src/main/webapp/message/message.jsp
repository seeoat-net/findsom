<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Message Form</title>
</head>
<body>

<h2>Write a Message</h2>

<form action="MessageController" method="post">
    <input type="hidden" name="action" value="sendMessage">

    <label for="messageText">Message Text:</label>
    <textarea name="messageText" id="messageText" rows="4" cols="50" required></textarea>
    <br>


    <input type="submit" value="Send Message">
</form>

</body>
</html>

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/message.css">
</head>
<body>
<form>
<div class=e30_1669>
  <div class="e30_1670"></div><span  class="e30_1671">알림함</span>
  <div class="e30_1672"></div>
  <div class="e30_1673"></div>
  <div class="e30_1674"></div>
  <div class="e30_1675"></div><span  class="e30_1676">작성글</span><span  class="e30_1677">댓글 단 글</span><span  class="e30_1678">쪽지함</span><span  class="e30_1679">작성댓글</span>
  <div class="e30_1680"></div><span  class="e30_1682">x</span><span  class="e30_1684">쪽지 보내기</span>
  <div class="e30_1691"></div>
  <div class="e30_1692"></div><span  class="e30_1693">내용을 입력하세요. </span>
  <div class="e30_1870"></div><span  class="e30_1871">전송</span>
</div>
</form>
</body>
</html> --%>


