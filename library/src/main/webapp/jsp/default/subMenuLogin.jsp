<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/css/subMenu.css" rel="stylesheet" type="text/css">
 <style>
    /* 스타일링을 위한 CSS 코드 */
    .button {
      padding: 10px 20px;
      font-size: 16px;
      cursor: pointer;
    }
    .red-button {
      background-color: red;
      color: white;
    }
    .blue-button {
      background-color: blue;
      color: white;
    }
  </style>
<body>
  <button class="button" id="redButton">Red Button</button>
  <button class="button" id="blueButton">Blue Button</button>

  <script>
    // 현재 URL을 가져오는 JavaScript 함수
    function getCurrentURL() {
      return window.location.href;
    }

    // URL에 따라 버튼 색상을 변경하는 JavaScript 함수
    function setButtonColorByURL() {
      var currentURL = getCurrentURL();

      // 원하는 URL 패턴에 따라 버튼 색상을 변경합니다.
      if (currentURL.includes("register")) {
        document.getElementById("register").classList.add("active");
        document.getElementById("blueButton").classList.remove("active");
      } else if (currentURL.includes("login")) {
        document.getElementById("blueButton").classList.add("active");
        document.getElementById("redButton").classList.remove("active");
      } 
    }

    // 페이지 로드 시 버튼 색상을 설정합니다.
    setButtonColorByURL();

    // 페이지 URL이 변경될 때마다 버튼 색상을 업데이트합니다.
    window.onpopstate = function () {
      setButtonColorByURL();
    };
  </script>

  
<div class = "subMenu">
	<h2>회원가입</h2>
	<ul>
		<li><a href="/login" class="button" id="login">로그인</a></li>
		<li><a href="#" class="button">아이디/패스워드 찾기</a></li>
		<li><a href="/register" class="button" id="register">회원가입</a></li>
		<li><a href="#" class="button">회원탈퇴</a></li>
	</ul>
</div>
</body>