<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet'href='./join.css?ver=3'>
</head>
<body>
<form id='login'method='get'action=''name='loginform'>
	<div class='imgcontainer'>
		<img class='imgcontainer__img'src='./img/welcome_gold4.png'>	
	</div>
	<div class='container'>
		<input type='text' name='user'id='user'placeholder='Enter user'required='required'><br>
		<input type='password' name='pwd'id='pwd'placeholder='Enter Password'required='required'><br>
		<input type='submit'name='loginbtn'id='loginbtn'value='Login'>
		<div class='bot_container'>
			<input type='button'class='btnCancel'value='Cancel'>
			<div>
				<span class='psw'>Forgot </span><a class='forgot'href='#'>password?</a>
			</div>
		</div>
	</div>
</form>

</body>
</html>