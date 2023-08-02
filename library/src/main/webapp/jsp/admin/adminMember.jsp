<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href = "/css/main.css" rel = "stylesheet" type = "text/css">
<link href = "/css/admin.css" rel = "stylesheet" type = "text/css">

<title>하이미디어 도서관 - 관리자 페이지 : 회원관리</title>

<body>
	<c:import url = "/header"/>
	<div class = "adminContainer inner">
		<c:import url = "/subMenuAdmin"/>
		<div class = "adminContent">
			<div class = "admin header">
				<h1>회원관리</h1>
				<div class="mb_30 mt_20">
					<a href="/main">HOME</a> > 
					<a href="/adminMember">관리자페이지</a> >
					<a class="checked" href="/adminMember">회원관리</a>
				</div>
				<div class = "subMenu_member">
					<ul>
						<li class = "active"><a href = "#">인증 승인/반려</a></li>
						<li><a href = "#">회원 제재</a></li>
						<li><a href = "#">회원 목록 조회</a></li>
					</ul>
				</div>
			</div>
			<div class="contentBox">
				<div>
					<label>인증 상태</label>
					<select>
						<option>준회원</option>
						<option>정회원</option>
					</select>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/footer"/>
</body>