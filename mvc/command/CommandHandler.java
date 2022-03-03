package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//요청처리를 위한 컨트롤러의 요청함수명을 동일하게 강제하기 위한 인터페이스
//따라서 (여기에서는)모든 컨트롤러는 반드시 CommandHandler인터페이스를 구현해야 한다.
public interface CommandHandler {
	
	//컨트롤러는 user의 요청에 따라 비즈니스로직을 수행하고
	//응답처리를 해야하므로
	//매개변수로 요청처리를 위한 HttpServletRequest객체를 갖는다.
	//매개변수로 요청처리를 위한 HttpServletResponse객체를 갖는다.
	public String process(HttpServletRequest request, 
			 HttpServletResponse response) throws Exception;
}







