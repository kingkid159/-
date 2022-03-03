package member.command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.DuplicateIdException;
import member.service.JoinRequest;
import member.service.JoinService;
import mvc.command.CommandHandler;

//요청url http://localhost/member/join.do
//(여기에서는)모든 컨트롤러는 반드시 CommandHandler인터페이스를 구현해야 한다.
//이 클래스는 회원가입 처리 요청 담당 컨트롤러이다 - p597
public class JoinHandler implements CommandHandler{

	//필드	P598 16라인
	private static final String FORM_VIEW="./view/member/joinFrm.jsp";
	private JoinService joinService = new JoinService();
	//생성자
	
	//메서드
	@Override
	public String process(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("회원가입 처리-JoinHandler진입");
		//2.비즈니스로직<->Service<->DAO<->DB
				//로그인폼보여주기(get방식) + 로그인처리(post방식) 
			if( request.getMethod().equals("GET") ) { //get방식요청
				return processForm(request,response);
			}else if( request.getMethod().equals("POST") ) { //post방식요청
				//회원가입처리 : 비즈니스로직<->Service<->DAO<->DB
				return processSubmit(request,response);
			}else{
				return null;
			}
	}//process
	//회원가입폼보여주기(get방식)-p598 31라인
		private String  processForm(HttpServletRequest request, HttpServletResponse response){
			System.out.println("processForm()진입");
			return FORM_VIEW;
		}//processForm()
		//회원가입처리 : 비즈니스로직<->Service<->DAO<->DB
		private String  processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception{
			System.out.println("processSumit()진입");
			//1)파라미터값가져오기
			//여기에서는 파라미터값을 가져와JoinService로 넘길 때
			//JoinRequest객체로 만들어서 넘긴다
			JoinRequest joinReq = new JoinRequest();
			joinReq.setId(request.getParameter("id"));
			joinReq.setName(request.getParameter("name"));
			joinReq.setPassword(request.getParameter("password"));
			joinReq.setPassword2(request.getParameter("password2"));
			joinReq.setBirth(request.getParameter("birth"));	
			joinReq.setGender(request.getParameter("gender"));
			joinReq.setPhone(request.getParameter("phone1")+request.getParameter("phone2")+request.getParameter("phone3"));
			joinReq.setAddress(request.getParameter("roadAddress")+request.getParameter("detailAddress"));
			joinReq.setEmail(request.getParameter("email1")+request.getParameter("email2"));
			joinReq.setEmail1(request.getParameter("email1"));
//			System.out.println(request.getParameter("phone1"));
//			System.out.println(request.getParameter("phone2"));
//			System.out.println(request.getParameter("phone3"));
//			System.out.println(joinReq.getPhone());
//			System.out.println(request.getParameter("email1"));
//			System.out.println(request.getParameter("email2"));
//			System.out.println(joinReq.getEmail());
//			System.out.println(request.getParameter("postcode"));
//			System.out.println(request.getParameter("roadAddress"));
//			System.out.println(request.getParameter("detailAddress"));
//			System.out.println(joinReq.getAddress());
//			System.out.println(request.getParameter("birth"));
			
			//유효성검사 추가
			Map<String, Boolean> errors= new HashMap<>();
	         request.setAttribute("errors",errors);
	         joinReq.validate(errors);
	         if(!errors.isEmpty()) {
	            return FORM_VIEW;
	         }
	            int n = joinService.join(joinReq);
	            request.setAttribute("n", n);
	            System.out.println(n);
	            if(n==0) {
	               
	                  errors.put("duplicateId",Boolean.TRUE);
	                  return FORM_VIEW;
	               
	               }else {
	                  return "main.jsp";
	               }
			//p598 52라인
			//2)비즈로직수행 컨트롤러<->JoinService<->MemberDAO<->DB
			//joinService.join(joinReq);
		//	int n = joinService.join(joinReq);
			//INSERT INTO member(mid,mname,pwd,regdate,isshow) 
			//VALUES(?,?,?,now(),'Y');
			//3)Model: 비즈니스로직 수행결과
		//	request.setAttribute("n", n);
			//4)View지정:
			//	성공시 view/member/joinSuccess.jsp
			//	실패시 http://localhost/컨텍스트패스/member/joinFrm.do
		//	return "header.jsp";
		}//processSubmit
}//class












