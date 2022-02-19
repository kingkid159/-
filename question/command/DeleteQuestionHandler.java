package question.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import question.service.DeleteQuestionService;
import question.service.DeleteRequest;
import question.service.PermissionDeniedException;
import question.service.QuestionData;
import question.service.QuestionNotFoundException;
import question.service.ReadQuestionService;

public class DeleteQuestionHandler implements CommandHandler{
	private static final String FORM_VIEW="../view/product/qna/questionDelete.jsp";
	private DeleteQuestionService deleteService = new DeleteQuestionService();
	private ReadQuestionService readService = new ReadQuestionService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if(request.getMethod().equalsIgnoreCase("GET")){
			return processForm(request,response);
		}else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String processForm(HttpServletRequest request,HttpServletResponse response)throws Exception{
		try {
			String noVal = request.getParameter("no");
			int no = Integer.parseInt(noVal);
			QuestionData questionData = readService.getQusetion(no);
			User authUser = (User) request.getSession().getAttribute("AUTHUSER");
			if(!canDel(authUser,questionData)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			DeleteRequest delReq = new DeleteRequest(authUser.getId(),no,
					questionData.getQuestion().getTitle(),
					questionData.getContent());
			request.setAttribute("delReq",delReq);
			return FORM_VIEW;
		}catch(QuestionNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	private boolean canDel(User authUser,QuestionData questionData) {
		String writerId = questionData.getQuestion().getId();
		return authUser.getId().contentEquals(writerId);
	}
	private String processSubmit(HttpServletRequest request, HttpServletResponse response)throws Exception {
		User authUser = (User)request.getSession().getAttribute("AUTHUSER");
		String noVal = request.getParameter("no");
		int no = Integer.parseInt(noVal);
		DeleteRequest delReq = new DeleteRequest(authUser.getId(),no,
				request.getParameter("title"),
				request.getParameter("content"));
		request.setAttribute("delReq",delReq);
		
		Map<String,Boolean>errors = new HashMap<>();
		request.setAttribute("errors", errors);
		delReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			deleteService.delete(delReq);
			return "../view/product/qna/deleteSuccess.jsp";
		}catch(QuestionNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch(PermissionDeniedException e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
	
}
