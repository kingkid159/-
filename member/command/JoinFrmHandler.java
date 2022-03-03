package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

//  http://localhost:포트번호/컨텍스트패스/member/joinFrm.do 요청
//  http://localhost/op/member/joinFrm.do 요청

//(여기에서는)모든 컨트롤러는 반드시 CommandHandler인터페이스를 구현해야 한다.
//이 클래스는  회원가입폼 보여주기 담당컨트롤러이다
//class 클래스명   implements 인터페이스명,..,인터페이스명
public class JoinFrmHandler implements CommandHandler {

	//필드
	//생성자
	
	//메서드
	//매개변수로 요청처리를 위한 HttpServletRequest객체를 갖는다.
	//매개변수로 요청처리를 위한 HttpServletResponse객체를 갖는다.
	@Override
	public String  process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("회원가입폼 보여주기 JoinFrmHandler진입~~");
		//1)파라미터값가져오기
	    //2)비즈로직수행 
		//3)Model: 비즈니스로직 수행결과
		//4)View지정: WebContent>view폴더생성>member폴더생성/joinFrm.jsp	
		//return null;
		
/*	상대경로방식	
 * //기준위치  http://localhost/op/member/joinFrm.do
		                          ../
		                          ../view/member/joinFrm.jsp  */
		return "view/member/joinFrm.jsp";
		
		
	}

}





