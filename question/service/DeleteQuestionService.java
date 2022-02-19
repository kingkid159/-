package question.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import question.dao.QuestionDao;
import question.model.Question;

public class DeleteQuestionService {
	private QuestionDao questionDao = new QuestionDao();
	public void delete (DeleteRequest delReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Question question = questionDao.selectById(conn,delReq.getQuestionNumber());
			if(question==null) {
				throw new QuestionNotFoundException();
			}
			if(!canDel(delReq.getUserId(),question)) {
				throw new PermissionDeniedException();
			}
			questionDao.delete(conn, delReq.getQuestionNumber());
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	private boolean canDel(String delUserId,Question question) {
		return question.getId().contentEquals(delUserId);
	}
}
