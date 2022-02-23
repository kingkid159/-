package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import review.dao.ReviewDao;
import review.model.Review;

public class DeleteReviewService {
	private ReviewDao reviewDao = new ReviewDao();
	public void delete(DeleteRequest delReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Review review = reviewDao.selectById(conn, delReq.getReviewNumber());
			if(review==null) {
				throw new ReviewNotFoundException();
			}
			if(!canDelete(delReq.getUserId(),review)) {
				throw new PermissionDeniedException();
			}
			reviewDao.delete(conn, delReq.getReviewNumber());
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
	private boolean canDelete(String delUserId, Review review) {
		System.out.println("delUserId="+delUserId);
		return review.getwriter().contentEquals(delUserId);
	}

}
