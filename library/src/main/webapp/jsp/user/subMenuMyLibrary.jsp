<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<link href="/css/subMenu.css" rel="stylesheet" type="text/css">
<c:url var="context" value="/"/>
<div class = "subMenu subMyLibrary">
	<h2>마이라이브러리</h2>
	<ul>
		<li class="submyLibrary_menu" onclick="showMyLibSub('myBookStatus')">대출/예약/연장 현황</li>
		<li class="submyLibrary_menu" onclick="showMyLibSub('#')">대출이력조회</li>
		<li class="submyLibrary_menu" onclick="showMyLibSub('#')">대출예약 확인</li>
		<li class="submyLibrary_menu" onclick="showMyLibSub('#')">희망도서신청</li>
		<li class="submyLibrary_menu" onclick="showMyLibSub('#')">문화행사 현황</li>
		<li class="submyLibrary_menu" onclick="showMyLibSub('#')">결제 관리</li>
		<li class="submyLibrary_menu" onclick="showMyLibSub('myInquiry')">1:1문의</li>
		<li class="submyLibrary_menu" onclick="showMyLibSub('myInfo')">회원정보</li>
	</ul>
</div>
<script src="/javaScript/subMyLibrary.js"></script>

