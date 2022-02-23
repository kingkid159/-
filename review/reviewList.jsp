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
<div id="review_contain">
<a class='wBTN' href='<%=request.getContextPath()%>/review/write.do?p_no=<%=pno %>'>후기 작성</a>
<c:if test="${reviewPage.hasNoReview()}">
후기가 없습니다.
</c:if>
<c:forEach var="review" items ="${reviewPage.content}">
	<ul>
		<li class="title_contain">
			<span class='title'>${review.title}</span>
			<span class='writer'>작성자 : ${review.writer}</span> 
			<span class='regdate'>작성일 : 
				<!-- value에 변경예정인 날짜를 넣고 pattern에 바꾸고 싶은 형식을 넣는다 -->
				<fmt:formatDate value="${review.regDate}" pattern="yyyy-MM-dd" />
			</span>
		</li>
		<li id="review_content_area">
			<div class='BTN_contain'>
				<a class='mdaBTN' href="<%=request.getContextPath()%>/review/modify.do?no=${review.number}&p_no=<%=pno %>">수정</a>
				<a class='mdaBTN'href="<%=request.getContextPath()%>/review/delete.do?no=${review.number}&p_no=<%=pno %>">삭제</a>
			</div>
			<div class='content'>이용후기 : ${review.content}</div>
		</li>
	</ul>
	<hr/>
</c:forEach>
<c:if test="${reviewPage.hasReview()}">
	<div class="page_number_contain">
		<c:if test = "${reviewPage.startPage > 5 }">
			<a class="page_number" href="list.do?pageNo=${reviewPage.startPage -5 }&p_no=<%=pno %>">이전</a>
		</c:if>
		<c:forEach var ="pNo"
			begin="${reviewPage.startPage}"
			end="${reviewPage.endPage}">
			<a class="page_number" href= "list.do?pageNo=${pNo}&p_no=<%=pno %>">${pNo}</a>
		</c:forEach>
		<c:if test="${reviewPage.endPage <reivewPage.totalPages}">
			<a class="page_number" href="list.do?pageNo=${reviewPage.startPage + 5 }&p_no=<%=pno %>">다음</a>
		</c:if>
	</div>
</c:if>
</div>
</body>
</html>