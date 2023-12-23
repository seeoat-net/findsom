<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.dto.MessageDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>쪽지 목록</title>
    <!-- 여기에 스타일시트 및 JavaScript 파일을 포함시킬 수 있습니다. -->
</head>
<body>
    <h2>내가 받은 쪽지 목록</h2>
    <table border="1">
        <thead>
            <tr>
                <th>쪽지번호</th>
                <th>보낸사람</th>
                <th>내용</th>
                <th>보낸시간</th>
                <th>관련 게시판</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="message" items="${messages}">
                <tr>
                    <td>${message.messageID}</td>
                    <td>${message.senderID}</td>
                    <td>${message.messageText}</td>
                    <td><fmt:formatDate value="${message.createAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td>${message.freepostID ne null ? '자유게시판' : message.findpostID ne null ? '찾기게시판' : 'N/A'}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
