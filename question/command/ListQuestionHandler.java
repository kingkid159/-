package question.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import question.service.ListQuestionService;
import question.service.QuestionPage;

public class ListQuestionHandler implements CommandHandler{
	private ListQuestionService listService = new ListQuestionService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String pageNoVal = request.getParameter("pageNo");
		int pageNo =1;
		if(pageNoVal != null) {
			pageNo =Integer.parseInt(pageNoVal);
	}
		QuestionPage questionPage = listService.getQuestionPage(pageNo);
		request.setAttribute("questionPage",questionPage);
		return "../view/product/qna/questionList.jsp";
	}
	
}
