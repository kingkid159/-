package review.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import review.service.WriteRequest;
import review.service.WriteReviewService;

public class WriteReviewHandler implements CommandHandler{ 
	private static final String FORM_VIEW = "../view/product/review/reviewWrite.jsp";
	private WriteReviewService writeService = new WriteReviewService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Boolean> errors = new HashMap<>();
		request.setAttribute("errors",errors);
		
		//세션에서 로그인한 사용자 정보를 구한다
		User user = (User)request.getSession(false).getAttribute("AUTHUSER");
		String noVal = request.getParameter("p_no");
		System.out.println("noVal"+noVal);
		int no = Integer.parseInt(noVal);
		WriteRequest writeReq = new WriteRequest(user.getId(),
				request.getParameter("title"),
				request.getParameter("content"),
				no);
		request.setAttribute("modReq", writeReq);
		System.out.println("sssession"+user);
		//user와 HttpServeltRequest를 이용해서 WriteRequest객체 생성
		//writeReq객체가 유효한지 검사
		writeReq.validate(errors);
		//에러가 있을경우 보여줄 페이지
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		//WriteReviewService를 이용해서 게시글 등록, 등록된 게시글의 아이디 리턴받기
		int newReviewNo = writeService.write(writeReq);
		//새 글의 ID를 request의 newReviewID 속성에 저장 처리결과는 return값으로 보내주기
		request.setAttribute("newReviewNo",newReviewNo);
		
		return "../view/product/review/reviewWriteSuc.jsp";
	}
}
