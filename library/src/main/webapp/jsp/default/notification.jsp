<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<c:choose>
		<c:when test = "${empty notify || notify.size() ==0}">
			별일없어요
		</c:when>
		<c:otherwise>
			<c:forEach var="i" begin="0" end="${notify.size()-1}" step="1">
				<a href = "${notify.get(i).getUrl()}">${notify.get(i).getNotify() }</a><br>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</body>
</html>