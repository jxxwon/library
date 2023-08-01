<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<title>하이디미어 도서관 - 회원가입</title>
<link href="/css/main.css" rel="stylesheet" type="text/css">
<link href="/css/container.css" rel="stylesheet" type="text/css">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<c:import url = "/header"/>

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
		
 		xhr.onreadystatechange = idCheckResProc;
	}
	function idCheckResProc(){
		
		// readyState 4: 완료(성공 여부 상관X)
		if(xhr.readyState !== 4)  
			return; 
		
		if(xhr.status === 200) { //응답의 성공 여부를 알 수 있음.
		 // status 200: 성공
		console.log(xhr.responseText); // '반환된 텍스트'
		//var idCheckLabel = document.getElementById('idCheckLabel');
		var idMessage = document.getElementById("idCondition");
		
		//idMessage.style.diplay = "none";
			if(xhr.responseText === "사용 가능한 아이디 입니다."){
				idMessage.style.color = "blue";
			}else{
				idMessage.style.color = "red";
			}
			idMessage.innerHTML = xhr.responseText;
		} else {
			console.log('에러: ' + xhr.status); // 요청 도중 에러 발생
		} 
	}
	function allCheck(){
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
			// 등록일자 입력란에 현재 시간을 설정
		    const regDateInput = document.getElementById('regDate');
		    const currentDate = new Date();
		    const formattedDate = currentDate.toISOString().slice(0, 10);
		    regDateInput.value = formattedDate;
		    
		    console.log("formattedDate : ", formattedDate);
			var f = document.getElementById('f');
			f.submit();
		}
	}

	function nameCheck(){
		let name = document.getElementById('name');
		nameLabel = document.getElementById('nameLabel');
		 if(!name.value){
			 nameLabel.innerHTML = '*이름은 필수 항목입니다.'
		 }else{
			 nameLabel.innerHTML = ''
		 }
	}

	function emailCheck(){
		let email = document.getElementById('email');
		emailLabel = document.getElementById('emailLabel');
		 if(!email.value){
			 emailLabel.innerHTML = '*이메일은 필수 항목입니다.'
		 }else{
			 emailLabel.innerHTML = ''
		 }
	}
		
	function idRegCheck() {
		  const idInput = document.getElementById("id");
		  const id = idInput.value;
		  const idMessage = document.getElementById("idCondition");

		  // 6~12자리의 영문 또는 숫자 혼용, 특수 문자 제외 정규식
		  const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,12}$/;
		  if (regex.test(id)) {
			  idMessage.textContent = "유효한 아이디 입니다.";
			  idMessage.style.color = "green";
		  } else {
		    idMessage.textContent = "6~12자리의 영문 또는 숫자 혼용, 특수 문자 제외로 입력하세요.";
		    idMessage.style.color = "red";
		  }
	}
		
	function pwRegCheck() {
		  const passwordInput = document.getElementById("pw");
		  const confirmInput = document.getElementById("confirm");
		  const password = passwordInput.value;
		  const passwordMessage = document.getElementById("passwordCondition");
		  
		  if(passwordInput.value == ""){
			  confirmInput.disabled = true;
		  }else{
			  confirmInput.disabled = false;
		  }
		  // 비밀번호는 8 ~ 12자리이며, 반드시 영문자와 숫자를 혼합하여 입력하시기 바랍니다. (특수문자 제외)
		  const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,12}$/;
		  if (regex.test(password)) {
			  passwordMessage.textContent = "유효한 비밀번호 입니다.";
			  passwordMessage.style.color = "green";
		  } else {
		    passwordMessage.textContent = "비밀번호는 8 ~ 12자리이며, 반드시 영문자와 숫자를 혼합하여 입력하세요. (특수문자 제외)";
		    passwordMessage.style.color = "red";
		  }
	}
		
	function pwConfirmCheck(){
		const pw = document.getElementById('pw');
		const confirmInput = document.getElementById("confirm");
		const pwConfirmCaution = document.getElementById("pwConfirmCaution");
		const pwConfirmMatch = document.getElementById('pwConfirmMatch');
		
		
		if (confirmInput.value === "") {
			pwConfirmCaution.style.display = "block";
		} else {
			pwConfirmCaution.style.display = "none";
		}
		 
		if (pw.value == confirmInput.value) {
		 pwConfirmMatch.style.color = 'blue';
		 pwConfirmMatch.innerHTML = '일치';
		} else {
		 pwConfirmMatch.style.color = 'red';
		 pwConfirmMatch.innerHTML = '불일치';
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
				
	$(function() {
	  $('input[name="birth"]').daterangepicker({
	    singleDatePicker: true,
	    showDropdowns: true,
	    startDate: 07/26/1993,
	    minYear: 1940,
	    maxYear: parseInt(moment().format('YYYY'), 10),
	    locale: {
	      format: 'YYYY-MM-DD'
	    }
	  }, function(start, end, label) {
	console.log(document.getElementById('birth').value);
	    // Update the input value with the selected date in "yyyy-mm-dd" format
	    console.log(start)
	    $('input[name="birth"]').val(start.format('YYYY-MM-DD'));
	  });
	});
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
				<input type="text" name="birth" id="birth" placeholder="생년월일 2000-08-25"  ><br>
				
				<label>아이디
					<span class="caution">*</span>	
				</label>
				<input type="text" name="id" id="id" placeholder="아이디" onkeyup="idRegCheck()">
				<button type="button" onclick="idCheckSend()">중복확인</button><br>
				<div id="idCheckLabel" class="alert"></div>
				<span id="idCondition" class="essential">*아이디는 6~12자리의 영문 또는 숫자 혼용, 특수 문자 제외</span><br>
				
				<label>비밀번호
					<span class="caution">*</span>	
				</label>
				<input type="password" name="pw" placeholder="비밀번호" id="pw" onkeyup="pwRegCheck()"><br>
				<span id="passwordCondition" class="essential">*비밀번호는 8 ~ 12자리이며, 반드시 영문자, 숫자를 혼합하여
				입력하시기 바랍니다.(특수문자 제외)</span><br>
				<label>비밀번호 확인*</label>
				<input type="password" name="confirm" placeholder="비밀번호 확인 " id="confirm" disabled="disabled"
				onkeyup="pwConfirmCheck()">
				<span id="pwConfirmCaution" class="caution">* 비밀번호를 한번 더 입력해주세요.</span><br>
				<label id="pwConfirmMatch"></label><br>
				
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
				
				<input type="text" id="regDate" name="regDate" style="display: none;"><br>
				
				<input type="button" class="registerBtn" value="회원가입" onclick="allCheck()">
				<input type="button" class="cancelBtn" value="취소" onclick="location.href='register2'"><br>
			</form>
		</div>
		
		
	</div>
</div>
<c:import url="/footer"/>


