package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import review.dao.ReviewDao;
import review.model.Review;

public class ReadReviewService {
	private ReviewDao reviewDao = new ReviewDao();
	
	public ReviewData getReview(int reviewNum, boolean increaseReadCount) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Review review = reviewDao.selectById(conn, reviewNum);
			if(review.getNumber() == null) {
				throw new ReviewNotFoundException();
			}
			Review content=reviewDao.selectById(conn, reviewNum);
			if(content==null) {
				throw new ReviewContentNotFoundException();
			}
			if(increaseReadCount) {
				reviewDao.increaseReadCount(conn,reviewNum);
			}
			return new ReviewData(review,content);
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
