<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="context" value="/" />
<head>

<title>하이디미어 도서관 - 관리자 페이지 : 열람실관리</title>
<link href="${context }css/main.css" rel="stylesheet" type="text/css">
<link href="${context }css/admin.css" rel="stylesheet" type="text/css">

<c:import url="/header" />

</head>
<body>
	<div class="adminContainer inner mb_30">
		<c:import url="/subMenuAdmin" />
		<div class="adminContent">
			<h1>열람실관리</h1>
			<div class="mb_30 mt_20"
				style="border-bottom: 1px solid #ddd; padding-bottom: 20px;">
				<a href="/main">HOME</a> > <a href="${context }admin/member">관리자페이지</a>
				> <a class="checked" href="${context }admin/room">열람실관리</a>
			</div>
			<form id="f" action="" method="post">
				<div class="subMenu_book" id="subMenu_info">
					<ul id="room_menu">
						<li class="info_menu active" value="bookLoan"><a
							href="${context}admin/room">오픈/마감</a></li>
						<li class="info_menu" id="memberListMenu" value="bookManage"><a
							href="${context}admin/roomStatus">열람실현황</a></li>
					</ul>
				</div>
				<input type="hidden" id="menuItemValue" name="category" value="">
			</form>
			<div class="bookContainer" style="border-top: none; padding-top: 0;">
				<div class="adminContent">
					<form action="roomStatusProc" id="f" class="bookContainer"
						method="post">
						<input type="submit" name="open" value="열람실 오픈"> <input
							type="submit" name="closed" value="열람실 마감">
					</form>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/footer" />
</body>
<script src="${context }javaScript/admin.js"></script>


