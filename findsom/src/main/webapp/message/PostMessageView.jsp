<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>쪽지 보내기</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .message-box {
            border: 1px solid #ccc;
            padding: 20px;
            margin: 20px 0;
        }
        .button {
            background-color: #800000;
            color: white;
            padding: 10px 20px;
            text-align: center;
            display: inline-block;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #a00000;
        }
        label { display: block; margin-top: 20px; }
        textarea { width: 100%; }
    </style>
</head>
<body>
    <div class="message-box">
       <form action="/message/write" method="post">
            <label for="messageText">쪽지 내용:</label>
            <textarea id="messageText" name="messageText" rows="4" required></textarea>
            
            <input type="submit" class="button" value="전송">
        </form>
    </div>
</body>
</html>
