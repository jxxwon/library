<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="context" value="/"/>
<link href = "${context }css/main.css" rel = "stylesheet" type = "text/css">
<link href = "${context }css/admin.css" rel = "stylesheet" type = "text/css">
<head>
	<title>하이미디어 도서관 - 관리자 페이지 : 회원관리</title>
		<script src = "${context }javaScript/admin.js"></script>
</head>

<script>
//탭 클릭 시 active 추가
	document.addEventListener('DOMContentLoaded', function() {
	    const subMenuItems = document.querySelectorAll('.admin_menu');
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

<c:import url = "/header"/>


<body>
	<div class = "adminContainer inner pageContent_mt">
		<c:import url = "/subMenuAdmin"/>
		<div class = "adminContent">
			<div class = "admin header">
				<h1>회원관리</h1>
				<div class="mb_30 mt_20">
					<a href="/main">HOME</a> > 
					<a href="${context }admin/member">관리자페이지</a> >
					<a class="checked" href="${context }admin/roomManage">열람실 관리</a>
				</div>
				
			</div>
			<div class="adminMemberContainer">
				<div id = "memberContainer">
				</div>
			</div>
		</div>
	</div>
	<c:import url="/footer"/>
</body>
