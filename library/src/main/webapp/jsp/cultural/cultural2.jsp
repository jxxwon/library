<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="context" value="/"/>
<link href="/css/cultural.css" rel="stylesheet" type="text/css">

<c:import url = "/header"/>

<div class="inner" style="display: flex">
	<div class="sub-menu">
        <c:import url="/subMenuCultural" />
    </div>

    <div class="culturalContainer">
	    <div class="top-cultural">
	    	<div class="top-cbox">
	    		<h1>문화행사 신청</h1>
	    		<p>
	    			지역주민들의 평생학습을 지원하고 건전한 취미, 여가생활을<br>
					영위할 수 있도록 학습 및 교양 프로그램을 운영하고 있습니다.
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
	    
	    <div class="tab_styleA wid80p">
			<ul>
				<li class="on"><h3><a href="#">접수 중 / 예정행사</a></h3></li>
				<li><a href="#">책동산 신청</a></li>
				<li><a href="#">접수 마감된 행사</a></li>
				<li><a href="#">나의 신청내역보기</a></li>
			</ul>
		</div>
 
 		<div class="tableA">
			<table summary="번호, 담당부서, 대상, 행사명, 일정, 상태">
				<caption>행사일정 리스트표</caption>
				<colgroup>
					<col width="5%">
					<col width="*">
					<col width="7%">
					<col width="7%">
					<col width="10%">
					<col width="13%">							
					<col width="13%">
					<col width="10%">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">행사명</th>
						<th scope="col">모집인원</th>
						<th scope="col">대기인원</th>
						<th scope="col">접수형태</th>
						<th scope="col">접수기간</th>								
						<th scope="col">행사기간</th>
						<th scope="col">상태</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
<!-- 								<td>15</td> -->
						<td class="title"><a href="#">[프로그램] 제36회 여름 독서교실 "다같이 지구 한바퀴"</a></td>
						<td>0/15</td>		<!-- 모집한인원 / 전체모집인원 -->
						<td>0/5</td>		<!-- 대기중인원 / 전체대기인원 -->
						<td>온라인 접수<br> 선착순<br> </td>
						<td>23.08.01<br>~23.08.07</td>							
						<td>23.08.08<br>~23.08.11</td>							
						<td><span class="ep_btnD">접수대기중</span></td>
					</tr>
					
					<tr>
						<td>15</td>
<!-- 								<td>1</td> -->
						<td class="title"><a href="#">[전시] 종합자료실 7월 지은이를 찾아서</a></td>
						<td>0/0</td>
						<td>0/0</td>
						<td>자유관람 </td>
						<td>23.07.01<br>~23.07.31</td>							
						<td>23.07.01<br>~23.07.31</td>							
						<td><span class="ep_btnA">접수중</span></td>
					</tr>
	

				</tbody>
			</table>
		</div>
	</div>	    
</div>
<<<<<<< HEAD
<c:import url = "/footer"/>
=======
<c:import url = "/footer"/>
>>>>>>> branch 'jaemin' of git@github.com:jmkwak1/Himedia_Library.git
