<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href = "/css/main.css" rel = "stylesheet" type = "text/css">
<link href = "/css/admin.css" rel = "stylesheet" type = "text/css">

<title>하이미디어 도서관 - 관리자 페이지 : 회원인증</title>

<c:import url = "/header"/>
<div class = "adminContainer inner">
	<c:import url = "/subMenuAdmin"/>
	<div class = "adminContent">
		<div class = "admin header">
			<h1>회원관리</h1>
			<div class="mb_30 mt_20">
				<a href="/main">HOME</a> > 
				<a href="/member">관리자페이지</a> >
				<a class="checked" href="/member">회원관리</a>
			</div>
		</div>
		<div class="memberContainer">
			<form>
				<table class="memberDetail">
					<tr>
						<th>아이디</th>
						<td>${member.id}</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${member.name}</td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td>${member.birth}</td>
					</tr>
					<tr>
						<th>연락처</th>
						<td>${member.mobile}</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${member.email}</td>
					</tr>
					<tr>
						<th>주소</th>
						<td>${member.postCode}<br>${member.address}<br>${member.detailAddress}</td>
					</tr>
					<tr>
						<th>가입일</th>
						<td>${member.regDate }</td>
					</tr>
					<tr>
						<th>신청일</th>
						<td>${member.regDate }</td>
					</tr>
					<tr>
						<th>신청서류</th>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
<c:import url = "/footer"/>