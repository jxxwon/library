<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<link href="/css/subMenu.css" rel="stylesheet" type="text/css">
<c:url var="context" value="/"/>
<div class = "subMenu">
	<h2>회원정보</h2>
	<ul>
		<li><a href="#" class="button">대출/예약/연장 현황</a></li>
		<li><a href="#" class="button">대출이력조회</a></li>
		<li><a href="#" class="button">대출예약 확인</a></li>
		<li><a href="#" class="button">희망도서신청</a></li>
		<li><a href="#" class="button">문화행사 현황</a></li>
		<li><a href="#" class="button">결제 관리</a></li>
		<li><a href="${context }myLibrary/myInquiry" class="button">1:1문의</a></li>
		<li><a href="${context }myLibrary/myInfo" id="subMyInfo" class="button">회원정보</a></li>
	</ul>
</div>

<script src="/javaScript/subMyLibrary.js"></script>

