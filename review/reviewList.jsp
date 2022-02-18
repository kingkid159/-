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
<link rel='stylesheet' href='<%=request.getContextPath()%>/css/reviewList.css?ver=3'>
</head>
<body>
<div id="review_contain">
<a href="<%=request.getContextPath()%>/review/write.do">[후기 작성]</a>
<c:if test="${reviewPage.hasNoReview()}">
후기가 없습니다.
</c:if>
<c:forEach var="review" items ="${reviewPage.content}">
<c:if test="${review.delete.equals('N')}">
	<ul>
		<li class="review_title_contain">
			<div class='review_title'>${review.title}</div>
			<span class='review_writer'>작성자 : ${review.writer}</span> 
			<span class='review_regdate'>작성일 : 
				<!-- value에 변경예정인 날짜를 넣고 pattern에 바꾸고 싶은 형식을 넣는다 -->
				<fmt:formatDate value="${review.regDate}" pattern="yyyy-MM-dd" />
			</span>
		</li>
		<li id="review_content_area">
			<a href="<%=request.getContextPath()%>/review/modify.do?no=${review.number}">수정</a>
			<a href="<%=request.getContextPath()%>/review/delete.do?no=${review.number}">삭제</a>
			<div class='review_content'>이용후기 : ${review.content}</div>
		</li>
	</ul>
	<hr/>
</c:if>
</c:forEach>
<c:if test="${reviewPage.hasReview()}">
	<div class="page_number_contain">
		<c:if test = "${reviewPage.startPage > 5 }">
			<a class="page_number" href="list.do?pageNo=${reviewPage.startPage -5 }">[이전]</a>
		</c:if>
		<c:forEach var ="pNo"
			begin="${reviewPage.startPage}"
			end="${reviewPage.endPage}">
			<a class="page_number" href= "list.do?pageNo=${pNo}">${pNo}</a>
		</c:forEach>
		<c:if test="${reviewPage.endPage <reivewPage.totalPages}">
			<a class="page_number" href="list.do?pageNo=${reviewPage.startPage + 5 }">[다음]</a>
		</c:if>
	</div>
</c:if>
</div>
</body>
</html>