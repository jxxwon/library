<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="context" value="/"/>

<link href = "${context }css/main.css" rel = "stylesheet" type = "text/css">
<link href = "${context }css/myLibrary.css" rel = "stylesheet" type = "text/css">
<title>하이미디어 도서관 - 마이라이브러리 : 회원정보</title>

<body>
	<div class="room_container">
		<div class="seat_division">
			<c:forEach var="i" begin="1" end="96" step="8">
				<div class="line">
					<div class="seat_left">
						<ul>
							<li><span class="each_seat">${i}</span></li>
							<li><span class="each_seat">${i+1}</span></li>
							<li><span class="each_seat">${i+2}</span></li>
							<li><span class="each_seat">${i+3}</span></li>
						</ul>
					</div>
					<div class="seat_right">
						<ul>
							<li><span class="each_seat">${i+4}</span></li>
							<li><span class="each_seat">${i+5}</span></li>
							<li><span class="each_seat">${i+6}</span></li>
							<li><span class="each_seat">${i+7}</span></li>
						</ul>
					</div>
				</div>
			</c:forEach>	
		</div>
	</div>
</body>