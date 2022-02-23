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
<% String qno=request.getParameter("no");%>
<%String pno = request.getParameter("p_no"); %>
<%= qno%>
<%=pno %>
<form action='./awrite.do'method='post'>
	<textarea class='textarea'name='content'></textarea>
	
	<input type='hidden' name='no'value="<%=qno%>">
	<input type='hidden' name='p_no' value='<%=pno %>'>
	<input type='submit'>
</form>
</body>
</html>