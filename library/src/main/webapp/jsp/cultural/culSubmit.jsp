<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
    input[type="text"], textarea {
        width: 100%;
        box-sizing: border-box;
        resize: none; /* textarea의 크기 조절 기능 비활성화 */
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
    	padding-right: 10px;
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
							<td><input style="width: 100%; height: 30px" type="text" name="title"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								<textarea style="width: 800px; height: 300px;" rows="10" cols="30" name="content"></textarea>
							</td>
						</tr>
						<tr>
							<th class="picture">사진첨부</th>
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
