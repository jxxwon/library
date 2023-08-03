<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 캘린더 추가 -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.css">

<!-- select2 라이브러리 추가 -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>

<script src="/javaScript/daytoday.js"></script>
<script src="/javaScript/list.js"></script>

<title>하이디미어 도서관 - 문화행사</title>
<c:url var="context" value="/"/>
<link href="/css/cultural.css" rel="stylesheet" type="text/css">

<style>
	.table-wrapper {
    	display: flex;
    	justify-content: center;
  	}

	.table{
		margin-top:30px;
	}
	
	.table tr td{
		padding:10px 10px 10px 0;
	}
	
	.table tr:last-child td {
	    padding-bottom: 50px;
	}
	
/*     input[type="text"], textarea {
        width: 100%;
        box-sizing: border-box;
        resize: none; /* textarea의 크기 조절 기능 비활성화 
    } */
	.select2 {
	    -webkit-appearance: auto;
	    -moz-appearance: auto;
	    appearance: auto;
	    width: 20%;
	    height: 30px;
	}
	
    input[type="file"] {
        width: 100%;
    }

    input[type="submit"], input[type="button"] {
        padding: 10px 20px;
        background-color: #338cfa;
        color: white;
        border: none;
        cursor: pointer;	/*hover시 마우스 손모양*/
    }

    input[type="submit"]:hover, input[type="button"]:hover {
        background-color: #256aaa;
    }
    
    .picture{
    	font-size: 15px;
    	padding-right: 0px;
    }
   
</style>

<c:import url="/header" />

<div class="table-wrapper">
	<div class="inner">
		<div class="sub-menu">
			<c:import url="/subMenuCultural" />
		</div>

		<div class="culturalContainer">
			<div class="top-cultural">
				<div class="top-cbox">
					<h1>문화행사 신청</h1>
					<p>
						
					</p>
					<ul class="Route">
						<li>HOME</li>
						<li> &gt; </li>
						<li>문화행사</li>
						<li> &gt; </li>
						<li>문화행사 신청</li>
					</ul>
				</div>
			</div>

			<div align="center">
				<form action="${context}culForm" method='post' enctype="multipart/form-data">
					<table class="table">
						<tr>
							<th width="100px">제목</th>
							<td><input style="width: 800px; height: 30px" type="text" name="title"></td>
						</tr>
						<tr>
						    <th>강의기간</th>
						    <td>
						        <input style="width: 15%; height: 30px;" class="lecture start-date" name="start-date">
						        &nbsp;~&nbsp;
						        <input style="width: 15%; height: 30px;" class="lecture end-date" name="end-date">
						    </td>
						</tr>
						<tr>
						    <th>접수기간</th>
						    <td>
						        <input style="width: 15%; height: 30px;" class="registration start-date" name="registration-start">
						        &nbsp;~&nbsp;
						        <input style="width: 15%; height: 30px;" class="registration end-date" name="registration-end">
						    </td>
						</tr>
						<tr>
						    <th>대&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;상</th>
						    <!-- appearance: auto; -webkit-appearance: auto; 글씨 크기에 맞게 아래방향 화살표 -->
						    <td>
						        <select class="select2" style="width: 20%; height: 30px; appearance: auto; -webkit-appearance: auto;" name="target">
								    <option value="전체">전체</option>
								    <option value="초등">초등학생</option>
								    <option value="중등">중학생</option>
								    <option value="고등">고등학생</option>
								    <option value="성인">성인</option>
								</select>
						    </td>
						</tr>
						<tr>
							<th class="picture">사진&nbsp;첨부</th>
							<td><input type="file" name="upfile"></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="신청"> 
								<input type="button" value="취소"	 onclick="location.href='culForm'">
							</td>
						</tr>
					</table>
				</form>
			</div>

		</div>	    
	</div>
</div>

<c:import url="/footer" />

<script src="/javaScript/subCulTop.js"></script>
