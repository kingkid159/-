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
		String strPno = request.getParameter("p_no");
		int pno = Integer.parseInt(strPno);
		System.out.println("pnoL="+pno);
		int pageNo =1;
		if(pageNoVal != null) {
			pageNo =Integer.parseInt(pageNoVal);
			
		}
		ReviewPage reviewPage = listService.getReviewPage(pageNo,pno);
		
		request.setAttribute("reviewPage",reviewPage);
		return "../view/product/review/reviewList.jsp";
		
	}

	@Override
	public String toString() {
		return "ListReviewHandler [listService=" + listService + "]";
	}
	
}
