<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href = "/css/main.css" rel = "stylesheet" type = "text/css">
<link href = "/css/myLibrary.css" rel = "stylesheet" type = "text/css">

<title>하이미디어 도서관 - 관리자 페이지 : 회원관리</title>

<body>
	<c:import url = "/header"/>
	<div class = "adminContainer inner">
		<c:import url = "/subMenuMyLibrary"/>
		<div class = "adminContent">
			<div class = "admin header">
				<h1>회원정보</h1>
				<div class="mb_30 mt_20">
					<a href="/main">HOME</a> > 
					<a href="/adminMember">마이라이브러리</a> >
					<a class="checked" href="/adminMember">회원정보</a>
				</div>
				<div class = "subMenu_member">
					<ul>
						<li id = "activeLi" class = "active"><a href = "#">회원정보 수정</a></li>
						<li><a href = "#">비밀번호 수정</a></li>
						<li><a href = "#">회원 인증</a></li>
						<li><a href = "#">회원 탈퇴</a></li>
					</ul>
				</div>
			</div>
			<div class="RIContainer">
				<form action="registerProc" method="post" id="f">
					<label>아이디</label>
					<span class="myInfoId">${sessionScope.id}</span><br>
					
					<label for = "name">이름</label>
					<span class="myInfoName">${sessionScope.name}</span><br>
					
					<label>생년월일</label>
					<span class="myInfoBirth">${sessionScope.birth}</span><br>
					
					<label>연락처
						<span class="caution">*</span>	
					</label>
					<input type="text" name="mobile" id="mobile" placeholder="전화번호" >
					<input type="button" id="authBtn" onclick="sendAuth()"  value="휴대폰 인증">
					
					<input type="checkbox" class="SMSBtn" onclick="SMS()" value="SMS수신">
					<label>SMS 수신</label><br>
					
					<label>이메일
						<span class="caution">*</span>	
					</label>
					<input type="text" name="email" id="email" placeholder="이메일" onkeyup="emailCheck()">
					<label id="emailLabel" class="alert"></label><br>
					
					<label>주소
						<span class="caution">*</span>	
					</label>
					<input type="text" id="postCode" name="postCode" placeholder="우편번호">
					<input type="button" id="postcodeBtn" onclick="execDaumPostcode()" value ="우편번호 찾기"><br>
					<input type="text" id="address" name="address" placeholder="주소"><br>
					<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소"><br>
					
					
					<input type="button" class="registerBtn" value="수정" onclick="allCheck()">
					<input type="button" class="cancelBtn" value="취소" onclick="location.href='register2'"><br>
				</form>
			</div>
		</div>
	</div>
	<c:import url="/footer"/>
</body>