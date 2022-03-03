package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

//요청url : http://localhost/op/member/loginFrm.do
//로그인폼 보여주기-연습용  담당컨트롤러
//모든 컨트롤러는 반드시 CommandHandler인터페이스를 구현해야 한다.
public class LoginFrmHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LoginFrmHandler진입");
		//컨트롤러가 해야할 일
		//1.파라미터 가져오기
		//2.비즈니스로직수행<->Service<->DAO<->DB
		//3.Model - session, request사용
		//4.View
//		http://localhost/op/member/loginFrm.do
//			             ../view/member/loginForm.jsp
//		http://localhost/op/view/member/loginForm.jsp
		return "../view/member/login.jsp";
	}

}









