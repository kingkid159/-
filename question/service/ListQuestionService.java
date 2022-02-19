package question.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import question.dao.QuestionDao;
import question.model.Question;

public class ListQuestionService {
	private QuestionDao questionDao= new QuestionDao();
	private int size = 10;
	
	public QuestionPage getQuestionPage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = questionDao.selectCount(conn);
			List<Question> content = questionDao.select(
					conn, (pageNum - 1)*size, (pageNum - 1)*size+10);
			return new QuestionPage(total,pageNum,size,content);
			}catch(SQLException e) {
			throw new RuntimeException(e);
		}
				
	}
}
