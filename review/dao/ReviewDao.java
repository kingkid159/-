package review.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;

import java.sql.PreparedStatement;
import review.model.Review;

public class ReviewDao {
	public Review insert(Connection conn, Review review) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt= null;
		ResultSet rs=null;
		try {
			//인서트 쿼리문을 이용해서 review테이블에  데이터를 삽입한다. r_no는 자동증가값으로 변경예정이라 나중에 값을 뺄 예정
			pstmt=conn.prepareStatement("insert into review "
					+"(r_no,id,p_no,r_title,r_content,r_regdate,r_hit) "
					+"values(?,?,?,?,?,?,?)");
			pstmt.setInt(1, review.getNumber());
			pstmt.setString(2, review.getWriter().getName());
			pstmt.setInt(3, review.getPno());	//Pno는 product 테이블 완성후 변경 예정
			pstmt.setString(4,review.getTitle());
			pstmt.setString(5, review.getContent());
			pstmt.setTimestamp(6,toTimestamp(review.getRegDate()));
			int insertedCount = pstmt.executeUpdate();
			
			
			if (insertedCount>0){
				stmt=conn.createStatement();
				//r_no 자동증가값으로 변경 후 사용예정인 r_no입력 쿼리 
				rs=stmt.executeQuery("select last_insert_id() from article");
				//신규 게시글의 r_no 값 구하기
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
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
}
