<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="context" value="/"/>

<link href = "${context }css/main.css" rel = "stylesheet" type = "text/css">
<link href = "${context }css/admin.css" rel = "stylesheet" type = "text/css">
<script src = "${context }javaScript/admin.js"></script>
<body>
	<div class="contentBox">
		<form action="">
			<table class="selectMember">
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>상태</th>
				</tr>
				<c:choose>
					<c:when test = "${empty members}">
						<tr style = "color:#000; cursor:default">
							<td colspan=5> 조회된 회원이 없습니다. </td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="member" items="${members }">
							<tr <c:if test = "${param.memberSelect == 'R' || param.memberSelect == null}">
								<tr onclick = "location.href='memberConfirm?id=${member.id }'" style="cursor:pointer;">
								</c:if>>
								<td>${member.rn }</td>
								<td>${member.id }</td>
								<td>${member.name }</td>
								<td>
									<c:if test = "${member.status == 'D'}">
										준회원
									</c:if>
									<c:if test = "${member.status == 'A'}">
										정회원
									</c:if>
									<c:if test = "${member.status == 'W'}">
										탈퇴회원
									</c:if>
									<c:if test = "${member.status == 'R'}">
										인증신청
									</c:if>
								</td>
								<td>
									<c:if test = "${member.status == 'D'}">
										${member.regDate }
									</c:if>
									<c:if test = "${member.status != 'D'}">
										${member.authDate }
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
			<div class="memberPage">
				${result }
			</div>
		</form>
	</div>
</body>
