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
            	<th>쪽지번호</th>
                <th>받은사람</th>
                <th>내용</th>
                <th>보낸시간</th>
                <th>보낸사람</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="message" items="${messages}">
                <tr>
                    <td>${message.messageID}</td>
                    <td>${message.receiverID}</td>
                    <td>${message.messageText}</td>
                    <td>${formattedDate(message.createAt)}</td>
                    <td>${message.senderID}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- 추가적인 내용 -->
</body>
</html>
