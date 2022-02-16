package review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import review.dao.ReviewDao;
import review.model.Review;

public class ListReviewService {
	private ReviewDao reviewDao = new ReviewDao();
	private int size = 10;
	
	public ReviewPage getReviewPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()){
			//전체 게시글의 개수를 구한다.
			System.out.println("pageNum="+pageNum);
			int total = reviewDao.selectCount(conn);
			/*
			 * pageNum에 해당하는 게시글 목록을 구한다. reviewDao.select()메서드의 두번째 파라미터는 조회할 레코드의 시작행이다
			 * 시작행은 0번 기준으로 (pageNum-1)*size를 시작행 번호로 사용한다. 여기서 pageNum은 ReviewDao에서 startRow로 갈 예정이고
			 * size는 끝 부분을 나타낼 예정이다 그러니 한 화면에 10개에 게시물을 나타내기 위해서 startRow로 넘어갈 식에 10을 더해준다.
			 */
			List<Review> content = reviewDao.select(
					conn, (pageNum - 1)*size,  (pageNum - 1)*size+10);
			
			return new ReviewPage(total, pageNum, size, content);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return "ListReviewService [reviewDao=" + reviewDao + ", size=" + size + "]";
	}
	
}
