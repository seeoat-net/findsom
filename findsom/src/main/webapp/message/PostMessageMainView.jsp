<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.dto.MessageDTO" %>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%! 
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formattedDate(java.util.Date date) {
        return dateFormat.format(date);
    }
%>

<html>
<head>
    <title>PostMessageMainView</title>
</head>
<body>
    <h2>내가 받은 쪽지 목록</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>내용</th>
                <th>작성일</th>
                <th>보낸 사람</th>
                <!-- 필요에 따라 더 많은 열을 추가할 수 있습니다. -->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="message" items="${messages}">
                <tr>
                    <td>${message.messageID}</td>
                    <td>${message.messageText}</td>
                    <td>${formattedDate(message.createAt)}</td>
                    <td>${message.senderID}</td>
                    <!-- 필요에 따라 더 많은 열을 추가할 수 있습니다. -->
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- 필요에 따라 추가적인 내용을 표시할 수 있습니다. -->
</body>
</html>
