<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <head>
<title>하이미디어 도서관 - 마이라이브러리 : 열람실 좌석 예약</title>

<link href = "/css/main.css" rel = "stylesheet" type = "text/css">
<link href = "/css/reservation.css" rel = "stylesheet" type = "text/css">
</head>   
<body>
	<c:import url = "/header"/>
	<div class = "reserve_container inner pageContent_mt">
			<div class = "reserve_content">
				<div class="reserve_top">
					<h1>열람실 좌석 예약</h1>
					<div class="reserve_menu mb_30 mt_20">
						<div>자율 학습실1<br>96석</div>
						<div>빈좌석<br>96석</div>
						<div>사용중<br>96석</div>
						<div>대기자<br>96석</div>
					</div>
					<div class = "subMenu_myInfo">
						<ul>
						    <li class="myInfo_menu" onclick="showInfo('updateInfo')">회원정보 수정</li>
						    <li class="myInfo_menu" onclick="showInfo('updatePW')">비밀번호 수정</li>
						    <li class="myInfo_menu" onclick="showInfo('updateAuth')">회원 인증</li>
						    <li class="myInfo_menu" onclick="showInfo('withdraw')">회원 탈퇴</li>
						  </ul>
					</div>
				</div>
				<div class="RIContainer">
					<div id="myInfoContainer">
					</div>
				</div>
			</div>
		</div>
	<c:import url="/footer"/>
<!--  <script src = "/javaScript/myInfoUpate.js"></script>-->
</body>

