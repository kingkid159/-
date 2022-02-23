package qna.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import qna.service.ArticleContentNotFoundException;
import qna.service.ArticleNotFoundException;
import qna.service.QnaData;
import qna.service.ReadQnaService;

public class QnaHandler implements CommandHandler{
	private ReadQnaService qnaService = new ReadQnaService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String noVal = request.getParameter("no");
		int qnaNo = Integer.parseInt(noVal);
		try {
			QnaData qnaData = qnaService.getQna(qnaNo);
			request.setAttribute("qnaData",qnaData);
			return "../view/product/qna/readQna.jsp";
		}catch(ArticleNotFoundException | ArticleContentNotFoundException e) {
			request.getServletContext().log("no article",e);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}
	
	

