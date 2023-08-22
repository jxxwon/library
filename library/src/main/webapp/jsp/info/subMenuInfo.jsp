<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="context" value="/"/>
<link href="${context }css/subMenu.css" rel="stylesheet" type="text/css">

<div class = "subMenu">
	<h2>정보광장</h2>
	<ul>
		<li><a href="${context }info/notice" class="button" id="subNotice">공지사항</a></li>
		<li><a href="/login" class="button" id="subQna">자주하는 질문</a></li>
		<li><a href="/login" class="button" id="subFree">자유게시판</a></li>
		<li><a href="/login" class="button" id="subVolunteer">자원봉사 안내</a></li>
	</ul>
</div>


