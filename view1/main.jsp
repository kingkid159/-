<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/d49fa1b12d.js" 
			crossorigin="anonymous"
></script>
<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/img/welcome.png">
<link rel="stylesheet"href="<%=request.getContextPath()%>/css/main.css?ver=4">
</head>
<body>
<jsp:include page="header.jsp" flush="false"/>
	<div id='right_box'>
	<div id='right_top'>
	
	</div>
	<div id='right_bot'>
	<div class="slider">
		    <input type="radio" name="slide" id="slide1" checked>
		    <input type="radio" name="slide" id="slide2">
		    <input type="radio" name="slide" id="slide3">
		    <input type="radio" name="slide" id="slide4">
		     <input type="radio" name="slide" id="slide5">
    <ul id="imgholder" class="imgs">
        <li><a href="productList.do?t_no=2"><img src="<%=request.getContextPath()%>/img/lol.jpg"class='img'></a></li>
        <li><a href="productList.do?t_no=3"><img src="<%=request.getContextPath()%>/img/grapic.jpg"class='img'></a></li>
        <li><a href="productList.do?t_no=1"><img src="<%=request.getContextPath()%>/img/work.jpg"class='img'></a></li>
        <li><a href="productList.do?t_no=4"><img src='<%=request.getContextPath()%>/img/grapic.png'class='img'></a></li>
        <li><a href="productList.do?t_no=5"><img src='<%=request.getContextPath()%>/img/programming.jpg'class='img'></a></li>
    </ul>
    <div class="bullets">
        <label for="slide1">게임용PC</label>
        <label for="slide2">그래픽전문가용</label>
        <label for="slide3">사무용PC</label>
        <label for="slide4">영상편집용</label>
        <label for="slide5">프로그래밍용</label>
    </div>
	</div>
</div>
	
</body>
</html>