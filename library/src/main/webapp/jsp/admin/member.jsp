<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href = "/css/main.css" rel = "stylesheet" type = "text/css">
<link href = "/css/admin.css" rel = "stylesheet" type = "text/css">
<head>
	<title>하이미디어 도서관 - 관리자 페이지 : 회원관리</title>
		<script src = "/javaScript/admin.js"></script>
		<script>
		document.addEventListener('DOMContentLoaded', function() {
		      condition();
		    });
		</script>
</head>

<c:import url = "/header"/>


<body onload="condition()">
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
				<div class = "subMenu_member">
					<ul >
						<li id = "activeLi" class = "active"><a href = "#">인증 승인/반려</a></li>
						<li><a href = "#">회원 제재</a></li>
						<li><a href = "#">회원 목록 조회</a></li>
					</ul>
				</div>
			</div>
			<div class="contentBox">
				<form action="">
					<div class="condition" >
						<label id="memberLbl" class="memberLbl">회원구분</label>
						<select id="memberSelect" class="memberSelect" name="memberSelect">
							<option value = "R">인증신청</option>
							<option value = "D">준회원</option>
							<option value = "A">정회원</option>
							<option value = "W">탈퇴회원</option>
						</select>
						<label id="idLbl" class="idLbl">아이디</label>
						<select id="searchSelect" class="searchSelect">
							<option>아이디</option>
							<option>이름</option>
							<option>연락처</option>
						</select>
						<input type = "text" placeholder="검색어를 입력하세요." id="memberSearch">
						<input type = "submit" value = "조회" id="searchBtn">
					</div>
					<table class="selectMember">
						<tr>
							<th>번호</th>
							<th>아이디</th>
							<th>이름</th>
							<th>상태</th>
							<th>신청일</th>
						</tr>
						<c:choose>
							<c:when test = "${empty members}">
								<tr>
									<td colspan=5> 조회된 회원이 없습니다. </td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="member" items="${members }">
									<tr onclick = "location.href='/memberConfirm?id=${member.id}'" style="cursor:pointer;">
										<td>${member.rn }</td>
										<td>${member.id }</td>
										<td>${member.name }</td>
										<td>
											<c:if test = "${member.status == 'D'}">
												준회원
											</c:if>
											<c:if test = "${member.status == 'A'}">
												정회원
											</c:if>
											<c:if test = "${member.status == 'W'}">
												탈퇴회원
											</c:if>
											<c:if test = "${member.status == 'R'}">
												인증신청
											</c:if>
										</td>
										<td>${member.authDate }</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
					<div class="memberPage">
						${result }
					</div>
					
				</form>
			</div>
		</div>
	</div>
	<c:import url="/footer"/>
</body>
