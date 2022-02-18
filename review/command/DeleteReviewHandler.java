package review.command;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import review.service.DeleteRequest;
import review.service.DeleteReviewService;
import review.service.PermissionDeniedException;
import review.service.ReadReviewService;
import review.service.ReviewData;
import review.service.ReviewNotFoundException;

public class DeleteReviewHandler implements CommandHandler{
	private static final String FORM_VIEW="../view/product/reviewDelete.jsp";
	
	private DeleteReviewService deleteService = new DeleteReviewService();
	private ReadReviewService readService = new ReadReviewService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
		}else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String processForm(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			String noVal = request.getParameter("no");
			int no =Integer.parseInt(noVal);
			ReviewData reviewData = readService.getReview(no, false);
			User authUser = (User) request.getSession().getAttribute("AUTHUSER");
			if(!canDel(authUser,reviewData)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			DeleteRequest delReq = new DeleteRequest(authUser.getId(),no,
					reviewData.getReview().getTitle(),
					reviewData.getContent());
			request.setAttribute("delReq",delReq);
			return FORM_VIEW;
		}catch(ReviewNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private boolean canDel(User authUser,ReviewData reviewData) {
		String writerId = reviewData.getReview().getWriter().getId();
		return authUser.getId().equals(writerId);
	}
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			return "../view/product/deleteSuccess.jsp";
		}catch(ReviewNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch(PermissionDeniedException e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
	
}
