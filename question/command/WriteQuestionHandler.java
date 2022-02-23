package question.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import question.service.WriteQuestionService;
import question.service.WriteRequest;

public class WriteQuestionHandler implements CommandHandler{
	private static final String FORM_VIEW = "../view/product/qna/questionWrite.jsp";
	private WriteQuestionService writeService =new WriteQuestionService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String processForm(HttpServletRequest request,HttpServletResponse response) {
		return FORM_VIEW;
	}
	private String processSubmit(HttpServletRequest request, HttpServletResponse response ) {
		Map<String,Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		User user = (User)request.getSession(false).getAttribute("AUTHUSER");
		String noVal = request.getParameter("p_no");
		System.out.println("noVal"+noVal);
		int no = Integer.parseInt(noVal);
		WriteRequest writeReq = new WriteRequest(user.getId(),
				request.getParameter("title"),
				request.getParameter("content"),
				no);
		writeReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		int newQuestionNo = writeService.write(writeReq);
		request.setAttribute("newReviewno",newQuestionNo);
		return "../view/product/qna/questionWriteSuc.jsp";
	}
}
