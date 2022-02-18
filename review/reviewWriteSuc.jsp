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
게시글을 등록했습니다.
<br>

<a href='<%=request.getContextPath()%>/review/list.do'>[게시글 목록보기]</a>
<a href='${ctxPath}/article/read.do?no=${newArticleNo}'>[게시글 내용보기]</a>
</body>
</html>