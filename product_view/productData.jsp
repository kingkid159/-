<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<%@ taglib prefix="u"  tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<style>
#1{
	width:150px;
}
img{
	float:left;
	margin-top:50px;
	
}
#price{
   color: #0088f3 !important;
    /* padding-left: 85px; */
    font-size: 50px;
    margin-left:500px;
}

#name{
	font-size:50px;
	color:#736d72;
}

#info{
	box-sizing: border-box !important;
    width: 600px;
    height: 500px;
    background-color: #fbfbfb;
    outline: 1px solid #e3e3e3;
    padding: 11px 12px;
    font-size: 20px;
    color: #555;
    line-height: 50px;
    margin-bottom: 10px;
    display: -ms-flexbox;
    display: flex;
    overflow: hidden;
    position: relative;
	margin:auto;
	text-align:center;
	margin-top:50px;
}  

#buy{
	background-color:#fa0598;

	border:1px solid #fa0598;
	margin-left:60px;
	border-radius:4px;
	color:white;
	width:200px;
	height:65px;
	
	cursor:pointer;
	
	
}
</style>
<meta charset="UTF-8">
<title>컴퓨터 정보</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
 $(function(){

 });
 </script>
</head>
<body>
<% String pno=request.getParameter("p_no");%>
<jsp:include page="../../header.jsp" flush="false"/>
	<img class='imgcontainer__img' src="<%=request.getContextPath()%>/img/${productData.productDTO.p_name}.png">
<ul>
		
		
		<span id="name"> ${productData.productDTO.p_name}</span></br>
		<li id="info"><u:pre value='${productData.productDTO.info}'/></li></br>
		<div>
		<span id="price">${productData.productDTO.p_price}원</span>
		
		<input type='button' name='buy' id='buy' value='구매하기' onclick ="location.href ='payment.do?p_no=${productData.productDTO.p_no}'">
	
		</div>
</ul>
<div>
			
<hr style="border:solid 10px pink;width:100%;"align="center">
	
</div>
	<embed src="review/list.do?p_no=<%= pno%>" style="width:45%;height:1000px;"></embed>
	<embed src="qna/list.do?p_no=<%= pno%>" style="width:45%;height:1000px;"></embed>
</body>
</html>