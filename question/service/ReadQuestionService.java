package question.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import question.dao.QuestionDao;
import question.model.Question;

public class ReadQuestionService {
	private QuestionDao	questionDao = new QuestionDao();
	
	public QuestionData getQusetion(int questionNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Question question = questionDao.selectById(conn, questionNum);
			if(question.getqNo()==null) {
				throw new QuestionNotFoundException();
			}
			Question content = questionDao.selectById(conn, questionNum);
			if(content==null) {
				throw new QuestionContentNotFoundException();
			}
			return new QuestionData(question,content);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
