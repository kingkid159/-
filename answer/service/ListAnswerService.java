package answer.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import answer.dao.AnswerDao;
import answer.model.Answer;
import jdbc.connection.ConnectionProvider;


public class ListAnswerService {
	private AnswerDao answerDao= new AnswerDao();
	private int size = 10;
	
	public AnswerPage getAnswerPage(int pageNum, int pno) {
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = answerDao.selectCount(conn);
			List<Answer> content = answerDao.select(
					conn, (pageNum - 1)*size, (pageNum - 1)*size+10,pno);
			System.out.println("content??"+content);
			
			return new AnswerPage(total,pageNum,size,content);
			}catch(SQLException e) {
			throw new RuntimeException(e);
		}
				
	}
}
