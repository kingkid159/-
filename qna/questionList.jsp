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
<link rel='stylesheet' href='<%=request.getContextPath()%>/css/list.css?ver=2'>
</head>
<body>

<% String pno=request.getParameter("p_no");%>
<div id ="question_contain">
	<a class='wBTN' href="<%=request.getContextPath()%>/qna/write.do?p_no=<%=pno %>">문의작성</a>
	<c:if test="${questionPage.hasNoQuestion()}">
		문의사항이 없습니다.
	</c:if>
<c:forEach var="question" items = "${questionPage.content}">
	<ul>
		<li class="title_contain">
			<a class='title' 
				href='<%=request.getContextPath()%>/qna/aread.do?no=${question.qNo}&p_no=<%=pno%>&pageNo=${questionPage.currentPage}'
				>${question.title}
			</a>
			<span class='writer'>작성자 : ${question.id}</span>
			<span class='regdate'>작성일: 
				<fmt:formatDate value="${question.regDate}" pattern="yyyy-mm-dd" />
			</span>
		</li>
		<li id="question_content_area">
			<div class='BTN_contain'>
				<a class='mdaBTN' href="<%=request.getContextPath()%>/qna/modify.do?no=${question.qNo}&p_no=<%=pno%>">수정</a>
				<a class='mdaBTN' href="<%=request.getContextPath()%>/qna/delete.do?no=${question.qNo}&p_no=<%=pno%>">삭제</a>
				<c:if test="${authUser.id.equals(admin)}">
				<a class='mdaBTN' href="<%=request.getContextPath()%>/qna/awrite.do?no=${question.qNo}&p_no=<%=pno%>">답변작성</a>
				</c:if>
			</div>
			<div class="content">문의내용 : ${question.content}</div>
		</li>
	</ul>
<hr/>

</c:forEach>
<c:if test="${questionPage.hasQuestion()}">
	<div class="page_number_contain">
		<c:if test = "${questionPage.startPage > 5 }">
			<a class="page_number" href="list.do?pageNo=${questionPage.startPage -5 }&p_no=<%=pno %>">이전</a>
		</c:if>
		<c:forEach var ="pNo"
			begin="${questionPage.startPage}"
			end="${questionPage.endPage}">
			<a class="page_number" href= "list.do?pageNo=${pNo}&p_no=<%=pno %>">${pNo}</a>
		</c:forEach>
		<c:if test="${questionPage.endPage <questionPage.totalPages}">
			<a class="page_number" href="list.do?pageNo=${questionPage.startPage + 5 }&p_no=<%=pno %>">다음</a>
		</c:if>
	</div>
</c:if>
</div>
</body>
</html>