<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <head>
<title>하이미디어 도서관 - 마이라이브러리 : 검색 메인</title>

<link href = "/css/main.css" rel = "stylesheet" type = "text/css">
<link href = "/css/search.css" rel = "stylesheet" type = "text/css">
</head>   

<body>
	<c:import url = "/header"/>
		<div class = "searchContainer pageContent_mt">
			<div class="condition">
				<div class="inner">
					<div class="search_area">
						<input placeholder="검색어를 입력하세요.">
						<button class="search_Btn"><img src="/image/search.png"></button>
					</div>
				</div>
			</div>
			<div class="visual" >
				<div class="inner">
					<div class="content left">
						<div class="popularBook">
							<span>인기도서</span>
							<div class="bookContainer">
								<c:forEach var="image" items="${bookImages}">
									<div class="bookImageBox"><img src="${image}"></div>
								</c:forEach>
							</div>
						</div>
						<div class="newBook">
							<span>신착도서</span>
							<div>책</div>
						</div>
					</div>
					<!-- <div class="content right">
					오른쪽
					</div> -->
				</div>
			</div>
				<!-- <div class = "searchContent">
					<div class="search_area">
						<button class="search_Btn"></button>
					</div>
					<form action="datasearchProc" method="post">
						<button type="submit">검색하기</button>
					</form>
				</div> -->
		</div>
	<c:import url="/footer"/>
</body>


