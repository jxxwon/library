<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<title>하이디미어 도서관 - 회원가입</title>
<link href="/css/main.css" rel="stylesheet" type="text/css">
<link href="/css/container.css" rel="stylesheet" type="text/css">
<style>
.regProcess span .active{background-color:#338cfa; color:#fff;}
</style>
<c:import url = "/header"/>
<div class="RegisterContainer inner mb_30" >
	<c:import url = "/subMenuLogin"/>
	<div class="loginContent">
		<h1>회원가입</h1>
		<div class="mb_30 mt_20">
			<a href="/main">HOME</a> > 
			<a href="/register">회원정보</a> >
			<a class="checked" href="/register">회원가입</a>
		</div>
		<div class="register">
			<div class="regProcess">
				<span>회원유형</span> <p>></p>
				<span>약관동의</span> <p>></p>
				<span>본인확인</span> <p>></p>
				<span class="active">정보입력</span> <p>></p>
				<span>가입완료</span>
			</div>
		</div>
		<div class="RIContainer">
			<form action="kakaoRegisterProc" method="post" id="f">
				<label>이름*</label>	
				<input type="text" name="name" id="name" placeholder="이름" ><br>
				<label>보호자 이름</label>
				<input type="text" name="proName" id="proName" placeholder="보호자 이름" ><br>
				<label>생년월일</label>
				<input type="text" name="birth" id="birth" placeholder="생년월일 2000-08-25" ><br>
				
				<label>아이디*</label>
				<input type="text" name="id" placeholder="아이디" id="id">
				<button>중복확인</button><br>
				<span>*아이디는 6~12자리의 영문 또는 숫자 혼용, 특수문자 제외</span><br>
				
				<label>비밀번호*</label>
				<input type="password" name="pw" placeholder="비밀번호" id="pw"><br>
				<span>*비밀번호는 10 ~ 12자리이며, 반드시 영문자, 숫자를 혼합하여
				입력하시기 바랍니다.(특수문자 제외)</span><br>
				<label>비밀번호 확인*</label>
				<input type="password" name="confirm" placeholder="비밀번호 확인 " id="confirm"
				onchange="pwCheck()">
				<span>* 비밀번호를 한번 더 입력해주세요.</span><br>
				
				<label>이메일 *</label>
				<input type="text" name="email" id="email" placeholder="이메일" >
				<br>
				
				<label>주소 *</label>
				<input type="text" id="postcode" name="postcode" placeholder="우편번호">
				<input type="button" id="postcodeBtn" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="address" name="address" placeholder="주소"><br>
				<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소"><br>
				
				<label>연락처 *</label>
				<input type="text" name="mobile" id="mobile" placeholder="전화번호" >
				<input type="button" id="authBtn" onclick="sendAuth()" value="인증번호 받기"><br>
				
				<input type="checkbox" onclick="SMS()" value="SMS수신">
				<label>SMS 수신</label><br>
				<span>* 도서의 반납예정일, 예약도서, 일반열람실 대기자 호출, 행사 등 안내</span><br>
				<input type="button" class="registerBtn" value="회원가입" onclick="allCheck()">
				<input type="button" class="cancelBtn" value="취소" onclick="location.href='login'"><br>
			</form>
		</div>
		
		
	</div>
</div>
<c:import url="/footer"/>


