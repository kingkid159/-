<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<%@ taglib prefix="u"  tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<style >
#aa{
	width: 550px;
	display : inline-block;
	border: 1px solid black;
	height: 750px;
}
ui{
	margin: 20px;
}
#info{
	box-sizing: border-box !important;
    width: 250px;
    height: 300px;
    background-color: #fbfbfb;
    outline: 1px solid #e3e3e3;
    padding: 11px 12px;
    font-size: 11px;
    color: #555;
    line-height: 22.5px;
    margin-bottom: 10px;
    display: -ms-flexbox;
    display: flex;
    overflow: hidden;
    position: relative;
	margin:auto;
	text-align:center;
	
}  
#price{
   color: #0088f3 !important;
    /* padding-left: 85px; */
    font-size: 18pt;
}
#name{
    font-size: inherit;
    font-weight: inherit;
    color:#736d72;

   
    
}
.imgcontainer__img{
	height:250px;
	margin-top:20px;
}



.a1{
	background-color:#fa0598;
	text-decoration:none;   
	
}
ul{
	min-width:1430px;
}
</style>
<meta charset="UTF-8">
<title>컴퓨터</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
  $(function(){
   
 });
 </script>
</head>
<body>
<%@ include file="../../header.jsp" %>
<%-- <jsp:include page="../../header.jsp" flush="false"/> 

	<div class="kate">
		<ul class="kate__ul">
			<c:if test="${productList.t_no eq 1 }">
			<div class="kate__li bg1"><a class='kate__li__a' href="productList.do?t_no=1">사무용</a></div>
			</c:if>
			<c:if test="${productList.t_no ne 1 }">
			<div class="kate__li"><a class='kate__li__a' href="productList.do?t_no=1">사무용</a></div>
			</c:if>
			
			<!-- <div id="i1" class="kate__li"><a class='kate__li__a' href="productList.do?t_no=1">사무용</a></div> -->
			<div id="i2"  class="kate__li"><a class='kate__li__a' href="productList.do?t_no=2">게임용</a></div>
			<div id="i3"  class="kate__li"><a class='kate__li__a' href="productList.do?t_no=3">그래픽디자인</a></div>
			<div class="kate__li"><a class='kate__li__a' href="productList.do?t_no=4">영상편집</a></div>
			<div class="kate__li"><a class='kate__li__a' href="productList.do?t_no=5">프로그래밍용</a></div>
		
		</ul>
   
	</div> --%>
	<hr/>

<ul>
<c:forEach var="product" items="${productList.content}">
            <li id="aa">
            	<a href='productData.do?p_no=${product.p_no}'>
            		<img class='imgcontainer__img' src="<%=request.getContextPath()%>/img/${product.p_name}.png">
            	</a><br/>
                  <span id="name"><h2 productData.do?p_no=${product.p_no}">${product.p_name}</h2></span><br/>
                  <span id="info"><u:pre value='${product.info}'/></span><br/>
                  <span id="price">${product.p_price}원</span><br/>
            </li> 

	
   </c:forEach>
</ul>
</table>
</body>
</html>