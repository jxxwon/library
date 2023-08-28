<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:url var="context" value="/"/>
    <head>
    
<title>하이디미어 도서관 - 관리자 페이지 : 열람실관리</title>
<link href="${context }css/main.css" rel="stylesheet" type="text/css">
<link href="${context }css/admin.css" rel="stylesheet" type="text/css">

<script>
	function roomStatus(status){
		
		let f = document.getElementById('f');
		f.submit();
	}
</script>
</head>
<body>
<div class="adminContainer inner mb_30" >
	<div class="adminContent">
		<form action="roomStatusProc" id="f" class="bookContainer"  method="post">
			<input type="submit" name="open" value="열람실 오픈" >
			<input  type="submit" name="closed" value="열람실 마감" >
		</form>
	</div>
</div>
</body>
<script src = "${context }javaScript/admin.js"></script>


