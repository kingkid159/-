<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>삭제페이지</h2>
<%String pno = request.getParameter("p_no"); %>
<input type='hidden' name='p_no' value='<%=pno %>'>
<form action="<%=request.getContextPath()%>/qna/delete.do" method="post">
번호:${delReq.questionNumber}
<input type="hidden" name="no" value="${delReq.questionNumber}">
<input type="hidden" name="title" value="${delReq.title}">
<input type="hidden" name="content" value="${delReq.content}">
<input type='hidden' name='p_no' value='<%=pno %>'>
<input type="submit"value="삭제">
</form>
</body>
</html>