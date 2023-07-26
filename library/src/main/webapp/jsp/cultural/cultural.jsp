<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="context" value="/"/>
<link href="${context}/static/css/cultural.css" rel="stylesheet" type="text/css">

<c:import url="/default/header.jsp" />

<div class="inner" style="display: flex">
    <c:import url="/subMenuCultural" />
    <h1>문화행사</h1>
</div>

<c:import url="/default/footer" />