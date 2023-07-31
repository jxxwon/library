<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>header</title>
    <link href="/css/header.css" rel="stylesheet" type="text/css">
    <c:url var="context" value="/"/>
    
    <script>
    	function header(){
  			var id = "${sessionScope.id}";
  			if(id != ''){
  				document.getElementById('login').style.display = 'none';
  				document.getElementById('logout').style.display = 'block';
  			}
  		}
    </script>
</head>
<body onload="header()">
    <div class="container">
        <div class="header-container">
            <div class="logo">
                <a href="${context}main"><img src="/image/LOGO.png"></a>
            </div>
            <div class="main_search">
                <form action="${context}search" method="post">
                    <input type="text" name="query" placeholder="검색어를 입력하세요">
                    <button type="submit">
                        <img src="/image/search.png" width="25" height="25">
                    </button>
                </form>
            </div>
            <div class="main_nav">
                <ul class="left">
                    <li><a href="${context}main">메인</a></li>
                    <li id="login"><a href="${context}login">로그인</a></li>
                    <li id="logout" style = "display:none"><a href="${context}logout">로그아웃</a></li>
                    <li><a href="${context}libraryForm">마이라이브러리</a></li>
                </ul>
                <ul class="right">
                    <li><a href="#"><img src="/image/blog.jpg"></a></li>
                    <li><a href="#"><img src="/image/facebook.jpg"></a></li>
                    <li><a href="#"><img src="/image/twitter.jpg"></a></li>
                    <li><a href="#"><img src="/image/youtube.jpg"></a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="header_nav">
        <hr>
        <ul>
            <li><a href="${context}index">도서관 소개</a></li>
            <li><a href="${context}recom">추천자료</a></li>
            <li><a href="${context}datasearch">자료검색</a></li>
            <li><a href="${context}reservation">좌석예약</a></li>
            <li><a href="${context}cultural">문화행사</a></li>
            <li><a href="${context}info">정보광장</a></li>
        </ul>
        <hr>
    </div>
</body>
</html>
