<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"href="<%=request.getContextPath() %>/css/Write.css?ver=9">
</head>
<body>
<% String qno=request.getParameter("no");%>
<%String pno = request.getParameter("p_no"); %>
<h1 class='title'>답변 작성</h1>
<form id='answerW'action='./awrite.do'method='post'>
	<div>
		<span class='content'>내용:</span>	
		<textarea class='textarea'name='content'></textarea>
	</div>
	<input type='hidden' name='no'value="<%=qno%>">
	<input type='hidden' name='p_no' value='<%=pno %>'>
	<input  class='reviewM_btn'  type='submit' value='작성'>
</form>
</body>
</html>