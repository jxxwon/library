<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<title>하이디미어 도서관 - 회원가입</title>
<link href="/css/main.css" rel="stylesheet" type="text/css">
<link href="/css/container.css" rel="stylesheet" type="text/css">
<c:import url = "/header"/>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
				if(data.userSelectedType === 'R'){
					document.getElementById('address').value= data.roadAddress;
					console.log(typeof data.roadAddress)
				}else{
					document.getElementById('address').value= data.jibunAddress;
				}
				var postCode = document.getElementById('postCode').value= data.zonecode;
				console.log("postCode" ,data.zonecode)
				console.log(postCode)
				console.log(typeof data.zonecode)
            }
        }).open();
    }
    
	var xhr;
	function idCheckSend() {
		// Ajax 요청을 초기화합니다
		
		xhr = new XMLHttpRequest();
		xhr.open('post', 'exist');
		var idCheck = document.getElementById('id');
		xhr.send(idCheck.value); 
		
 		xhr.onreadystatechange = resProc;
	}
			function resProc(){
		
		// readyState 4: 완료(성공 여부 상관X)
		if(xhr.readyState !== 4)  
			return; 
		
		if(xhr.status === 200) { //응답의 성공 여부를 알 수 있음.
		 // status 200: 성공
		console.log(xhr.responseText); // '반환된 텍스트'
		var idCheckLabel = document.getElementById('idCheckLabel');
		idCheckLabel.innerHTML = xhr.responseText;
		} else {
			console.log('에러: ' + xhr.status); // 요청 도중 에러 발생
		} 
	}
			/*function allCheck(){
			let id = document.getElementById('id');
			let pw = document.getElementById('pw');
			confirm = document.getElementById('confirm');
			userName = document.getElementById('name');
			
			if(id.value == ""){
				alert('아이디는 필수 항목입니다.');
			}else if(pw.value == ""){
				alert('비밀번호는 필수 항목입니다.');
			}else if(confirm.value == ""){
				alert('비밀번호 확인은 필수 항목입니다.');
			}else if(userName.value == ""){
				alert('이름은 필수 항목입니다.');
			}else{
				var f = document.getElementById('f');
				f.submit();
			}
		}*/

		function nameCheck(){
			let name = document.getElementById('name');
			nameLabel = document.getElementById('nameLabel');
			 if(!name.value){
				 nameLabel.innerHTML = '*이름은 필수 항목입니다.'
			 }else{
				 nameLabel.innerHTML = ''
			 }
			// window.alert('pwCheck 호출')
		}

		function emailCheck(){
			let email = document.getElementById('email');
			emailLabel = document.getElementById('emailLabel');
			 if(!email.value){
				 emailLabel.innerHTML = '*이메일은 필수 항목입니다.'
			 }else{
				 emailLabel.innerHTML = ''
			 }
			// window.alert('pwCheck 호출')
		}

		function pwCheck(){
			let pw = document.getElementById('pw');
		 	let confirm = document.getElementById('confirm');
		  	let label = document.getElementById('label');
		  
		  if (pw.value == confirm.value) {
		    label.style.color = 'blue';
		    label.innerHTML = '일치';
		  } else {
		    label.style.color = 'red';
		    label.innerHTML = '불일치';
		  }
			// window.alert('pwCheck 호출')
		}

		function loginCheck(){
			let id = document.getElementById('id');
			let pw = document.getElementById('pw');
			
			if(id.value == ""){
				alert('아이디는 필수 항목입니다.');
			}else if(pw.value == ""){
				alert('비밀번호는 필수 항목입니다.');
			}else{
				var f = document.getElementById('f');
				f.submit();
			}
		}
</script> 

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
			<form action="registerProc" method="post" id="f">
				<label for = "name">이름
					 <span class="caution">*</span>	
				</label>
				<input type="text"  name="name" id="name" placeholder="이름" onblur="nameCheck()" autofocus="autofocus"> <!-- onblur 이벤트는 입력창이 포커스를 잃을 때 실행 -->
				<label id="nameLabel" class="alert"></label><br>
				
				<label>보호자 이름</label>
				<input type="text" name="proName" id="proName" placeholder="보호자 이름" ><br>
				<label>생년월일</label>
				<input type="text" name="birth" id="birth" placeholder="생년월일 2000-08-25" ><br>
				
				<label>아이디
					<span class="caution">*</span>	
				</label>
				<input type="text" name="id" id="id" placeholder="아이디" >
				<button type="button" onclick="idCheckSend()">중복확인</button>
				<div id="idCheckLabel" class="alert"></div>
				<span id="idCondition" class="essential">*5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.</span><br>
				
				<label>비밀번호
					<span class="caution">*</span>	
				</label>
				<input type="password" name="pw" placeholder="비밀번호" id="pw"><br>
				<span class="essential">*비밀번호는 10 ~ 12자리이며, 반드시 영문자, 숫자를 혼합하여
				입력하시기 바랍니다.(특수문자 제외)</span><br>
				<label>비밀번호 확인*</label>
				<input type="password" name="confirm" placeholder="비밀번호 확인 " id="confirm"
				onchange="pwCheck()">
				<span class="caution">* 비밀번호를 한번 더 입력해주세요.</span><br>
				<label id="label"></label><br>
				
				<label>이메일
					<span class="caution">*</span>	
				</label>
				<input type="text" name="email" id="email" placeholder="이메일" onblur="emailCheck()" >
				<label id="emailLabel" class="alert"></label><br>
				
				<label>주소
					<span class="caution">*</span>	
				</label>
				<input type="text" id="postCode" name="postCode" placeholder="우편번호">
				<input type="button" id="postcodeBtn" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="address" name="address" placeholder="주소"><br>
				<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소"><br>
				
				<label>연락처
					<span class="caution">*</span>	
				</label>
				<input type="text" name="mobile" id="mobile" placeholder="전화번호" >
				<input type="button" id="authBtn" onclick="sendAuth()" value="인증번호 받기"><br>
				
				<input type="checkbox" class="SMSBtn" onclick="SMS()" value="SMS수신">
				<label>SMS 수신</label><br>
				<span class="caution">* 도서의 반납예정일, 예약도서, 일반열람실 대기자 호출, 행사 등 안내</span><br>
				<input type="submit" class="registerBtn" value="회원가입" onclick="allCheck()">
				<input type="button" class="cancelBtn" value="취소" onclick="location.href='register2'"><br>
			</form>
		</div>
		
		
	</div>
</div>
<c:import url="/footer"/>


