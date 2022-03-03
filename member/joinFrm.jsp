<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel='stylesheet'href='<%=request.getContextPath()%>/css/join.css?ver=2'>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
  <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    $.datepicker.setDefaults({
        dateFormat: 'yy/mm/dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true ,
        changeYear: true,
        changeMonth: true,
        yearRange: 'c-100:c+10',
        showAnim: "fade",
        closeText: '닫기',
        yearSuffix: '년'
    });

    $(function() {
        $("#birth").datepicker();
        
        
    });

</script>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
   function execDaumPostcode(){
      new daum.Postcode({
         oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
               extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
               extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("roadAddress").value = roadAddr;
            document.getElementById("jibunAddress").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
               document.getElementById("extraAddress").value = extraRoadAddr;
            } else {
               document.getElementById("extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
               var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
               guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
               guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
               var expJibunAddr = data.autoJibunAddress;
               guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
               guideTextBox.style.display = 'block';
            } else {
               guideTextBox.innerHTML = '';
               guideTextBox.style.display = 'none';
            }
         }
      }).open();
   }
</script>


</head>
<body>
<form id='join' method='post'action='join.do'name='joinfrm' >
   <div class='imgcontainer'>
        <a href='/welcome/main.jsp'><img class='imgcontainer__img' src='<%=request.getContextPath()%>/img/welcome.png'></a>
   </div>
   <div class="contain">
   <div>
      <div class='kate'>아이디:</div>
      <div class='kate'>비밀번호:</div>
      <div class='kate'>비밀번호 확인:</div>
      <div class='kate'>이름 :</div>
      <div class='kate'>생년월일:</div>
      <div class='kate'>성별 :</div>
      <div class='kate'>이메일 :</div>
      <div class='kate'>핸드폰 :</div> 
      <div class='kate'>주소 :</div> 
   </div>
   
   <div>
   <div>
   			<input type='text' name='id'id='id'placeholder='아이디'>
   			<c:if test="${errors.id}"><span class='error'>ID를 입력하세요</span></c:if>
   			<c:if test="${errors.duplicateId}"><span class='error'>이미 사용중인 아이디 입니다</span></c:if>
   </br></div>
   
   <div>
   			<input type='password' name='password'id='password'placeholder='비밀번호'>
   			<c:if test="${errors.password}"><span class='error'>암호를 입력하세요</span></c:if>
   <br></div>
   
   <div>
   			<input type='password' name='password2'id='password2'placeholder='비밀번호 확인'>
   			<c:if test="${errors.password2}"><span class='error'>암호를 입력하세요</span></c:if>
   			<c:if test="${errors.notMatch}"><span class='error'>암호가 일치하지 않습니다</span></c:if>
   <br></div>
   
   <div>
   			<input type='text' name='name'id='name'placeholder='이름'>
   			<c:if test="${errors.name}"><span class='error'>이름 입력하세요</span></c:if>
   <br></div>
   
   <div>
		   <input type="text" name="birth"id="birth" >
		   <c:if test="${errors.birth}"><span class='error'>생년월일을 입력하세요</span></c:if>
   </div>
    
   <div>
		   <input type="radio" id='man' name="gender" value="남" checked><span class="man2">남</span>
		   <input type="radio" id='man' name="gender" value="여" checked><span class="man2">여</span></br>
<%-- 		    <c:if test="${errors.gender}"><span class='error'>성별을 선택하세요</span></c:if> --%>
    </br></div>
    
   <div>
   			<input type="text" name="email1"id="email1" maxlength="50">
               <span class="man2"></span>
                        <select name="email2" id="email2">
                            <option>@naver.com</option>
                            <option>@daum.net</option>
                            <option>@gmail.com</option>
                            <option>@nate.com</option>                               
                        </select><c:if test="${errors.email1}"><span class='error'>이메일을 입력하세요</span></c:if>
</br></div>
                   
   <div>
   			<input type="text" name="phone1" id="phone1" maxlength=3/>-<span class="hp">-</span>
        	<input type="text" name="phone2" id="phone2"maxlength=4/><span class="hp">-</span>
         	<input type="text" name="phone3" id="phone3"maxlength=4/>
			<c:if test="${errors.phone}"><span class='error'>핸드폰 번호를 입력하세요</span></c:if>
   </div>  
   <div class='address'>                  
		   <input type="text"name="postcode"id="postcode" placeholder="우편번호">
		   <input class='execDaum'type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
		   <input type="text" name="roadAddress"id="roadAddress" placeholder="도로명주소">
		   <input type="hidden" id="jibunAddress" placeholder="지번주소">
		   <span id="guide" style="color:#999;display:none"></span>
		   <input type="text"name="detailAddress" id="detailAddress" placeholder="상세주소">
		   <input type="hidden" id="extraAddress" placeholder="참고항목">
		   <c:if test="${errors.address}"><span class='error'>주소를 입력하세요</span></c:if>
   </div> 

   </div>
   </br>
   </div>
   <div><input type='submit'name='loginbtn'id='loginbtn'value='회원가입'></div>

   
   </form>
   


</body>
</html>