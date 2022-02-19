package question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import question.model.Question;

public class QuestionDao {
	public Question insert(Connection conn, Question question)throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt=conn.prepareStatement("insert into question "
					+ "(q_no,id,q_title,q_content,q_regdate,q_hit,q_delete) "
					+ "values(qno.nextval,?,?,?,sysdate,?,'N')");
			pstmt.setString(1, question.getId());
			pstmt.setNString(2, question.getTitle());
			pstmt.setNString(3, question.getContent());
			pstmt.setInt(4, question.getReadCount());
			int insertedCount=pstmt.executeUpdate();
			if (insertedCount>0) {
				stmt=conn.createStatement();
				rs=stmt.executeQuery("select q_no from question where q_no = (select max(q_no) from question)");
				if(rs.next()) {
					Integer newNum=rs.getInt(1);
					return new Question(
						newNum,
						question.getId(),
						question.getPno(),
						question.getTitle(),
						question.getContent(),
						null,
						null);
				}
			}
			return null;
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public int selectCount(Connection conn)throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from question where q_delete = 'N' ");
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		
	}
	
	public List<Question> select(Connection conn, int startRow, int size)
	throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from ("+
					"SELECT ROW_NUMBER() OVER (ORDER BY q_no desc) NUM"+
					",question.* "+
					"FROM question "+
					"ORDER BY q_no desc) "+
					"WHERE q_delete = 'N' and NUM BETWEEN ? AND ?  ");
			pstmt.setInt(1, startRow+1);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Question> result = new ArrayList<>(); 
			while (rs.next()) {
				result.add(convertQuestion(rs));
			}
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			}
		}
	private Question convertQuestion(ResultSet rs) throws SQLException{
		return new Question(
				rs.getInt("q_no"),rs.getNString("id"),
				0,rs.getNString("q_title"),
				rs.getNString("q_content"),
				rs.getNString("q_delete"),rs.getDate("q_regdate"));
	}
	
	public int update(Connection conn, int no, String title, String content)throws SQLException {
		try(PreparedStatement pstmt =
				conn.prepareStatement("update Question set q_title = ?, q_content = ? "
						+ "where q_no = ? ")){
			pstmt.setNString(1, title);
			pstmt.setNString(2, content);
			pstmt.setInt(3, no);
			return pstmt.executeUpdate();
		}
	}
	public Question selectById(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try {
			pstmt=conn.prepareStatement(
					"select * from question where q_no = ?");
			pstmt.setInt(1,no);
			rs=pstmt.executeQuery();
			Question question = null;
			if(rs.next()) {
				question=convertQuestion(rs);
			}
			return question;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public int delete(Connection conn, int no) throws SQLException{
		try (
			PreparedStatement pstmt = conn.prepareStatement("update question "
					+ "set q_delete = 'Y' where q_no =? ")
			){
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}
}
