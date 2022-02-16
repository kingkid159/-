<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/review/modify.do?no=${modReq.reviewNumber}" method="post">
번호:${modReq.reviewNumber}
<h1>수정 작성하는 곳</h1>
<%-- <c:if test="${errors.title}">제목을 입력하세요</c:if> --%>
<input type="text" name="title" value="${modReq.title}">
<textarea name="content">${modReq.content}</textarea>
<input type="submit" value="글 수정">
</form>
</body>
</html>