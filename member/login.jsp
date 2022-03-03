<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet'href='<%=request.getContextPath()%>/css/login.css?ver=1'>
</head>
<body>
<form id='login'method='post'action='login.do'name='loginform'>
	<div class='imgcontainer'>
		<a href='/welcome/main.jsp'><img class='imgcontainer__img' src='<%=request.getContextPath()%>/img/welcome.png'></a>	
	</div>
	<div class='container'>
		<input type='text' name='id'id='id'placeholder='Enter user'>
		<c:if test="${errors.id}"><span class='error'>ID를 입력하세요</span></c:if></br>
		<input type='password' name='password'id='password'placeholder='Enter Password'>
		<c:if test="${errors.password}"><span class='error'>비밀번호를 입력하세요</span></c:if>
		</br>
		<c:if test="${errors.idOrPwNotMatch}"><span class='error'>아이디 또는 비밀번호가 일치하지않습니다</span></c:if></br>
		<input type='submit'name='loginbtn'id='loginbtn'value='Login'>
		</br>
		<div class='bot_container'>
		<a href="/welcome/main.jsp">HOME으로 돌아가기</a>
		</div>
	</div>
</form>

</body>
</html>