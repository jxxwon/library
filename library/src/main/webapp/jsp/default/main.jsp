<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하이미디어 도서관</title>
<script>
        // 메세지 변수를 JavaScript 변수로 변환
        var message = "${msg}";
        var updateMsg = "${updateMsg}";
        
        if(message !== "")
        // alert로 메세지 출력
        	alert(message);
        if(updateMsg !== "")
        // alert로 메세지 출력
        	alert(updateMsg);
        
    </script>
</head>
<body>
	<c:import url = "/header"/> 
	<c:import url="/footer"/>
</body>
</html>