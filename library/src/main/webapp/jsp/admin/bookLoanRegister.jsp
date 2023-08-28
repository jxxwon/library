<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="context" value="/"/>

<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<link href = "${context }css/main.css" rel = "stylesheet" type = "text/css">
<link href = "${context }css/admin.css" rel = "stylesheet" type = "text/css">

<script>
	function loanChk(){
		var restVol = document.getElementById('restVol').value;
		
		if(restVol < 1){
			alert('잔여 권수가 없어 대출 등록이 불가합니다.');
		} else {
			var f = document.getElementById('f');
			f.submit();
		}
	}
</script>

<title>하이미디어 도서관 - 관리자 페이지 : 대출 등록</title>

<body>
	<c:import url = "/header"/>
	<div class = "loanRegisterFormContainer inner pageContent_mt">
		<c:import url = "/subMenuAdmin"/>
		<div class = "loanRegisterFormContent">
			<div class = "admin header">
				<h1>도서관리</h1>
				<div class="mb_30 mt_20" style = "border-bottom:1px solid #ddd; padding-bottom:20px;">
					<a href="/main">HOME</a> > 
					<a href="${context }admin/member">관리자페이지</a> >
					<a class="checked" href="${context }admin/book">도서관리</a>
				</div>
			</div>
			<div class="loanRegisterContainer">
				<form action="loanRegisterProc?loanId=${reserve.loanId }" method="post" id="f">
					<table class="loanRegisterForm">
						<c:choose>
							<c:when test = "${empty reserve == false}">
								<tr>
									<th>도서정보</th>
									<td>${reserve.bookName}</td>
									<th class="right">ISBN</th>
									<td><input type = "text" readonly = "readonly" name = "isbn" style = "border:none; color:#000; font-size:16px; font-weight:400; padding-left:0;" value = "${reserve.isbn }"></td>
								</tr>
								<tr>
									<th>잔여권수</th>
									<td><input type = "text" readonly = "readonly" name = "restVol" id="restVol" style = "border:none; color:#000; font-size:16px; font-weight:400; padding-left:0;" value = "${book.restVol }권"></td>
									<th class="right">총권수</th>
									<td>${book.vol }권</td>
								</tr>
								<tr>
									<th>대출자</th>
									<td><input type = "text" readonly = "readonly" name = "userId" style = "border:none; color:#000; font-size:16px; font-weight:400; padding-left:0;" value = "${reserve.userId }"></td>
									<th class="right">예약일</th>
									<td>${reserve.reserveDate }</td>
								</tr>
								<tr>
									<th>대출기간</th>
									<td colspan=3>
										<input type="text" name="startDate" id="startDate" placeholder = "시작일자">~<input type="text" name="endDate" id="endDate" placeholder = "종료일자">
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<th>도서정보</th>
									<td>
										<input type = "text" id = "book" name = "book" class="book" placeholder="책 제목 또는 ISBN 입력"><input type = "button" value = "검색" class="bookBtn" onclick="bookSearch()">
										<div id="bookList"></div>
									</td>
								</tr>
								<tr>
									<th>대출자</th>
									<td>
										<input type = "text" id = "book" name = "book" class="book" readonly="readonly" placeholder="검색 버튼으로 차용인(사용자)정보를 불러오세요.">
										<input type = "button" value = "검색" class="bookBtn">
									</td>
								</tr>
								<tr>
									<th>대출기간</th>
									<td colspan=3>
										<input type="text" name="startDate" id="startDate" placeholder = "시작일자">~<input type="text" name="endDate" id="endDate" placeholder = "종료일자">
									</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</table>
					<div class="loanBtn">
						<input type = "button" value = "등록" onclick="loanChk()" >
						<input type = "button" value = "목록" onclick="location.href='book'">
					</div>
				</form>
			</div>
		</div>
	</div>
	<c:import url="/footer"/>
</body>
<script src = "${context }javaScript/admin.js"></script>