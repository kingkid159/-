<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>삭제성공</h2>
<%response.sendRedirect("list.do?p_no="+request.getParameter("p_no")); %>
</body>
</html>