package question.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import question.dao.QuestionDao;
import question.model.Question;

public class ModifyQuestionService {
	private QuestionDao questionDao = new QuestionDao();
	public void modify (ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Question question = questionDao.selectById(conn,modReq.getQuestionNumber());
			if(question==null) {
				throw new QuestionNotFoundException();
			}
			if(!canModify(modReq.getUserId(),question)) {
				throw new PermissionDeniedException();
			}
			questionDao.update(conn, modReq.getQuestionNumber(), modReq.getTitle(),modReq.getContent());
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
	private boolean canModify(String modfyingUserId,Question question) {
		return question.getId().contentEquals(modfyingUserId);
	}
}
