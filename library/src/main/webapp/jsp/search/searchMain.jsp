<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <head>
<title>하이미디어 도서관 - 마이라이브러리 : 검색 메인</title>

<link href = "/css/main.css" rel = "stylesheet" type = "text/css">
<link href = "/css/myLibrary.css" rel = "stylesheet" type = "text/css">
</head>   

<body>
	<c:import url = "/header"/>
	<div class = "myLibraryContainer inner pageContent_mt">
	검색창
			<div class = "myLibraryContent">
				<form action="datasearchProc" method="post">
					<button type="submit">검색하기</button>
				</form>
			</div>
	</div>
	<c:import url="/footer"/>
</body>


