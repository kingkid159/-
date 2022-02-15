package review.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;

import java.sql.PreparedStatement;
import review.model.Review;
import review.model.Writer;

public class ReviewDao {
	public Review insert(Connection conn, Review review) throws SQLException{
		System.out.println(review);
		
		PreparedStatement pstmt = null;
		Statement stmt= null;
		ResultSet rs=null;
		try {
			//인서트 쿼리문을 이용해서 review테이블에  데이터를 삽입한다. r_no는 자동증가값으로 변경예정이라 나중에 값을 뺄 예정
			
			pstmt=conn.prepareStatement("insert into review "
					+"(r_no,id,p_no,r_title,r_content,r_regdate,r_hit) "
					+"values(review_no.nextval,?,review_no.nextval,?,?,?,?)");
			System.out.println("review"+review.getNumber());
			pstmt.setString(1, review.getWriter().getId());
			pstmt.setString(2,review.getTitle());
			pstmt.setString(3, review.getContent());
			pstmt.setTimestamp(4,toTimestamp(review.getRegDate()));
			pstmt.setInt(5, review.getReadCount());
			int insertedCount = pstmt.executeUpdate();
			System.out.println("insertedCount"+insertedCount);
			if (insertedCount>0){
				stmt=conn.createStatement();
				//r_no 자동증가값으로 변경 후 사용예정인 r_no입력 쿼리 
				//nvl null값에 밸류를 주는것
				rs=stmt.executeQuery("select r_no from review where r_no=(select max(r_no) from review)");
				//신규 게시글의 r_no 값 구하기
				System.out.println("rs="+rs);
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					System.out.println("newNum"+newNum);
					//신규 게시물을 review에 리턴
					return new Review(newNum,
							review.getWriter(),
							review.getPno(),
							review.getTitle(),
							review.getContent(),
							review.getRegDate(),
							0);
				}
			}
		
			
			return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	/* review 테이블의 전체 레코드 수를 리턴 */
	public int selectCount(Connection conn)throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from review ");
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		
	}
	
	public List<Review> select(Connection conn, int startRow, int size)
	throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			/*
			 * desc로 게시글 번호 역순처리후 limit가 4, 2이면 5번째 레코드부터 2개의 레코드인 5,4를 읽어온다
			 */
			pstmt = conn.prepareStatement("select * from ( select"+
					" * from review "+
					"order by r_no desc) where rownum <=?");
			/* pstmt.setInt(1, startRow); */
			pstmt.setInt(1, size);
			rs = pstmt.executeQuery();
			List<Review> result = new ArrayList<>(); 
			while (rs.next()) {
				result.add(convertReview(rs));
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			}
		
		}
		
	private Review convertReview(ResultSet rs) throws SQLException{
		return new Review(rs.getInt("r_no"),
				new Writer(
						rs.getNString("id")),
						/*,rs.getNString("name"))*/
				null, rs.getNString("r_title"),
				rs.getNString("r_content"), toDate(rs.getTimestamp("r_regDate")),
				rs.getInt("r_hit"));
		
	}
	private Date toDate(Timestamp timestamp) {
		return new Date (timestamp.getTime());
	}
	
}
