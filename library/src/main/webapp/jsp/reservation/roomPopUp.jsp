<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="context" value="/"/>

<link href = "${context }css/main.css" rel = "stylesheet" type = "text/css">

<body>
<!-- 컴퓨터가 맛이가서... db가 켜지지 않아서 세션을 확인할 수 없음. -->
	<h3>열람실 예약</h3>
	<span>${sessionScope.id}</span>
	<span>${sessionScope.name }</span>
	<form class="updateAuthForm" action="updateAuthProc" method="post" id="f">
		<label for = "name">이름</label>
		<span class="name">${sessionScope.name}</span><br>
		
		<label for = "id">아이디</label>
		<span class="id">${sessionScope.id}</span><br>
		
		<label for = "id"></label>
		<span class="id">${sessionScope.id}</span><br>
		
		<input type="submit" class="registerBtn" value="예약">
		<input type="button" class="cancelBtn" value="취소" onclick="location.href='/myLibrary/myInfo'"><br>
	</form>
</body>
