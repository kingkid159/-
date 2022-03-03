package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

//마이페이지로 넘어가는 컨트롤러
public class MyPageHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MyPageHandler진입");
		//localhost/welcome/view/member/mypage.jsp
		return "view/member/mypage.jsp";
	}

}
