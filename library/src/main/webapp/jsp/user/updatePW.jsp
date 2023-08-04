<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href = "/css/main.css" rel = "stylesheet" type = "text/css">
<link href = "/css/myLibrary.css" rel = "stylesheet" type = "text/css">


<title>하이미디어 도서관 - 마이라이브러리 : 회원정보</title>
<body>
	<form class="updatePwContainer" action="updatePwProc" method="post" id="f">
	
		<label>기존 비밀번호<span class="caution">*</span></label>
		<input type="text" name="currentPW" id="currentPW" ><br>
		
		<label>신규 비밀번호<span class="caution">*</span></label>
		<input type="text" name="newPW" id="newPW" ><br>
		
		<label>신규 비밀번호 확인<span class="caution">*</span></label>
		<input type="text" name="newConfirmPW" id="newConfirmPW" ><br>
		
		<input type="submit" class="registerBtn" value="비밀번호 변경">
		<input type="button" class="cancelBtn" value="취소" onclick="location.href='/myLibrary/myBookStatus'"><br>
	</form>
	<script src = "/javaScript/myInfoUpate.js"></script>
</body>