package review.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import review.service.ListReviewService;
import review.service.ReviewPage;

public class ListReviewHandler implements CommandHandler{
	private ListReviewService listService = new ListReviewService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String pageNoVal = request.getParameter("pageNo");
		int pageNo =1;
		if(pageNoVal != null) {
			pageNo =Integer.parseInt(pageNoVal);
			
		}
		ReviewPage reviewPage = listService.getReviewPage(pageNo);
		request.setAttribute("reviewPage",reviewPage);
		return "../view/product/reviewList.jsp";
		
	}

	@Override
	public String toString() {
		return "ListReviewHandler [listService=" + listService + "]";
	}
	
}
