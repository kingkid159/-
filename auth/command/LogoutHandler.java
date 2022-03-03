package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;

//요청url : http://localhost/op/logout.do
//로그아웃 담당컨트롤러
public class LogoutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LogoutHandler진입");
		HttpSession session = request.getSession(false);
		if (session != null) {	//세션이 존재한다면 -> 로그인된 상태라면
			session.invalidate();	//세션제거
		}
		response.sendRedirect(request.getContextPath()+"/main.jsp");  
		return null;
	}

}
