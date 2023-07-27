<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <link href="/css/main.css" rel="stylesheet" type="text/css">
    <link href="/css/kakaoRegister.css" rel="stylesheet" type="text/css">
    <script src="dbLibray.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function execDaumPostcode() {
	    new daum.Postcode({
	        oncomplete: function(data) {
				if(data.userSelectedType === 'R'){
					document.getElementById('address').value= data.roadAddress;
				}else{
					document.getElementById('address').value= data.jibunAddress;
				}
	          /*  
	          	console.log(data.userSelectedType)
	            console.log(data.roadAddress)
	            console.log(data.jibunAddress)
	            console.log(data.zonecode)
	           */
	        }
	    }).open();
	}
</script>
<div class="KRContainer" >
	<div class="KRContent">
		<div class="logo">
        	<a href="${context}main"><img src="/image/LOGO.png"></a>
		</div>
		<h1 class="mb_20">카카오 연동 회원가입</h1>
		<!--아이디(중복체크), 비밀번호, 이름, 생년월일, 주소, 전화번호 -->
		<form action="kakaoRegisterProc" method="post" id="f">
			<input type="text" name="id" placeholder="아이디" id="id"> (*필수 항목) <br>
			<input type="password" name="pw" placeholder="비밀번호" id="pw"><br>
			<input type="password" name="confirm" placeholder="비밀번호 확인 " id="confirm"
			onchange="pwCheck()">
			<label id="label" ></label><br>
			<input type="text" name="name" id="name" placeholder="이름" ><br>
			<input type="text" name="birth" id="birth" placeholder="생년월일 2000-08-25" ><br>
			<input type="button" onclick="execDaumPostcode()" value="주소 찾기"><br>
			<input type="text" id="address" name="address" placeholder="주소"><br>
			<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소"><br>
			<input type="text" name="mobile" id="mobile" placeholder="전화번호" ><br>
			
			<input type="button" value="카카오 연동 회원가입하기" onclick="allCheck()">
			<input type="button" value="취소" onclick="location.href='login'"><br>
		</form>
	</div>
</div>
<c:import url="/footer"/>


