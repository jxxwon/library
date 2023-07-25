<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style type="text/css">
	a {text-decoration: none; color:black;}
	ul {padding: 20px;}
	ul li {display: inline; padding: 15px;}
	.main_div{height: 150px; padding-top : 80px;}
</style>    

<script src="dbQuiz.js"></script>

<div align="left">
	<img alt="하이미디어도서관" src="C:\boot\Hi_library.png">
</div>
<div align="center">
	<h4>검색</h4>
</div><div align="right">
	<ul>
		<li><a href="${context }index">메인</a></li>
		<li><a href="${context }login">로그인</a></li>
		<li><a href="${context }logout">로그아웃</a></li>
		<li><a href="${context }libraryForm">마이 라이브러리</a></li>
		<li><a href="${context }icon">아이콘</a></li>
	</ul>
</div>

<c:url var="context" value="/"/>
<div align="center">
	<hr>
	<ul>
		<li><a href="${context }index">도서관 소개</a></li>
		<li><a href="${context }register">추천자료</a></li>
		<li><a href="${context }login">자료검색</a></li>
		<li><a href="${context }memberInfo">좌석예약</a></li>
		<li><a href="${context }logout">문화행사</a></li>
		<li><a href="${context }boardForm">정보광장</a></li>
	</ul>
	<hr>
</div>








