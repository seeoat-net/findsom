<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FindPost</title>
<link rel=stylesheet href="<c:url value='../css/mainView.css' />">
<link rel=stylesheet href="<c:url value='../css/findcheck.css' />">
</head>
<body>
	<div class="leftline"></div>
	<div class="rightline"></div>
	<div class="somsom"></div>
	<span  class="title">찾아주겠솜🏠</span>
	<div class="bell"></div>
	<div class="line"></div>
	<span  class="mypage">마이페이지</span>
	<div class="line1"></div>
	<span  class="find">
  		<a href="FindMainView.jsp">구인 게시판</a>
  	</span>
  	<div class="line2"></div>
  	<span  class="match">매칭 게시판</span>
  	<div class="line3"></div>
  	<span  class="shit">쉿! 게시판</span>
  	<div class="line4"></div>
  	<span  class="free">
  		<a href="../free/FreeMainView.jsp">자유 게시판</a>
	</span> 
	<div class="main">
	  	<button class="cancle" onclick="history.back();">취소</button>
	  	<button class="register" type="submit" form="postform">등록</button>
	  	<div>작성글 확인</div>
	</div>
</body>
</html>
<%--
<%
    // 사용자가 입력한 데이터 가져오기
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String isAnonymous = request.getParameter("isAnonymous");
    
    // DAO 클래스의 create 메서드를 호출하여 데이터베이스에 데이터 저장
    FindDAO findDAO = new FindDAO();
    FindDTO post = new FindDTO();
    post.setTitle(title);
    post.setContent(content);
    post.setIsAnonymous(isAnonymous);
    
    FindDTO result = findDAO.create(post);
    try {
        FindDTO result = findDAO.create(post); // create 메서드를 사용하여 데이터베이스에 데이터 저장
        if (result != null) {
            // 데이터베이스에 성공적으로 저장되었을 때 처리할 코드 작성
            response.sendRedirect("SuccessPage.jsp"); // 성공 페이지로 이동
        } else {
            // 데이터베이스 저장에 실패한 경우 처리할 코드 작성
            response.sendRedirect("ErrorPage.jsp"); // 에러 페이지로 이동
        }
    } catch (SQLException e) {
        e.printStackTrace();
        response.sendRedirect("ErrorPage.jsp"); // 에러 페이지로 이동
    }
--%>