package auth.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.LoginFailException;
import auth.service.LoginService;
import auth.service.User;
import member.model.MemberDTO;
import mvc.command.CommandHandler;

//*요청url : http://localhost/op/login.do 
//로그인폼보여주기(get방식) + 로그인처리(post방식)  컨트롤러 P606
public class LoginHandler implements CommandHandler {

	//필드-  [접근제한자] [속성] 데이터타입 필드명[=초기값];  클래스명.필드명    클래스명.메서드()
	private static final String FORM_VIEW = "view/member/login.jsp";
	
	//생성자- [접근제한자] [속성] 클래스명(매개변수리스트){}
	//public LoginHandler(){} //기본생성자   LoginHandler ref=new LoginHandler(); ref.필드명  ref.메서드()
	//public LoginHandler(매개변수,매개변수){}
	
	//메서드- [접근제한자] [속성] 리턴유형  메서드명(매개변수리스트){}
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LoginHandler진입.요청방식-"+request.getMethod());
		
		//2.비즈니스로직<->Service<->DAO<->DB
		//로그인폼보여주기(get방식) + 로그인처리(post방식) 
		if( request.getMethod().equals("GET") ) { //get방식요청
			return processForm(request,response);
		}else if( request.getMethod().equals("POST") ) { //post방식요청
			//로그인처리 : 비즈니스로직<->Service<->DAO<->DB
			return processSubmit(request,response);
		}else{
			return null;
		}
		
	}//process
	

	//로그인폼보여주기(get방식)-p607 32라인
	private String  processForm(HttpServletRequest request, HttpServletResponse response){
		System.out.println("processForm()진입");
		return FORM_VIEW;
	}//processForm()
	
	//로그인처리(post방식)-p607 36라인
	private String  processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("processSumit()진입");
		
		//1.파라미터받기-p607 38라인
		
		String id = trim(request.getParameter("id"));	//회원id
		String password = trim(request.getParameter("password"));	//회원비번
		System.out.printf("id=%s, password=%s\n",id,password);//콘솔 확인용
		
		//p608 41~51
		//id와 비번 필수입력체크-> 로그인폼으로 보내기
		//컬렉션프레임워크는 다양한 타입의 여러개의 값을 하나의 변수에 저장,관리하기 쉽게하는 인터페이스와 클래스의 모음
		//데이터를 꺼낼 때 형변환없이 한가지 타입으로 저장하기 쉽게끔
		//generic(제너릭,지네릭)을 이용하여 타입을 제한한다
		//클래스타입<데이터타입>  참조변수명 = new 클래스명<데이터타입>();
		/*여기에서는 Map이므로 key와 value가 동시에 반드시 묶어서 지정해야하므로
		 Map<키의 타입,값의 타입> errors = new HashMap<키의 타입,값의 타입>();*/
		Map<String,Boolean> errors = new HashMap<String,Boolean>(); //에러정보를 저장하기위한 변수선언-Map(키명,에러정보값)방식으로 저장
		request.setAttribute("errors",errors);
		// isEmpty() : Returns true if, and only if, length() is 0.
		if(id==null || id.isEmpty()) {
			System.out.println("id비었음");
			//참조변수명.put(String 키명,Object 값) : Map에 데이터를 저장
			errors.put("id",Boolean.TRUE);
		}
		if(password==null || password.isEmpty()) {
			System.out.println("비번비었음");
			errors.put("password",Boolean.TRUE);
		}
		System.out.println("에러정보 errors.get('id')="+errors.get("id"));
		System.out.println("에러정보 errors.get('password')="+errors.get("password"));
		
		if( !errors.isEmpty() ) { //errors가 비어있지않다면->id와 비번이 없다면
			return FORM_VIEW;//로그인폼으로 보내기
		}
		
		//2.로그인처리(p607 53라인): 비즈니스로직<->Service<->DAO<->DB
		LoginService loginService = new LoginService();
		//user id와 비번을 서비스클래스의 로그인처리메서드 호출시 제공
		try {
			System.out.println("로그인컨트롤러에서 loginService.login()호출전");
			User user = loginService.login(id,password);
			System.out.println("로그인컨트롤러에서 loginService.login()호출후");
			
			//3.Model
			/*session.setAttribute(String name,Object value):세션에 특정 정보를 저장.설정
			  session.setAttribute(속성명,속성값)
			  SELECT mno,mid,mname,pwd,regdate,isshow
			  예)session.setAttribute("MNO",2) //2 회원번호 mno
			   예)session.setAttribute("MID", "hid"); //hid 회원id mid
			   예)session.setAttribute("MNAME", "홍길동"); //홍길동 회원명 mname   */
			
			//request참조변수명.getSession()를 이용하여   HttpSession session얻기
			HttpSession session = request.getSession();
			session.setAttribute("AUTHUSER",user);	//회원정보 P607 55라인
			//4.View		
			response.sendRedirect(request.getContextPath()+"/main.jsp");
			System.out.println(user.getAddress());
			return null;

		} catch (LoginFailException e) {
			errors.put("idOrPwNotMatch",Boolean.TRUE);
			return FORM_VIEW;	//로그인실패가 되면 로그인폼view를 보여줘라
		}
	}//processSumit()
	
	//문자열 좌우의 공백을 제거-p607 64라인
	private String trim(String str) {
		//매개변수로 넘어온 문자열이 null이면 null을 리턴
		//매개변수로 넘오온 문자열이 null아니면 문자열 좌우의 공백을 제거하여 리턴
		//			(조건)? true일경우:false경우
		return (str==null)? null : str.trim();
	}
	
}//class


















