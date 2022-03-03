<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"href="<%=request.getContextPath() %>/css/Write.css?ver=2">
</head>
<body>
<% String pno=request.getParameter("p_no");%>
<h1 class='title'>후기 수정</h1>
	<form id='reviewM' action="<%=request.getContextPath()%>/review/modify.do" method="post">
		
	<%-- <c:if test="${errors.title}">제목을 입력하세요</c:if> --%>
		<div class='review_title'>제목:
			<input type="text" class='text' name="title" value="${modReq.title}">
		</div>	
		<div>	
			<span class='content'>내용:</span>	
			<input type='hidden' name='no' value='${modReq.reviewNumber}'>
			<input type='hidden' name='p_no'value="<%=pno%>">
			<textarea class='textarea' name="content">${modReq.content}</textarea>
		</div>
		<input type="submit" class='reviewM_btn' value="글 수정">
	</form>
</body>
</html>