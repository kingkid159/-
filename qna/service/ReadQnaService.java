package qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import qna.dao.QnaDao;
import qna.model.QnaModel;

public class ReadQnaService {
	private QnaDao qnaDao = new QnaDao();
	public QnaData getQna(int qnaNo) {
		try(Connection conn = ConnectionProvider.getConnection()){
			QnaModel qContent = qnaDao.selecById(conn, qnaNo);
			if(qContent == null) {
				throw new ArticleNotFoundException();
			}
			QnaModel qTitle = qnaDao.selecById(conn, qnaNo);
			if (qTitle == null) {
				throw new ArticleContentNotFoundException();
			}
			return new QnaData(qContent,qTitle);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
