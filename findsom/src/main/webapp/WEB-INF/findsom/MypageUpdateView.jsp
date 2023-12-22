<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
   User user = (User)request.getAttribute("user");
   LifePattern lifePattern = (LifePattern)request.getAttribute("lifePattern");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MypageUpdateView</title>
<link rel=stylesheet href="<c:url value='../css/mainView.css' />">
<script>
function userModify() {
   if (form.password.value == "") {
      alert("비밀번호를 입력하십시오.");
      form.password.focus();
      return false;
   }
   if (form.password.value != form.password2.value) {
      alert("비밀번호가 일치하지 않습니다.");
      form.name.focus();
      return false;
   }
   if (form.name.value == "") {
      alert("이름을 입력하십시오.");
      form.name.focus();
      return false;
   }
   var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
   if(emailExp.test(form.email.value)==false) {
      alert("이메일 형식이 올바르지 않습니다.");
      form.email.focus();
      return false;
   }
   var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
   if(phoneExp.test(form.phone.value)==false) {
      alert("전화번호 형식이 올바르지 않습니다.");
      form.phone.focus();
      return false;
   }
   form.submit();
}

function userList(targetUri) {
   form.action = targetUri;
   form.submit();
}
</script>
</head>
<body>
   <div class="d-flex" id="wrapper">
      <!-- Sidebar-->
      <div class="border-end bg-white" id="sidebar-wrapper">
         <div class="col-12 sidebar-heading border-bottom bg-beige" style="flex-basis: 150px; border-bottom: 1.5px solid #8B2842;">
            <!-- <img style="display: block; margin-left: 20px;" alt="./../images/somsom.png" src="./../images/somsom.png" /> -->
            ${user.getNickname()} | ${user.isRecruite()}</div>
         <div class="list-group list-group-flush">
            <a
               class="list-group-item list-group-item-action list-group-item-light p-3"
               href="<c:url value='/user/mypageMain' />">마이페이지</a> <a
               class="list-group-item list-group-item-action list-group-item-light p-3"
               href="<c:url value='/find/findlist' />">구인 게시판</a> <a
               class="list-group-item list-group-item-action list-group-item-light p-3"
               href="<c:url value='/match/matching' />">매칭 게시판</a> <a
               class="list-group-item list-group-item-action list-group-item-light p-3"
               href="<c:url value='/noise' />">쉿! 게시판</a> <a
               class="list-group-item list-group-item-action list-group-item-light p-3"
               href="<c:url value='/free/freelist' />">자유 게시판</a>
         </div>
      </div>
      <!-- Page content wrapper-->
      <div>
         <!-- Top navigation-->
                <nav class="navbar navbar-expand-lg navbar-light bg-beige border-bottom">
                    <div class="container-fluid">
                    <div style="font-size: 1.2rem; color: #8B2842;">찾아주겠솜🏠</div>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" 
                        aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                                <li class="nav-item active"><a class="nav-link" href="#!">알림함</a></li>
                            </ul>
                        </div>
                    </div>
                </nav>

         <!-- Page content-->
      <div align="center" id="container" style="width: 1465px">
         <h1 style="color: #8B2842;">마이페이지</h1>
            <form name="form" method="POST" 
            action="<c:url value='/user/register' />">
            <table style="width: 100%">
               <tr>
                  <td>
                     <!-- 회원가입이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 --> <c:if
                        test="${registerFailed}">
                        <font color="red"><c:out value="${exception.getMessage()}" /></font>
                     </c:if>
                     <table>
                        <tr>
                           <td><input type="button" id="btn" value="완료"
                              onClick="userList('<c:url value='/user/updateUser' />')"></td>
                        </tr>
                     </table>
                     
                     <table style="width: 100%" cellpadding="5">
                  <tr>
                     <!-- 사용자 기본 정보 -->
                     <td valign="top">
                        <table>
                           <tr>
                              <td colspan=3 style="text-align: center;"><h2>사용자 기본 정보 수정</h2></td>
                           </tr>
                        <tr height="40">
                           <td colspan=3 style="font-size: 14px; text-align: center;">
                           사용자 ID는 수정할 수 없습니다.
                           </td>
                        </tr>
                        <tr height="40">
                           <td width="130">이메일</td>
                           <td width="250" style="padding-left: 10"><input type="text" id="input_txt"
                           style="width: 240" name="email" value="<%= user.getEmail() %>"></td>
                           <td width="70"><input type="button" id="btn" value="중복확인"
                              onClick="userCreate(
                           '<c:url value='/user/register'/>')">
                           </td>
                        </tr>
                        <tr height="40">
                           <td width="130">사용자 ID</td>
                           <td width="250" style="padding-left: 10"><input type="text"
                              style="width: 240;" name="userId" value="<%= user.getUserId() %>" disabled>
                              <input type="hidden" name="userId" value="<%= user.getUserId() %>">
                              </td>
                        </tr>
                        <tr height="40">
                           <td width="130">비밀번호</td>
                           <td width="250" style="padding-left: 10"><input id="input_txt"
                           type="password" style="width: 240" name="password" value="${user.password}"
                           placeholder="4자 이상, 영문자와 숫자의 조합" required></td>
                        </tr>
                        <tr height="40">
                           <td width="130">비밀번호 확인</td>
                           <td width="250" style="padding-left: 10"><input id="input_txt"
                           type="password" style="width: 240" name="password2" value="${user.password}"
                           placeholder="비밀번호를 다시 입력해주세요." required></td>
                        </tr>
                        <tr height="40">
                           <td width="130">전화번호</td>
                           <td width="250" style="padding-left: 10"><input type="text" id="input_txt"
                              style="width: 240" name="phone" value="<%= user.getPhone() %>"></td>
                           <td width="70"><input type="button" id="btn" value="중복확인"
                              onClick="userCreate(
                              '<c:url value='/user/register'/>')">
                           </td>
                        </tr>
                        <tr height="40">
                           <td width="130">이름</td>
                           <td width="250" style="padding-left: 10"><input type="text" id="input_txt"
                              style="width: 240" name="name" value="<%= user.getName() %>"></td>
                        </tr>
                        <tr height="40">
                           <td width="130">닉네임</td>
                           <td width="250" style="padding-left: 10"><input type="text" id="input_txt"
                              style="width: 240" name="nickname" value="<%= user.getNickname() %>"></td>
                           <td width="70"><input type="button" id="btn" value="중복확인"
                              onClick="userCreate(
                              '<c:url value='/user/register'/>')">
                           </td>
                        </tr>
                        <!-- 기숙사 정보 -->
                        <tr height="40">
                            <td width="130">기숙사</td>
                            <td width="250" style="padding-left: 10">
                                <label><input type="radio" name="isRecruite" value="recruiting" 
                                    <%= user.isRecruite() != null && user.isRecruite().equals("recruiting") ? "checked" : "" %>> 모집중</label>
                                <label><input type="radio" name="isRecruite" value="recruited" 
                                    <%= user.isRecruite() != null && user.isRecruite().equals("recruited") ? "checked" : "" %>> 모집완료</label>
                            </td>
                        </tr>
                        <%
                        // Check if the user is recruited before rendering the dormitory information section
                        if (user.isRecruite() != null && user.isRecruite().equals("recruited")) {
                        %>
                        <tr height="40">
                            <td width="130">기숙사 정보</td>
                            <td width="250" style="padding-left: 10">
                                <select style="width: 240" name="roomInfo">
                                    <option value="10101" <%= user.getRoomInfo() != null && user.getRoomInfo().equals("10101") ? "selected" : "" %>>1기숙사 101호</option>
                                    <option value="10102" <%= user.getRoomInfo() != null && user.getRoomInfo().equals("10102") ? "selected" : "" %>>1기숙사 102호</option>
                                    <option value="10103" <%= user.getRoomInfo() != null && user.getRoomInfo().equals("10103") ? "selected" : "" %>>1기숙사 102호</option>
                                    <option value="10104" <%= user.getRoomInfo() != null && user.getRoomInfo().equals("10104") ? "selected" : "" %>>1기숙사 102호</option>
                                    <option value="10105" <%= user.getRoomInfo() != null && user.getRoomInfo().equals("10105") ? "selected" : "" %>>1기숙사 102호</option>
                                    <option value="20101" <%= user.getRoomInfo() != null && user.getRoomInfo().equals("20101") ? "selected" : "" %>>2기숙사 101호</option>
                                    <option value="20102" <%= user.getRoomInfo() != null && user.getRoomInfo().equals("20102") ? "selected" : "" %>>2기숙사 102호</option>
                                    <option value="20103" <%= user.getRoomInfo() != null && user.getRoomInfo().equals("20103") ? "selected" : "" %>>2기숙사 102호</option>
                                    <option value="20104" <%= user.getRoomInfo() != null && user.getRoomInfo().equals("20104") ? "selected" : "" %>>2기숙사 102호</option>
                                    <option value="20105" <%= user.getRoomInfo() != null && user.getRoomInfo().equals("20105") ? "selected" : "" %>>2기숙사 102호</option>
                                    <!-- 다른 기숙사 정보도 동일하게 추가 -->
                                </select>
                            </td>
                        </tr>
                        <%
                        } else {
                            // If not recruited, disable the select dropdown
                        %>
                        <tr height="40">
                            <td width="130">기숙사 정보</td>
                            <td width="250" style="padding-left: 10">
                                <select style="width: 240" name="roomInfo" disabled>
                                    <option value="XXXXX" selected>배정받지 않음</option>
                                </select>
                            </td>
                        </tr>
                        <%
                        }
                        %>
                        </table>
                     </td>

                     <!-- 사용자 생활 패턴 -->
                     <td valign="top">
                        <table>
                           <tr>
                              <td colspan=2 style="text-align: center;"><h2>사용자 생활패턴</h2></td>
                           </tr>
                           <tr height="40">
                               <td width="130">아침형/저녁형</td>
                               <td width="250" style="padding-left: 10">
                                   <label><input type="checkbox" name="isMorningPerson" value="morning" 
                                   <%= lifePattern.getIsMorningPerson() != null && lifePattern.getIsMorningPerson().equals("morning") ? "checked" : "" %>> 아침형</label>
                                   <label><input type="checkbox" name="isMorningPerson" value="night" 
                                   <%= lifePattern.getIsMorningPerson() != null && lifePattern.getIsMorningPerson().equals("night") ? "checked" : "" %>> 저녁형</label>
                               </td>
                           </tr>
                           <tr height="40">
                               <td width="130">흡연자/비흡연자</td>
                               <td width="250" style="padding-left: 10">
                                   <label><input type="checkbox" name="isSmoker" value="smoker" 
                                   <%= lifePattern.getIsSmoker() != null && lifePattern.getIsSmoker().equals("smoker") ? "checked" : "" %>> 흡연자</label>
                                   <label><input type="checkbox" name="isSmoker" value="nonSmoker" 
                                   <%= lifePattern.getIsSmoker() != null && lifePattern.getIsSmoker().equals("nonSmoker") ? "checked" : "" %>> 비흡연자</label>
                               </td>
                           </tr>
                           <tr height="40">
                               <td width="130">입사기간</td>
                               <td width="250" style="padding-left: 10">
                                   <label><input type="checkbox" name="employmentPeriod" value="semester" 
                                   <%= lifePattern.getEmploymentPeriod() != null && lifePattern.getEmploymentPeriod().equals("semester") ? "checked" : "" %>> 학기중</label>
                                   <label><input type="checkbox" name="employmentPeriod" value="vacation" 
                                   <%= lifePattern.getEmploymentPeriod() != null && lifePattern.getEmploymentPeriod().equals("vacation") ? "checked" : "" %>> 방학까지</label>
                               </td>
                           </tr>
                           <tr height="40">
                               <td width="130">MBTI</td>
                               <td width="250" style="padding-left: 10">
                                   <input id="input_txt" style="width: 240" name="mbti" 
                                   placeholder="[필수] 소문자로 입력.ex) istj" required value="<%= lifePattern.getMbti() %>">
                               </td>
                           </tr>
                           <tr height="40">
                               <td width="130">샤워시간</td>
                               <td width="250" style="padding-left: 10">
                                   <label><input type="checkbox" name="showerTime" value="morningShower" 
                                   <%= lifePattern.getShowerTime() != null && lifePattern.getShowerTime().equals("morningShower") ? "checked" : "" %>> 아침</label>
                                   <label><input type="checkbox" name="showerTime" value="nightShower" 
                                   <%= lifePattern.getShowerTime() != null && lifePattern.getShowerTime().equals("nightShower") ? "checked" : "" %>> 밤</label>
                               </td>
                           </tr>
                        <tr height="40">
                           <td width="130">기상시간(알람)</td>
                           <td width="250" style="padding-left: 10">
                              <label><input type="checkbox" name="wakeUpTime" value="one" 
                              <%= lifePattern.getWakeUpTime() != null && lifePattern.getWakeUpTime().equals("one") ? "checked" : "" %>> 알람 한개</label>
                              <label><input type="checkbox" name="wakeUpTime" value="many" 
                              <%= lifePattern.getWakeUpTime() != null && lifePattern.getWakeUpTime().equals("many") ? "checked" : "" %>> 알람 여러개</label>
                           </td>
                        </tr>
                        <tr height="40">
                            <td width="130">잠버릇</td>
                            <td width="250" style="padding-left: 10">
                                <label><input type="checkbox" name="teethGrinding" value="teethGrinding" <%= lifePattern.getTeethGrinding() != null && lifePattern.getTeethGrinding().equals("teethGrinding") ? "checked" : "" %>> 이갈이</label>
                                <label><input type="checkbox" name="snoring" value="snoring" <%= lifePattern.getSnoring() != null && lifePattern.getSnoring().equals("snoring") ? "checked" : "" %>> 코골이</label>
                                <label><input type="checkbox" name="ear" value="ear" <%= lifePattern.getEar() != null && lifePattern.getEar().equals("ear") ? "checked" : "" %>> 잠귀 밝음</label>
                            </td>
                        </tr>
                        <tr height="40">
                            <td width="130">친목</td>
                            <td width="250" style="padding-left: 10">
                                <label><input type="checkbox" name="hasFriendship" value="yesFriendship" 
                                <%= lifePattern.getHasFriendship() != null && lifePattern.getHasFriendship().equals("yesFriendship") ? "checked" : "" %>> 친목O</label>
                                <label><input type="checkbox" name="hasFriendship" value="noFriendship" 
                                <%= lifePattern.getHasFriendship() != null && lifePattern.getHasFriendship().equals("noFriendship") ? "checked" : "" %>> 친목X</label>
                            </td>
                        </tr>
                        <tr height="40">
                            <td width="130">방에서 이어폰 착용</td>
                            <td width="250" style="padding-left: 10">
                                <label><input type="checkbox" name="hasEarphones" value="yesEarphones" 
                                <%= lifePattern.getHasEarphones() != null && lifePattern.getHasEarphones().equals("yesEarphones") ? "checked" : "" %>> 이어폰O</label>
                                <label><input type="checkbox" name="hasEarphones" value="noEarphones" 
                                <%= lifePattern.getHasEarphones() != null && lifePattern.getHasEarphones().equals("noEarphones") ? "checked" : "" %>> 이어폰X</label>
                            </td>
                        </tr>
                        <tr height="40">
                            <td width="130">청결도</td>
                            <td width="250" style="padding-left: 10">
                                <label><input type="checkbox" name="cleanliness" value="yesclean" 
                                <%= lifePattern.getCleanliness() != null && lifePattern.getCleanliness().equals("yesclean") ? "checked" : "" %>> 청결유지</label>
                                <label><input type="checkbox" name="cleanliness" value="noclean" 
                                <%= lifePattern.getCleanliness() != null && lifePattern.getCleanliness().equals("noclean") ? "checked" : "" %>> 더러워도됨</label>
                            </td>
                        </tr>
                        <tr height="40">
                            <td width="130">방 안 취식</td>
                            <td width="250" style="padding-left: 10">
                                <label><input type="checkbox" name="eatInRoom" value="yesEatInRoom" 
                                <%= lifePattern.getEatInRoom() != null && lifePattern.getEatInRoom().equals("yesEatInRoom") ? "checked" : "" %>> 방 안 취식O</label>
                                <label><input type="checkbox" name="eatInRoom" value="noEatInRoom" 
                                <%= lifePattern.getEatInRoom() != null && lifePattern.getEatInRoom().equals("noEatInRoom") ? "checked" : "" %>> 방 안 취식X</label>
                            </td>
                        </tr>
                        <tr height="40">
                            <td width="130">나이</td>
                            <td width="250" style="padding-left: 10">
                                <input id="input_txt" type="text" style="width: 240" name="age" 
                                   placeholder="[필수] 숫자로만 입력. 예) 21" required value="<%= lifePattern.getAge() %>">
                            </td>
                        </tr>
                        <tr height="40">
                            <td width="130">침대 1층/2층 선호</td>
                            <td width="250" style="padding-left: 10">
                                <label><input type="checkbox" name="bedPreference" value="1" 
                                <%= lifePattern.getBedPreference() != null && lifePattern.getBedPreference().equals("1") ? "checked" : "" %>> 1층</label>
                                <label><input type="checkbox" name="bedPreference" value="2" 
                                <%= lifePattern.getBedPreference() != null && lifePattern.getBedPreference().equals("2") ? "checked" : "" %>> 2층</label>
                            </td>
                        </tr>
                        </table>
                     </td>
                  </tr>
               </table>
         </form>
      </div>
      </div>
   </div>
</body>
</html>