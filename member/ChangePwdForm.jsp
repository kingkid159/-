<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>암호 변경</title>
<link rel='stylesheet'href='<%=request.getContextPath()%>/css/pwd.css?ver=3'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>

	<form id='pwd' action="changePwd.do" method="post">
	<div class='imgcontainer'>
      <a href='/welcome/main.jsp'><img class='imgcontainer__img' src='<%=request.getContextPath()%>/img/welcome.png'></a>
     </div>
<div class='pwdcontain'>
   <div>
      <div class='kate'>현재 암호:</div>
      <div class='kate'>새 암호:</div>
   </div>
		
		<div>
		
		<input type="password" name="curPwd" id="password">
		<c:if test="${errors.curPwd}"> <script>alert("암호를 입력하세요.")</script></c:if>
		<c:if test="${errors.badCurPwd}"> <script>alert("현재 암호가 틀립니다.")</script></c:if>
	
		<input type="password" name="newPwd" id="password">
		<c:if test="${errors.newPwd}"><script>alert("새 암호를 입력하세요.")</script></c:if>
		</div>
</div>	
	<input type="submit" value="암호 변경" id='loginbtn'>
	
</form> 
</body>
</html>