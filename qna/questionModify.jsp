<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%String pno = request.getParameter("p_no"); %>
<input type='hidden' name='p_no' value='<%=pno %>'>
<form action="<%=request.getContextPath()%>/qna/modify.do" method="post">
번호:${modReq.questionNumber}
<h1>수정 작성하는 곳</h1>
<input type='hidden' name='p_no' value='<%=pno %>'>
<input type='hidden' name='no' value='${modReq.questionNumber}'>
<%-- <c:if test="${errors.title}">제목을 입력하세요</c:if> --%>
<input type="text" name="title" value="${modReq.title}">
<textarea name="content">${modReq.content}</textarea>
<input type="submit" value="글 수정">
</form>
</body>
</html>