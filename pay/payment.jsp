<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>결제</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name ="viewport" content="width=device-width,initial-scale=1.0">

<link rel="stylesheet"href="bootstrap/css/bootstrap.css">

<!-- 스크립트 적용 -->
<script src= "bootstrap/js/jquery.min.js"></script>
<script src= "bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<style type="text/css">
   
#pay{
	border:1px solid #5c5c5c;
	width:500px;
	height:500px;
	display:flex;
	flex-direction:column;
	justify-content:center;
	align-items:center;
	margin:auto;
	margin-top:30px;
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
	width:400px;
}

#abb{
	color:white;
	font-size:30px;
	margin-bottom:20px;
}
</style>
</head>
<body>
<form id='pay'>
<div class='imgcontainer'>
	   <a href='/welcome/main.jsp'><img class='imgcontainer__img' src='<%=request.getContextPath()%>/img/welcome.png'></a>
	</div>
	<div><div id='abb'>결제 방식 선택</div></div>
   <div id="abc">
      <a id="check_creditCard" class="btn btn-outline-primary btn-block" >신용카드</a>   
      <a id="check_real-timeBankTransfer" type="button" class="btn btn-outline-primary btn-block">계좌이체</a>
      <a id="check_vbank" type="button" class="btn btn-outline-primary btn-block">가상계좌</a>
      <a id="check_phone" type="button" class="btn btn-outline-primary btn-block">휴대폰 결제</a>  
      
   </div>
 </form>
 <!-- 신용카드 -->
<script>
	$("#check_creditCard").click(function () {
		var IMP = window.IMP; // 생략가능
        IMP.init('imp18444472'); // 'imp00000000' 대신 부여받은 "가맹점 식별코드"를 사용

        IMP.request_pay({
            pg: 'html5_inicis',
            /* 
                'kakao':카카오페이, 
               	'html5_inicis':이니시스(웹표준결제)
                'nice':나이스페이
                'jtnet':제이티넷
                'uplus':LG유플러스
                'danal':다날
                'payco':페이코
                'syrup':시럽페이
                'paypal':페이팔
            */
            
            pay_method: 'card',
            /* 
                'samsung':삼성페이, 
                'card':신용카드, 
                'trans':실시간계좌이체,
                'vbank':가상계좌,
                'phone':휴대폰소액결제 
            */
            
            merchant_uid: 'merchant_' + new Date().getTime(),
            /* 
			merchant_uid에 경우 
			https://docs.iamport.kr/implementation/payment
			위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
             */
             
            //결제창에서 보여질 이름
            name: '${productData.productDTO.p_name}',
            //가격 
            amount: '${productData.productDTO.p_price}', 
            buyer_email: 'iamport@siot.do',
            buyer_name: '${productData.productDTO.p_name}', 
            buyer_tel: '${AUTHUSER.phone}',  
            buyer_addr: '서울특별시 강남구 삼성동',
            buyer_postcode: '123-456',
            m_redirect_url: 'https://www.yourdomain.com/payments/complete'
            /*  
		         모바일 결제시,
		         결제가 끝나고 랜딩되는 URL을 지정 
            (카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐) 
            */
        }, function (rsp) {
            console.log(rsp);
            if (rsp.success) {
                var msg = '결제가 완료되었습니다.';
                msg += '고유ID : ' + rsp.imp_uid;
                msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                msg += '\n결제 금액 : ' + rsp.paid_amount;
                msg += '\n카드 승인번호 : ' + rsp.apply_num;
                location.href="<%=request.getContextPath()%>/view/pay/paymentSuccess.jsp";
            } else {
                var msg = '결제에 실패하였습니다.';
                msg += '\n에러내용 : ' + rsp.error_msg;
                location.href="#";
            }
            alert(msg);
        });
    });
</script>



