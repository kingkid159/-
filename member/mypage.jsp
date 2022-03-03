<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<link rel='stylesheet'href='<%=request.getContextPath()%>/css/mypage.css?ver=2'>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>

</head>
<body>
<form id='mypage' method='post'name='mypage' >
   <div class='imgcontainer'>
     <a href='/welcome/main.jsp'><img class='imgcontainer__img' src='<%=request.getContextPath()%>/img/welcome.png'></a>
   </div>
   <div class="contain">
   <div>
      <div class='kate'>아이디:</div>
      <div class='kate'>이름 :</div>
      <div class='kate'>생년월일: </div>
      <div class='kate'>성별 : </div>
      <div class='kate'>이메일 : </div>
      <div class='kate'>핸드폰 : </div> 
      <div class='kate'>주소 : </div> 
   </div>
	
	<div>
	 <input type='text' name='id'id='id'value='${AUTHUSER.id}' disabled>
	 <input type='text' name='name'id='name'value='${AUTHUSER.name}'disabled>
	 <input type="text" name="birth"id="birth" value='${AUTHUSER.birth}'disabled>
	 <input type="text" id='gender' name="gender" value="${AUTHUSER.gender}"disabled>
	 <input type="text" name="email"id="email" value='${AUTHUSER.email}'maxlength="50"disabled>
	 <input type="text" name="phone" id="phone"value='${AUTHUSER.phone}'maxlength="50"disabled>
	 <input type="text"name="address"id='address'value='${AUTHUSER.address}'disabled>
	</div>
   </div>
   </br>
   <div>
   	<input type='button' name='changepassword' id='changepassword' value='비밀번호 수정' onclick ="location.href = 'changePwd.do'">
	<input type='submit' name='home' id='home' value='홈으로' formaction="/welcome/main.jsp">
	</div>
   </form>
   
</body>
</html>