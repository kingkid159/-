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
<body>			<!-- 작성해서보낼프로퍼티 -->
	<form id='reviewW'action="#" method='post'>
		<div class='review_title'>제목
			<input type='text' name='title'value="${param.title}">
			<c:if test="${errors.title}">제목을 입력하세요</c:if>
		</div>
		<div>
			내용:<br/>
			<textarea name="content" rows='10'cols='30'>
			${param.content}
			</textarea>
		</div>
		<input type='submit' value='등록'/>
	</form>
</body>
</html>