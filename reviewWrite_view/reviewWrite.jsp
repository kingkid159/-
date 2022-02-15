<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"href="<%=request.getContextPath() %>/css/reviewWrite.css?ver=3">

</head>
<body>			
<!-- 작성해서보낼프로퍼티 -->
<jsp:include page="../../header.jsp" flush="false"/>
<h1 class='title'>후기 작성</h1>
	<form id='reviewW'action="./write.do" method='post'>
		<div class='review_title'>제목
			<input class='text'type='text' placeholder='제목을 입력하세요'name='title'value="${param.title}">
			<c:if test="${errors.title}"><script>alert("제목을 입력하세요")</script></c:if>
		</div>
		<div>
			<span class='content'>내용:</span>
			<textarea class='textarea'name="content" rows='10'cols='30'placeholder='내용을 입력하세요!'></textarea>
			
		</div>
		<input class='review_btn'type='submit' value='등록'/>
		<button class='review_btn'>취소</button>
	</form>
</body>
</html>