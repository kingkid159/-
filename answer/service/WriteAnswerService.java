package answer.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import answer.dao.AnswerDao;
import answer.model.Answer;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;



public class WriteAnswerService {
	private AnswerDao answerDao = new AnswerDao();
	
	public Integer write(WriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Answer answer = toAnswer(req);
			Answer savedAnswer = answerDao.insert(conn,answer);
			if(savedAnswer == null) {
				throw new RuntimeException("fail to insert Answer");
			}
			conn.commit();
			return savedAnswer.getNo();
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
	private Answer toAnswer(WriteRequest req) {
		Date now = new Date();
		return new Answer(req.getNo(),req.getId(),req.getContent(),null,null);
	}
}
