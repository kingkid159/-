package answer.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import answer.service.AnswerPage;
import answer.service.ListAnswerService;
import mvc.command.CommandHandler;


public class ListAnswerHandler implements CommandHandler{
	private ListAnswerService listService = new ListAnswerService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String pageNoVal = request.getParameter("pageNo");
		int pageNo =1;
		String strPno = request.getParameter("no");
		int pno = Integer.parseInt(strPno);
		if(pageNoVal != null) {
			pageNo =Integer.parseInt(pageNoVal);
	}
		AnswerPage answerPage = listService.getAnswerPage(pageNo,pno);
		request.setAttribute("answerPage",answerPage);
		return "../view/product/qna/questionList.jsp";
	}
}