<!--소액결제-->
<script>
   $("#check_phone").click(function () {
      var IMP = window.IMP;
        IMP.init('imp18444472'); 
        IMP.request_pay({
            pg: 'danal',
            pay_method: 'phone',
            merchant_uid: 'merchant_' + new Date().getTime(),
            //결제창에서 보여질 이름
            name: '${productData.productDTO.p_name}',
            //가격 
            amount: '${productData.productDTO.p_price}', 
            buyer_email: 'iamport@siot.do',
            buyer_name: '구매자이름',
            buyer_tel: '010-1234-5678',
            buyer_addr: '서울특별시 강남구 삼성동',
            buyer_postcode: '123-456',
            m_redirect_url: 'https://www.yourdomain.com/payments/complete'
        }, function (rsp) {
            console.log(rsp);
            if (rsp.success) {
                var msg = '결제가 완료되었습니다.';
                msg += '고유ID : ' + rsp.imp_uid;
                msg += '상점 거래ID : ' + rsp.merchant_uid;
                msg += '결제 금액 : ' + rsp.paid_amount;
                msg += '카드 승인번호 : ' + rsp.apply_num;
                location.href="<%=request.getContextPath()%>/view/pay/paymentSuccess.jsp";
            } else {
                var msg = '결제에 실패하였습니다.';
                msg += '\n에러내용 : ' + rsp.error_msg;
                location.href="#";
            }
            alert(msg);
        });
    });
</script>

<!--  계좌이체 -->
<script>
   $("#check_real-timeBankTransfer").click(function () {
      var IMP = window.IMP;
        IMP.init('imp18444472'); 
        IMP.request_pay({
            pg: 'danal',
            pay_method: 'trans',
            merchant_uid: 'merchant_' + new Date().getTime(),
            //결제창에서 보여질 이름
            name: '${productData.productDTO.p_name}',
            //가격 
            amount: '${productData.productDTO.p_price}',   
            buyer_email: 'iamport@siot.do',
            buyer_name: '구매자이름',
            buyer_tel: '010-1234-5678',
            buyer_addr: '서울특별시 강남구 삼성동',
            buyer_postcode: '123-456',
            m_redirect_url: 'https://www.yourdomain.com/payments/complete'
        }, function (rsp) {
            console.log(rsp);
            if (rsp.success) {
                var msg = '결제가 완료되었습니다.';
                msg += '고유ID : ' + rsp.imp_uid;
                msg += '상점 거래ID : ' + rsp.merchant_uid;
                msg += '결제 금액 : ' + rsp.paid_amount;
                msg += '카드 승인번호 : ' + rsp.apply_num;
                location.href="<%=request.getContextPath()%>/view/pay/paymentSuccess.jsp";
            } else {
                var msg = '결제에 실패하였습니다.';
                msg += '\n에러내용 : ' + rsp.error_msg;
                location.href="#";
            }
            alert(msg);
        });
    });
</script>

<!-- 아임포트 다날 가상계좌 구현 코드 -->
<script>
   $("#check_vbank").click(function () {
      var IMP = window.IMP;
        IMP.init('imp18444472'); 
        IMP.request_pay({
            pg: 'danal',
            pay_method: 'vbank',
            merchant_uid: 'merchant_' + new Date().getTime(),
            //결제창에서 보여질 이름
            name: '${productData.productDTO.p_name}',
            //가격 
            amount: '${productData.productDTO.p_price}', 
            buyer_email: 'iamport@siot.do',
            buyer_name: '구매자이름',
            buyer_tel: '010-1234-5678',
            buyer_addr: '서울특별시 강남구 삼성동',
            buyer_postcode: '123-456',
            m_redirect_url: 'https://www.yourdomain.com/payments/complete'
        }, function (rsp) {
            console.log(rsp);
            if (rsp.success) {
                var msg = '결제가 완료되었습니다.';
                msg += '고유ID : ' + rsp.imp_uid;
                msg += '상점 거래ID : ' + rsp.merchant_uid;
                msg += '결제 금액 : ' + rsp.paid_amount;
                msg += '카드 승인번호 : ' + rsp.apply_num;
                location.href="<%=request.getContextPath()%>/view/pay/paymentSuccess.jsp";
            } else {
                var msg = '결제에 실패하였습니다.';
                msg += '\n에러내용 : ' + rsp.error_msg;
                location.href="#";
            }
            alert(msg);
        });
    });           
    </script>

<form>
<input type="hidden" name="PaymentType" id="type"/>
<input type="hidden" name="total" id="type"/>
</form>

</body>
</html>