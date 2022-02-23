package answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import answer.model.Answer;
import jdbc.JdbcUtil;


public class AnswerDao {

	public Answer insert(Connection conn, Answer answer)throws SQLException{
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		Statement stmt = null;
		try {
			
			pstmt=conn.prepareStatement("insert into answer "
					+ "(a_no,id,a_contnent,a_regdate,a_delete) "
					+ "values(?,?,?,sysdate,'N') ");
			pstmt.setString(2,answer.getId());
			pstmt.setString(3,answer.getContent());
			pstmt.setInt(1, answer.getNo());
			pstmt.executeUpdate();
			return new Answer(answer.getNo(), answer.getId()
					, answer.getContent(),null,null);
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	public int selectCount(Connection conn)throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from product pro "
					+ "join question ques on "
					+ "pro.p_no=ques.p_no join answer "
					+ "ans on ans.a_no=ques.q_no where a_delete = 'N' ");
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		
	}
	public List<Answer> select(Connection conn, int startRow, int size, int ano)
			throws SQLException{
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					pstmt = conn.prepareStatement("select * from ("+
							"SELECT ROW_NUMBER() OVER (ORDER BY a_no desc) NUM "+
							",answer1.*  "+
							"FROM (select pro.p_no as p_no,a_no,ans.id as id ,a_contnent,a_delete,a_regdate  "
							+ "from product pro join question ques on pro.p_no=ques.p_no  "+
							"join answer ans on ans.a_no=ques.q_no "
							+ "where a_delete='N') answer1 "
							+ "where a_delete ='N' and a_no=? "
							+ "ORDER BY a_no desc) "+
							"WHERE a_no=? and a_delete = 'N' and NUM BETWEEN ? AND ?");
					pstmt.setInt(1, ano);
					pstmt.setInt(2, ano);
					pstmt.setInt(3, startRow+1);
					pstmt.setInt(4, size);
					System.out.println("size="+size);
					rs = pstmt.executeQuery();
					List<Answer> result = new ArrayList<>(); 
					System.out.println("a00000");
					while (rs.next()) {
						System.out.println("a1111111");
						result.add(convertAnswer(rs));
					}
					System.out.println("result"+result.size());
					return result;
				}finally {
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
					}
				}
	private Answer convertAnswer(ResultSet rs) throws SQLException{
		return new Answer(
				rs.getInt("a_no"), rs.getString("id"), 
				rs.getString("a_contnent"),rs.getDate("a_regdate"),
				rs.getString("a_delete"));
	}
}
