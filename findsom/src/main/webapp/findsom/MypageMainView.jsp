<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MypageMainView</title>
<link rel=stylesheet href="<c:url value='../css/mainView.css' />">
</head>
<body>
	<div class="d-flex" id="wrapper">
		<!-- Sidebar-->
		<div class="border-end bg-white" id="sidebar-wrapper">
			<div class="col-12 sidebar-heading border-bottom bg-beige">사용자 이름 (모집상태)</div>
			<div class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">마이페이지</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">구인 게시판</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">매칭 게시판</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">쉿! 게시판</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">자유 게시판</a>
			</div>
		</div>
		<!-- Page content wrapper-->
		<div>
			<!-- Top navigation-->
                <nav class="navbar navbar-expand-lg navbar-light bg-beige border-bottom">
                    <div class="container-fluid">
                    <div style="font-size: 1.2rem; color: #8B2842;">찾아주겠솜🏠</div>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                                <li class="nav-item active"><a class="nav-link" href="#!">알림함</a></li>
                            </ul>
                        </div>
                    </div>
                </nav>

			<!-- Page content-->
			<div>
				<p>The starting state of the menu will appear collapsed on
					smaller screens, and will appear non-collapsed on larger screens.
					When toggled using the button below, the menu will change.</p>
				<p>
					Make sure to keep all page content within the
					<code>#page-content-wrapper</code>
					. The top navbar is optional, and just for demonstration. Just
					create an element with the
					<code>#sidebarToggle</code>
					ID which will toggle the menu when clicked.
				</p>
			</div>
		</div>
	</div>
</body>
</html>