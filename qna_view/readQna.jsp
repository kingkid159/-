<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href='<%=request.getContextPath()%>/css/readQna.css?ver=3'>
</head>
<body>
<%String pageNo = request.getParameter("pageNo");%>
<%String pno = request.getParameter("p_no"); %>

<div id='qna_contain'>
	<div class='q_title'>${qnaData.qTitle.QTitle}</div>
	<div class='q_content'>${qnaData.qTitle.QContent}</div>
	<div class='a_content'>${qnaData.qTitle.AContent}</div>
	<a class='listBTN'href='list.do?pageNo=<%=pageNo %>&p_no=<%=pno%>'>목록</a>
</div>

</body>
</html>