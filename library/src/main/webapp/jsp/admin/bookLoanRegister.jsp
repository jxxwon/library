<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="context" value="/"/>

<link href = "${context }css/main.css" rel = "stylesheet" type = "text/css">
<link href = "${context }css/admin.css" rel = "stylesheet" type = "text/css">

<script>
	function writeChk(){
		var title = document.getElementById('title').value;
		var content = document.getElementById('content').value;
		
		//title.trim() == title에서 양 끝의 공백 제거
		//title.trim().length == 0 는 제목에 스페이스바로 공백만 있을 때 true반환
		if(title == "" || title.trim().length == 0){
			alert('제목을 입력하세요.');
		} else if(content == "" || content.trim().length == 0){
			alert('내용을 입력하세요.');
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
				<form action="myInquiryWriteProc" method="post" id="f">
					<table class="loanRegisterForm">
						<tr>
							<th>도서정보</th>
							<td>
								<input type = "text" id = "book" name = "book" class="book" readonly="readonly" placeholder="검색 버튼으로 책 정보를 불러오세요.">
								<input type = "button" value = "검색" class="bookBtn">
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
							<th>기간</th>
							<td><textarea id = "content" name = "content" rows ="20" cols ="15" style = "resize: none"></textarea></td>
						</tr>
					</table>
					<div class="loanBtn">
						<input type = "button" value = "등록" onclick="writeChk()" >
						<input type = "button" value = "목록" onclick="location.href='book'">
					</div>
				</form>
			</div>
		</div>
	</div>
	<c:import url="/footer"/>
</body>
<script src = "${context }javaScript/admin.js"></script>