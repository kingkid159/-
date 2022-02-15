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
<table class='review'>
	<tr>
		<td>
			<a href="<%=request.getContextPath()%>/review/write.do">[게시글 쓰기]</a>
	<tr class='review__tr'>
		<td class='review__number'>번호</td>
		<td class='review__title'>제목</td>
		<td class='review__Writer'>작성자</td>
		<td class='reivew__hit'>조회수</td>
		<td class='review__regdate'>작성일</td>
	</tr>
<c:if test="${reviewPage.hasNoReview()}">
	<tr>
		<td>게시글이 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="review" items ="${reviewPage.content }">
	<ul>
		<li>
			<span>작성자 : ${review.writer}</span> <span>작성일 : ${review.regDate }</span>
		</li>
		<li style="white-space:pre;">
			이용후기 : ${review.content}
		</li>
	</ul>
	 <tr>
		<td>${review.number}</td>
		<td>
		<!-- 읽기기능 구현 후 추가할 경로 -->
			<a href="#">
				<c:out value="${review.title}"/>
			</a>
		</td>
		<td>${review.writer}</td>
		<td>${review.readCount}</td>
		<td>${review.regDate }</td>
	</tr>
</c:forEach>
<c:if test="${reviewPage.hasReview()}">
	<tr>
		<td>
			<c:if test = "${reviewPage.startPage > 5 }">
				<a href="list.do?pageNo=${reviewPage.startPage -5 }">[이전]</a>
			</c:if>
			<c:forEach var ="pNo"
				begin="${reviewPage.startPage}"
				end="${reviewPage.endPage}">
				<a href= "list.do?pageNo=${pNo}">[${pNo}]</a>
			</c:forEach>
			<c:if test="${reviewPage.endPage <reivewPage.totalPages}">
			<a href="list.do?pageNo=${reviewPage.startPage + 5 }">[다음]</a>
			</c:if>
		</td>
	</tr>
</c:if>
	
</table>
</body>
</html>