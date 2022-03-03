<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"href="<%=request.getContextPath() %>/css/Write.css?ver=7">
</head>
<body>
<%String pno = request.getParameter("p_no"); %>
<h1 class='title'>문의 수정</h1>
<form id='questionM'action="<%=request.getContextPath()%>/qna/modify.do" method="post">
	<input type='hidden' name='p_no' value='<%=pno %>'>
	<input type='hidden' name='p_no' value='<%=pno %>'>
	<input type='hidden' name='no' value='${modReq.questionNumber}'>
	<%-- <c:if test="${errors.title}">제목을 입력하세요</c:if> --%>
	<div class='review_title'>제목:
		<input type="text" class='text' name="title" value="${modReq.title}">
	</div>
	<div>
		<span class='content'>내용:</span>	
		<textarea name="content"class='textarea'>${modReq.content}</textarea>
	</div>
		<input type="submit" class='reviewM_btn'  value="글 수정">
</form>
</body>
</html>