<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<title>하이미디어 도서관 - 마이라이브러리 : 회원정보</title>

<link href="/css/main.css" rel="stylesheet" type="text/css">
<link href="/css/search.css" rel="stylesheet" type="text/css">
<link href="/css/myLibrary.css" rel="stylesheet" type="text/css">

</head>
<body>
	<c:import url="/header" />
	<div class="myLibraryContainer inner pageContent_mt">
		<c:import url="/datasearch/subMenuSearch" />
		<div class="myLibraryContent">
			<div>
				<h1>통합검색</h1>
				<div>소장하고 있는 자료에 대한 가장 기본적인 검색입니다.</div>
				<div class="mb_30 mt_20">
					<a href="/main">HOME</a> > <a href="/datasearch">자료검색</a> > <a
						class="checked" href="/datasearch/totalSearch">통합검색</a>
				</div>
			</div>
			<div class="searchBox">
				<form class="search_area totalSearch" action="totalSearchProc"
					method="post">
					<input name="totalSearch" placeholder="검색어를 입력하세요.">
					<button class="search_Btn totalSearch">
						<img src="/image/search.png">
					</button>
				</form>
			</div>
			<div class="search_result">
				<div class="bookImgBox">
					<!-- span>1</span> -->
					<div class="result_bookImg">
						<img src="/image/bookExam.jpeg" />
					</div>
				</div>
				<div class="result_bookContent">
					<div class="bookContentBox">
						<div class="title">하늘이 기다려</div>
						<div class="bookInfo">
							<span>마리-캐스틸 멘션-솨아 감독</span> |
							<span>미디어룩[제작]]</span> |
							<span>2018</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="/javaScript/search.js"></script>
	<c:import url="/footer" />
</body>