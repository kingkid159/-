<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"href="<%=request.getContextPath() %>/css/Write.css?ver=4">
</head>
<body>
<% String pno=request.getParameter("p_no");%>
<form id='reviewD' action="<%=request.getContextPath()%>/review/delete.do" method="post">
<div class='delete'>삭제하시겟습니까?</div>
<input type="hidden" name="no" value="${delReq.reviewNumber}">
<input type="hidden" name="title" value="${delReq.title}">
<input type="hidden" name="content" value="${delReq.content}">
<input type='hidden' name='p_no'value="<%=pno%>">

<input type="submit" class='review_btn' value="삭제">
</form>
</body>
</html>