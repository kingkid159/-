package review.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import review.service.ModifyRequest;
import review.service.ModifyReviewService;
import review.service.PermissionDeniedException;
import review.service.ReadReviewService;
import review.service.ReviewData;
import review.service.ReviewNotFoundException;

public class ModifyReviewHandler implements CommandHandler{
	private static final String FORM_VIEW ="../view/product/review/reviewModify.jsp";
	
	private ReadReviewService readService = new ReadReviewService();
	private ModifyReviewService modifyService = new ModifyReviewService();
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
	
	private String processForm(HttpServletRequest request,HttpServletResponse response)throws IOException{
		try {
			String noVal = request.getParameter("no");
			//System.out.println("noVal="+noVal);
			int no = Integer.parseInt(noVal);
			ReviewData reviewData = readService.getReview(no, false);
			User authUser =(User) request.getSession().getAttribute("AUTHUSER");
			if(!canModify(authUser, reviewData)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			ModifyRequest modReq = new ModifyRequest(authUser.getId(),no,
					reviewData.getReview().getTitle(),
					reviewData.getContent());
			request.setAttribute("modReq",modReq);
			return FORM_VIEW;
		}catch(ReviewNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private boolean canModify(User authUser,ReviewData reviewData) {
		String writerId = reviewData.getReview().getwriter();
		return authUser.getId().equals(writerId);
	}
	private String processSubmit(HttpServletRequest request, HttpServletResponse response)throws Exception{
		User authUser = (User)request.getSession().getAttribute("AUTHUSER");
		String noVal = request.getParameter("no");
		int no = Integer.parseInt(noVal);
		ModifyRequest modReq = new ModifyRequest(authUser.getId(),no,
				request.getParameter("title"),
				request.getParameter("content"));
		System.out.println("modReq="+modReq);
		request.setAttribute("modReq", modReq);
		
		Map<String,Boolean>errors = new HashMap<>();
		request.setAttribute("errors", errors);
		modReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return "../view/product/review/modifySuccess.jsp";
		}catch(ReviewNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}catch(PermissionDeniedException e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
	
}
