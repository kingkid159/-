package review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import review.dao.ReviewDao;
import review.model.Review;


public class WriteReviewService {
	private ReviewDao reviewDao = new ReviewDao();
	
	//write()메서드는 WriteRequest 타입의 request파라미터를 이용해서 게시글을 등록하고, 결과로 게시글 번호 리턴한다
	public Integer write(WriteRequest request) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//트랜잭션 시작
			conn.setAutoCommit(false);
			
			//WriteRequest로 부터 Review객체 생성,  insert 쿼리를 실행해야 ID를 알 수 있으므로 47행에 number값을 null값으로 전달
			Review review = toReview(request);
			//Review의 insert메서드를 실행하고 그 결과를 savedReview에 할당
			//데이터를 삽입한 경우 savedReview는 null이 아니고 review테이블에 추가한 데이터의 주요 값을 number값으로 가진다
			Review savedReview = reviewDao.insert(conn,review);
			//savedArticl이 null이면 article테이블에 삽입한 코드가 없단 뜻이므로 익셉션을 발생시킴
			if(savedReview == null) {
				throw new RuntimeException("fail to insert Review");
			}
			//책에서는 review와 reviewContent 클래스를 를 따로 만들었지만 여기서는 review하나로 통일 했기때문에 
			//추가적으로 데이터 삽입 코드 없음
			
			//트랜젝션 커밋
			conn.commit();
			//새로 추가한 게시글 번호 리턴
			return savedReview.getNumber();
			//익셉션 발생시 트랜잭션 롤백
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

	private Review toReview(WriteRequest request) {
		// TODO Auto-generated method stub
		Date now = new Date();
		return new Review(0,request.getWriter(),0,request.getTitle(),request.getContent(),
				now,0);
	}

}
