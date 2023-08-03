<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href = "/css/main.css" rel = "stylesheet" type = "text/css">
<link href = "/css/myLibrary.css" rel = "stylesheet" type = "text/css">

<title>하이미디어 도서관 - 마이라이브러리 : 1:1문의</title>

<body>
	<c:import url = "/header"/>
	<div class = "adminContainer inner">
		<c:import url = "/subMenuMyLibrary"/>
		<div class = "adminContent">
			<div class = "admin header">
				<h1>1:1문의</h1>
				<div class="mb_30 mt_20">
					<a href="/main">HOME</a> > 
					<a href="/adminMember">마이라이브러리</a> >
					<a class="checked" href="/myInquiry">1:1문의</a>
				</div>
			</div>
			<div class="inquiryContainer">
				<form action="" method="post" id="f">
					<table class="inquiry">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>처리상태</th>
							<th>작성일</th>
						</tr>
						<c:choose>
							<c:when test = "${empty inquiries}">
								<tr>
									<td colspan = 4>
										등록한 문의가 없습니다.
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="inquiry" items = "${inquiries}">
									<tr>
										<td>${inquiry.rn}</td>
										<td>${inquiry.title }</td>
										<td>${inquiry.reply }</td>
										<td>${inquiry.writeDate }</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
					<div class="inquiryBtn">
						<input type = "button" value = "글쓰기" onclick="location.href='myInquiryWriteForm'">
					</div>
				</form>
			</div>
		</div>
	</div>
	<c:import url="/footer"/>
</body>