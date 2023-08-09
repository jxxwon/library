<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href = "/css/main.css" rel = "stylesheet" type = "text/css">
<link href = "/css/myLibrary.css" rel = "stylesheet" type = "text/css">

<title>하이미디어 도서관 - 마이라이브러리 : 1:1문의</title>

<body>
	<div class = "adminContainer">
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
				<form action="" id="f">
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
										<td>
											<c:if test = "${inquiry.reply == 'N' }">
												미답변
											</c:if>
											<c:if test = "${inquiry.reply == 'Y' }">
												답변완료
											</c:if>
										</td>
										<td>${inquiry.writeDate }</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
					<div class="inquiryBtn">
						<input type = "button" value = "글쓰기" onclick="location.href='/myLibrary/myInquiryWriteForm'">
					</div>
					<div class="inquiryPage">
						${result }
					</div>
					<div class="inquirySearch">
						<select class="inqSelect" name = "select" id="inqSelect" >
							<option value="title">제목</option>
							<option value="reply">처리상태</option>
						</select>
						<input type = "text" name = "search" id = "search" placeholder ="검색어를 입력하세요">
						<select class = "replySelect" name = "replySelect" id = "replySelect" style = "display:none">
							<option value = "N">미답변</option>
							<option value = "Y">답변완료</option>
						</select>
						<input type = "button" id="myInquirySearchBtn" value = "검색" >
					</div>
				</form>
			</div>
		</div>
	</div>
<script>

</script>
</body>
