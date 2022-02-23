package question.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import answer.service.AnswerPage;
import answer.service.ListAnswerService;
import mvc.command.CommandHandler;
import question.service.ListQuestionService;
import question.service.QuestionPage;

public class ListQuestionHandler implements CommandHandler{
	private ListQuestionService listService = new ListQuestionService();
	private ListAnswerService aListService = new ListAnswerService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String pageNoVal = request.getParameter("pageNo");
		int pageNo =1;
		String strPno = request.getParameter("p_no");
		int pno = Integer.parseInt(strPno);
		if(pageNoVal != null) {
			pageNo =Integer.parseInt(pageNoVal);
	}
		QuestionPage questionPage = listService.getQuestionPage(pageNo,pno);
		AnswerPage answerPage = aListService.getAnswerPage(pageNo, pno);
		
		Map<String, AnswerPage> answerPageMap = new HashMap<String, AnswerPage>();
		answerPageMap.put("ansPage",answerPage);
		request.setAttribute("answerPage",answerPage);
		request.setAttribute("questionPage",questionPage);
		request.setAttribute("ansPage",answerPageMap);
		return "../view/product/qna/questionList.jsp";
	}
	
}
