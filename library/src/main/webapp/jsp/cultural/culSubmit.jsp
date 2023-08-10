<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 캘린더 추가 -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.css">

<script src="/javaScript/daytoday.js"></script>

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
	    padding-top: 30px;
	    padding-bottom: 70px;
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
	
/*     input[type="file"] {
        width: 35%;
    } */
    
input[type="file"] {
    display: none;
}

  /* 파일첨부 버튼 스타일 */
  .file-upload-btn {
    padding: 0px 20px;
    border: 1px solid #ccc;
    background-color: #f9f9f9;
    color: #555;
    cursor: pointer;
  }

  /* 파일첨부 input 요소를 숨김 */
  .file-upload-input {
    display: none;
  }

  /* 파일명을 보여주는 요소 스타일 */
  #fileNameDisplay {
    padding: 0px 20px;
/*     border: 1px solid #ccc;*/
    background-color: #f9f9f9;
    color: #555;
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
	<div class="inner pageContent_mt">
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
							<td><input style="width: 500px; height: 30px" type="text" name="title"></td>
						</tr>
						<tr>
						    <th>강의기간</th>
						    <td>
						        <input style="width: 100px; height: 30px;" class="lecture start-date" name="start-date">
						        &nbsp;~&nbsp;
						        <input style="width: 100px; height: 30px;" class="lecture end-date" name="end-date">
						    </td>
						</tr>
						<tr>
						    <th>접수기간</th>
						    <td>
						        <input style="width: 100px; height: 30px;" class="registration start-date" name="registration-start">
						        &nbsp;~&nbsp;
						        <input style="width: 100px; height: 30px;" class="registration end-date" name="registration-end">
						    </td>
						</tr>


						<tr>
						    <th>대&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;상</th>
						    <!-- appearance: auto; -webkit-appearance: auto; 글씨 크기에 맞게 아래방향 화살표 -->
						    <td>
						        <select class="select2" style="width: 20%; height: 30px; appearance: auto; -webkit-appearance: auto;" name="target">
								    <option value="전체">전체</option>
								    <option value="어린이">어린이</option>
								    <option value="청소년">청소년</option>
								    <option value="성인">성인</option>
								    <option value="어르신">어르신</option><!-- 65세이상 -->
								</select>
						    </td>
						</tr>
						<tr>
							<th class="picture">사진&nbsp;첨부</th>
							<td>
								<!-- 실제 파일 첨부 input 요소 -->
								<input type="file" name="upfile" id="fileInput" class="file-upload-input" onchange="showFileName()">
								<!-- 파일명을 보여주는 요소 -->
								<span id="fileNameDisplay">첨부된 파일이 없음</span>
								<!-- 파일첨부 버튼 -->
								<label for="fileInput" class="file-upload-btn">파일 첨부</label>
							</td>
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

<script src="/javaScript/file.js"></script>
<script src="/javaScript/subCulTop.js"></script>
