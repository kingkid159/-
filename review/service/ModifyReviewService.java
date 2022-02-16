package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import review.dao.ReviewDao;
import review.model.Review;

public class ModifyReviewService {
	private ReviewDao reviewDao = new ReviewDao();
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Review review = reviewDao.selectById(conn,modReq.getReviewNumber());
			if(review==null) {
				throw new ReviewNotFoundException();
			}
			if(!canModify(modReq.getUserId(),review)){
				throw new PermissionDeniedException();
			}
			reviewDao.update(conn, modReq.getReviewNumber(), modReq.getTitle(), modReq.getContent());
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
	private boolean canModify(String modfyingUserId, Review review) {
		return review.getWriter().getId().contentEquals(modfyingUserId);
	}
}
