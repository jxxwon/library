<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:url var="context" value="/"/>
    <head>
    
<title>하이디미어 도서관 - 정보광장 : 자주하는 질문</title>
<link href="${context }css/main.css" rel="stylesheet" type="text/css">
<link href="${context }css/info.css" rel="stylesheet" type="text/css">

<c:import url = "/header"/>
<script>
//탭 클릭 시 active 추가
	document.addEventListener('DOMContentLoaded', function() {
	    const subMenuItems = document.querySelectorAll('.info_menu');
	    subMenuItems[0].classList.add('active');
	
	    subMenuItems.forEach(item => {
	        item.addEventListener('click', function() {
	            subMenuItems.forEach(item => {
	                item.classList.remove('active');
	            });
	
	            this.classList.add('active');
	        });
	    });
	});
</script>
</head>
<body>
<div class="infoContainer inner mb_30" >
	<c:import url = "/subMenuInfo"/>
	<div class="infoContent">
		<h1>자주하는 질문</h1>
		<div class="info_title">
			<p>자주 물으시는 내용들을 정리했습니다.<br>
			   찾으시는 내용이 없으실 때는 1:1문의를 이용해 주시면<br>
			   신속하고 빠른 답변을 받으실 수 있습니다.</p>
		</div>
		<div class="mb_30 mt_20" style = "border-bottom:1px solid #ddd; padding-bottom:20px;">
			<a href="/main">HOME</a> > 
			<a href="${context }info/notice">정보광장</a> >
			<a class="checked" href="${context }info/faq">자주하는 질문</a>
		</div>
		<div class = "subMenu_info" id = "subMenu_info">
			<ul>
				<li class = "info_menu active" onclick="showFaq('faqMember')">회원가입 및 탈퇴</li>
				<li class = "info_menu" id="memberListMenu">도서열람</li>
				<li class = "info_menu" id="memberListMenu" onclick="location.href='memberList'">도서관 이용</li>
				<li class = "info_menu" id="memberListMenu" onclick="location.href='memberList'">사이트 이용</li>
			</ul>
		</div>
		<div class="contentBox" style = "padding-top:0;">
			<div class="noticeContainer" style = "border-top:none; padding-top:0;">
				<c:import url = "${context }info/faqMember"/>
			</div>
			<c:if test = "${sessionScope.status == 'M'}">
				<div class="write">
					<input type = "button" value = "등록" onclick="location.href='faqWriteForm'">
				</div>
			</c:if>
		</div>
	</div>
</div>
<c:import url="/footer"/>
</body>
<script src = "${context }javaScript/info.js"></script>


