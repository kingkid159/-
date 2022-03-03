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
			crossorigin="anonymous">
			
</script>


<style>
  <style media="screen">
    .div2 {
      border:1px solid;
      width: 100px;
      height: 50px;
      display: flex;
      align-items: center;
      justify-content: center;
    }
  </style></style>

<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/img/welcome.png">
<link rel="stylesheet"href="<%=request.getContextPath()%>/css/header.css?ver=2">
</head>
<body>
	<c:if test="${n>0}"><script>alert("회원가입 성공")</script> </c:if>
<%-- 	<c:if test="${n<=0}"><script>alert("회원가입 실패")</script></c:if> --%>
<div id="all_conatain">
	<nav class="navbar">
	<!-- 	로그인 했을경우 -->
		<ul class="nav__ul">
			<c:if test="${! empty AUTHUSER}">
				<li class="nav__li">
					안녕하세요 ${AUTHUSER.name}님
				</li>
				<li class="nav__li">
					<a class="nav__a"href="logout.do">로그아웃</a>
				</li>
				<li class="nav__li">
					<a class="nav__a"href="myPage.do">마이페이지</a>
				</li>
		=
			</c:if>		
		<!-- 로그인 안 했을 경우 -->
			<c:if test="${empty AUTHUSER}">
				<li class="nav__li">
					<a class="nav__a" href="joinFrm.do">회원가입</a>
				</li>
				<li class="nav__li">
					<a class="nav__a"href="login.do">로그인</a>
				</li>
			
			</c:if>
		</ul>
	</nav>
	<form id="search_frm" action="search.do"method='get'>
		<div class="img_contain">
			<a href="main.jsp">
				<img class="logo"src="<%=request.getContextPath()%>/img/welcome.png"alt="logo_img">
			</a>
		</div>	
		<div class="search_box">
			<input id="search"type="text" name="search"placeholder=" 검색어를 입력하세요"/>
		<!-- input img로 변경 예정 -->
			<input id="search_btn" type="submit"style=display:none;>
			<label for="search_btn" class="search_btn__label"><i class="fas fa-search"></i></label>
		</div>
	</form>
	<div class="kate">
		<!-- 	 <ul class="kate__ul">
			
			
			<div class="kate__li"><a class='kate__li__a' href="productList.do?t_no=1">사무용</a></div>
			<div class="kate__li"><a class='kate__li__a' href="productList.do?t_no=2">게임용</a></div>
			<div class="kate__li"><a class='kate__li__a' href="productList.do?t_no=3">그래픽디자인</a></div>
			<div class="kate__li"><a class='kate__li__a' href="productList.do?t_no=4">영상편집</a></div>
			<div class="kate__li"><a class='kate__li__a' href="productList.do?t_no=5">프로그래밍용</a></div>
		
		</ul> -->	
		
    	<div class="a1">
    		
    		<c:if test="${productList.t_no eq 1 }">
			<a style="color:yellow; font-size:30px;" class='kate__li__a' href="productList.do?t_no=1">사무용</a>
			</c:if>
		
			<c:if test="${productList.t_no ne 1 }">
			<a class='kate__li__a' href="productList.do?t_no=1">사무용</a>
			</c:if>
				
			<c:if test="${productList.t_no eq 2 }">
			<a style="color:yellow; font-size:30px;" class='kate__li__a' href="productList.do?t_no=2">게임용</a>
			</c:if>	
			<c:if test="${productList.t_no ne 2 }">
			<a class='kate__li__a' href="productList.do?t_no=2">게임용</a>
			</c:if>
			<c:if test="${productList.t_no eq 3 }">
			<a style="color:yellow; font-size:30px;" class='kate__li__a' href="productList.do?t_no=3">그래픽디자인</a>
			</c:if>
			<c:if test="${productList.t_no ne 3 }">
			<a class='kate__li__a' href="productList.do?t_no=3">그래픽디자인</a>
			</c:if>
			<c:if test="${productList.t_no eq 4 }">
			<a style="color:yellow; font-size:30px;" class='kate__li__a' href="productList.do?t_no=3">영상편집</a>
			</c:if>				
			<c:if test="${productList.t_no ne 4 }">
			<a class='kate__li__a' href="productList.do?t_no=4">영상편집</a>
			</c:if>			
			<c:if test="${productList.t_no eq 5 }">
			<a style="color:yellow; font-size:30px;" class='kate__li__a' href="productList.do?t_no=5">프로그래밍용</a>
			</c:if>
			<c:if test="${productList.t_no ne 5 }">
			<a class='kate__li__a' href="productList.do?t_no=5">프로그래밍용</a>
			</c:if>
		</div>

    	</div>
	</div>

</div>


</body>
</html>