<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="context" value="/"/>

<link href = "${context }css/main.css" rel = "stylesheet" type = "text/css">
<link href = "${context }css/admin.css" rel = "stylesheet" type = "text/css">

<title>하이미디어 도서관 - 관리자 페이지 : 도서관리</title>

<body>
	<div class = "bookContent">
		<div class="bookContainer">
			<form action="" id="f" class="bookLoanForm">
				<div class="bookSearch">
					<select class="loanSelect" name = "select" id="loanSelect" onchange="searchChange()">
						<option <c:if test="${param.select == 'status'}">selected='selected'</c:if>value="status">신청상태</option>
						<option <c:if test="${param.select == 'id'}">selected='selected'</c:if>value="id">아이디</option>
						<option <c:if test="${param.select == 'title'}">selected='selected'</c:if>value="title">책제목</option>
					</select>
					<c:choose>
					    <c:when test="${param.select == 'status' || param.select == null}">
					        <select class="loanStatusSelect" name="loanStatusSelect" id="loanStatusSelect">
					            <option <c:if test="${param.loanStatusSelect == 'R'}">selected='selected'</c:if>value="A">대출예약</option>
					            <option <c:if test="${param.loanStatusSelect == 'L'}">selected='selected'</c:if>value="L">대출중</option>
					            <option <c:if test="${param.loanStatusSelect == 'O'}">selected='selected'</c:if>value="L">연체</option>
					            <option <c:if test="${param.loanStatusSelect == 'T'}">selected='selected'</c:if>value="T">전체</option>
					        </select>
					    </c:when>
					    <c:otherwise>
					        <select class="loanStatusSelect" name="loanStatusSelect" id="loanStatusSelect" style="display:none">
					            <option value="R">대출예약</option>
					            <option value="L">대출중</option>
					            <option value="O">연체</option>
					            <option value="T">전체</option>
					        </select>
					    </c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test = "${param.select == 'status' || param.select == null}">
							<input type = "text" name = "search" id = "search" placeholder ="검색어를 입력하세요" style = "display:none">
						</c:when>
						<c:otherwise>
							<input type = "text" name = "search" id = "search" placeholder ="검색어를 입력하세요" style = "display:inline-block">
						</c:otherwise>
					</c:choose>
					<input type = "submit" id="loanSearchBtn" value = "검색" onclick="loanSearch()" >
				</div>
				<div class="loanBtn">
					<input type = "button" value = "등록" onclick="location.href='bookLoanRegister'">
				</div>
				<table class="inquiry">
					<tr>
						<th>번호</th>
						<th>책제목</th>
						<th>대출자</th>
						<th>대출시작일</th>
						<th>반납예정일</th>
					</tr>
					<c:choose>
						<c:when test = "${empty inquiries}">
							<tr>
								<td colspan = 5 style = "cursor:default; color:#000;">
									예약 신청 중인 도서가 없습니다.
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="inquiry" items = "${inquiries}">
								<tr onclick="location.href='inquiryContent?no=${inquiry.no}'">
									<td>${inquiry.no}</td>
									<td>${inquiry.title }</td>
									<td>
										<c:if test = "${inquiry.reply == 'N' }">
											미답변
										</c:if>
										<c:if test = "${inquiry.reply == 'Y' }">
											답변완료
										</c:if>
									</td>
									<td>${inquiry.id }</td>
									<td>${inquiry.writeDate }</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>
				<div class="inquiryPage">
					${result }
				</div>
			</form>
		</div>
	</div>
</body>
<script>
	function searchChange(){
		var loanSelect = document.getElementById('loanSelect');
		var select = document.getElementById('loanSelect').options.selectedIndex;
		var option = loanSelect.options[select].value;
		if(option == "status"){
			document.getElementById('search').style.display='none';
			document.getElementById('loanStatusSelect').style.display='inline-block';
		} else {
			document.getElementById('search').style.display='inline-block';
			document.getElementById('loanStatusSelect').style.display='none';
		}
	}
	
	/*submit 시 parameter 안 넘어가게 조절함(disabled)*/
	function loanSearch(){ 
		const loanSearchBtn = document.getElementById('loanSearchBtn');
		var loanSelect = document.getElementById('loanSelect');
		var select = document.getElementById('loanSelect').options.selectedIndex;
		var option = loanSelect.options[select].value;
		
		var loanStatusSelect = document.getElementById('loanStatusSelect');
		var searchInput = document.getElementById('search');
		
		if(option == 'id' || option == 'title'){
			document.getElementById('loanStatusSelect').disabled = true;
			if(searchInput.value == ''){
				alert('검색어를 입력하세요.');
				document.getElementById('loanSelect').disabled = true;
				document.getElementById('search').disabled = true;
			}
		} else{	
			document.getElementById('loanStatusSelect').disabled = false;
	        searchInput.disabled = true;
		}
		
	}
</script>

<script src = "${context }javaScript/admin.js"></script>