<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<style>
#home{
	background-color:#4285f4;
	border:1px solid #4285f4;

	border-radius:4px;
	color:white;
	width:400px;
	height:45px;
	margin:auto;
	cursor:pointer;
	margin-top:50px;
	
}

#pay{
	border:1px solid #5c5c5c;
	width:500px;
	height:500px;
	display:flex;
	flex-direction:column;
	justify-content:center;
	align-items:center;
	margin:auto;
	margin-top:0px;
	background-color:#242424;
	
}
.imgcontainer__img{
	width:250px;
	hetight:170px;
	float:left;
	margin-bottom:40px;
}
#abc{
	color:white;
	font-size:30px;
}
</style>

<html>
<head>
<title>결제 완료</title>
</head>
<body>
<form id='pay'>
	<div class='imgcontainer'>
	   <a href='/welcome/main.jsp'><img class='imgcontainer__img' src='<%=request.getContextPath()%>/img/welcome.png'></a>
	</div>
	<div id='abc'>구매해 주셔서 감사합니다!</div>
	<div><input type='submit' name='home' id='home' value='홈으로' formaction="/welcome/main.jsp"></div>
    
</form>
</body>
</html>