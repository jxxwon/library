<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="context" value="/"/>

<link href = "${context }css/main.css" rel = "stylesheet" type = "text/css">
<link href = "${context }css/info.css" rel = "stylesheet" type = "text/css">

<title>하이미디어 도서관 - 정보광장 : 공지사항</title>

<script>
	function delInquiry(){
		if (confirm('해당 공지사항을 삭제하시겠습니까?')==true){
			alert('공지사항이 삭제되었습니다.');
			location.href='myInquiryDelete?rn=${inquiry.rn}';
		}
	}
</script>

<body>
	<c:import url = "/header"/>
	<div class = "infoContainer inner pageContent_mt">
		<c:import url = "/subMenuInfo"/>
		<div class="infoContent">
			<h1>공지사항</h1>
			<div class="info_title">
				<p>여러분의 소중한 공간 하이미디어도서관 정보광장입니다.</p>
			</div>
			<div class="mb_30 mt_20">
				<a href="/main">HOME</a> > 
				<a href="${context }info/notice">정보광장</a> >
				<a class="checked" href="${context }info/notice">공지사항</a>
			</div>
		</div>
			<div class="inquiryContainer">
				<table class="inquiryContentForm">
					<tr>
						<th>제목</th>
						<td>${inquiry.title}</td>
					</tr>
					<tr>
						<th>작성일</th>
						<td width="400">${inquiry.writeDate}</td>
						<th class="reply">처리상태</th>
						<td width="200">
							<c:if test = "${inquiry.reply == 'N'}">
								미답변
							</c:if>
							<c:if test = "${inquiry.reply == 'Y'}">
								답변완료
							</c:if>
						</td>
						
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<div>${inquiry.content}</div>
						</td>
					</tr>
					<c:if test = "${inquiry.reply == 'Y'}">
						<tr>
							<th>답변</th>
							<td colspan="3">${inquiry.replyContent }</td>
						</tr>
					</c:if>
				</table>
				<div class="inquiryBtn">
				<c:if test = "${inquiry.reply == 'N'}">
					<input type = "button" value = "수정" onclick="location.href='myInquiryUpdate?rn=${inquiry.rn}'">
					<input type = "button" value = "삭제" onclick="delInquiry()">
				</c:if>
					<input type = "button" value = "목록" onclick="location.href='myInquiry'">
				</div>
			</div>
		</div>
	</div>
	<c:import url="/footer"/>
</body>