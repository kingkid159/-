package answer.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import answer.model.Answer;
import answer.service.WriteAnswerService;
import answer.service.WriteRequest;
import auth.service.User;
import mvc.command.CommandHandler;

public class WriteAnswerHandler implements CommandHandler{
	private static final String FORM_VIEW="../view/product/qna/answerWrite.jsp";
	private WriteAnswerService writeService = new WriteAnswerService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);{
				return null;
			}
		}
	}
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Boolean>errors = new HashMap<>();
		request.setAttribute("errors", errors);
		User user = (User)request.getSession().getAttribute("AUTHUSER");
		String noVal = request.getParameter("no");
		int no = Integer.parseInt(noVal);
		WriteRequest writeReq = new WriteRequest(user.getId(),request.getParameter("content"),no);
		writeReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		int newAnswerNo = writeService.write(writeReq);
		request.setAttribute("newAnswerNo", newAnswerNo);
		request.setAttribute("no", no);
		return "../view/product/qna/answerWriteSuc.jsp";
		/* return "../view/test.jsp" */
	}
}
