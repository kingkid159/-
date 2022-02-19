package question.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import question.dao.QuestionDao;
import question.model.Question;


public class WriteQuestionService {
	private QuestionDao questionDao = new QuestionDao();
	
	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Question question = toQuestion(req);
			Question savedQuestion = questionDao.insert(conn,question);
			if(savedQuestion == null) {
				throw new RuntimeException("fail to insert Question");
			}
			conn.commit();
			return savedQuestion.getqNo();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	private Question toQuestion(WriteRequest req) {
		Date now = new Date();
		return new Question(0,req.getId(),0,req.getTitle(),req.getContent(),null,null);
	}
}
