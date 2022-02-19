<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 오라클에서 받아온 날짜 타입을 원하는 형식으로 변경할때 사용하는 jstl  -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<fmt:formatDate var="frmDate" value="${review.regDate}" pattern="yyyy.MM.dd"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id ="question_contain">
<a href="<%=request.getContextPath()%>/question/write.do">[후기작성]</a>
<c:if test="${questionPage.hasNoQuestion()}">
문의사항이 없습니다.
</c:if>
<c:forEach var="question" items = "${questionPage.content}">
<ul>
	<li class="question_title_contain">
		<div class='question_title'>${question.title}</div>
		<span class='question_writer'>작성자 : ${question.id}</span>
		<span class='question_regdate'>작성일: 
			<fmt:formatDate value="${question.regDate}" pattern="yyyy-mm-dd" />
		</span>
	</li>
	<li id="question_content_area">
		<a href="<%=request.getContextPath()%>/question/modify.do?no=${question.qNo}">수정</a>
		<a href='#'>삭제</a>
		<div class="question_content">이용후기 : ${question.content}</div>
	</li>
</ul>
<hr/>
</c:forEach>
<c:if test="${questionPage.hasQuestion()}">
	<div class="page_number_contain">
		<c:if test = "${questionPage.startPage > 5 }">
			<a class="page_number" href="list.do?pageNo=${questionPage.startPage -5 }">[이전]</a>
		</c:if>
		<c:forEach var ="pNo"
			begin="${questionPage.startPage}"
			end="${questionPage.endPage}">
			<a class="page_number" href= "list.do?pageNo=${pNo}">${pNo}</a>
		</c:forEach>
		<c:if test="${questionPage.endPage <questionPage.totalPages}">
			<a class="page_number" href="list.do?pageNo=${questionPage.startPage + 5 }">[다음]</a>
		</c:if>
	</div>
</c:if>
</div>
</body>
</html>