<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link href="/css/main.css" rel="stylesheet" type="text/css">
    <link href="/css/login.css" rel="stylesheet" type="text/css">
<style>
	.loginContainer{ padding:50px 0 0 50px;}
	h1{margin-bottom:50px;}
	.loginNavigate{}
	.checked{font-weight: 600;}
	
	.contentBox{display: flex;}
	.loginImgBox { width : 180px; margin-right:50px;}
	.loginImgBox  > img { width:100%; padding-top: 35px; padding-left: 10px;}
	label{display:inline-block; width:80px; margin-bottom:15px;}
	#id, #pw{width:230px; }
	form{display: flex;}
	.loginButton{background-color: skyblue; width:80px; 
	color:white; border-color: transparent; font-size: 17px;}
	
	.kakao{width: 100%; height:55px; display : flex; justify-content: center;align-items: center;
	background: #FEE500; border-radius: 5px;}
	
	.bottomButtonBox{display:flex;justify-content: space-between;}
	.registerButton, .findButton{
	border-color: transparent; margin-top:10px; height:50px; font-size:15px}
	.registerButton{ padding: 0 65px 0 65px; margin-right:10px; }
	.findButton{padding:0 30px 0 30px;}
</style>

<div class="loginContainer" >
	<h1>로그인</h1>
	<div class="mb_30">
		<a href="/index">HOME</a> > 
		<a href="/register">회원가입</a> >
		<a class="checked" href="/login">로그인</a>
	</div>
	<div class="contentBox">
		<div class="loginImgBox">
			<img src="/image/loginImg.png" alt="yanolja-logo-gray"> 
		</div>
		<div>
			<form action="loginProc" method="post" class="mb_10" id="f">
				<div class="mr_10">
					<label>아이디 </label>
					<input type="text" name="id" placeholder="아이디" class="mb_10" id="id"><br>
					<label>비밀번호 </label>
					<input type="password" name="pw" placeholder="비밀번호" id="pw"><br>
				</div>
				<input type="button" value="로그인" class="loginButton" onclick="loginCheck()">
			</form>
			<input type="checkbox"/>
			<label>아이디 저장</label>
			<div class="kakao">
				<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&
				client_id=4e44c398b323055c3d41ede548ed454d&
				redirect_uri=http://localhost/kakaoLogin">
					<img class="kakaoLoginBox" src = "https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" />
				</a>
			</div>
			<div class="bottomButtonBox">
				<button class="registerButton">회원가입</button>
				<button class="findButton">아이디/비밀번호 찾기</button>
			</div>
		</div>
	</div>
</div>



