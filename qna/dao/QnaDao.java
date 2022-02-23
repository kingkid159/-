package qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import jdbc.JdbcUtil;
import qna.model.QnaModel;

public class QnaDao {
	/*public List<QnaModel> select(Connection conn,QnaModel qna)throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt=conn.prepareStatement("select * from ( "
					+ "SELECT * "
					+ "FROM (select ques.q_no as q_no,a_no,ans.id as aid,"
					+ "ques.id as qid, q_title,q_content,a_contnent,a_delete,"
					+ "a_regdate,q_regdate  "
					+ "from product pro join question ques on pro.p_no=ques.p_no "
					+ "join answer ans on ans.a_no=ques.q_no "
					+ "where a_delete='N')) "
					+ "WHERE a_no=? and a_delete = 'N' ");
			pstmt.setInt(1, qna.getQno());
			rs=pstmt.executeQuery();
			List<QnaModel> result = new ArrayList<>(); 
			System.out.println("a00000");
			while (rs.next()) {
				System.out.println("a1111111");
				result.add(convertQna(rs));
			}
			System.out.println("result"+result.size());
			return result;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			}
		}
	private QnaModel convertQna(ResultSet rs) throws SQLException{
	return new QnaModel(
			rs.getInt("q_no"),rs.getInt("a_no"),
			rs.getNString("q_title"),rs.getNString("q_content"),
			rs.getNString("a_content"),rs.getNString("qid"),
			rs.getNString("aid"),rs.getDate("q_regdate"),
			rs.getDate("a_regdate"),rs.getNString("aDelete"));
	}*/
	public QnaModel selecById(Connection conn , int no)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from ( "
					+ "SELECT * "
					+ "FROM (select ques.q_no as q_no,a_no,ans.id as aid,"
					+ "ques.id as qid, q_title,q_content,a_contnent,a_delete,"
					+ "a_regdate,q_regdate  "
					+ "from product pro join question ques on pro.p_no=ques.p_no "
					+ "join answer ans on ans.a_no=ques.q_no "
					+ "where a_delete='N')) "
					+ "WHERE a_no=? and a_delete = 'N' ");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			QnaModel qna = null;
			if (rs.next()) {
				qna = new QnaModel(
						rs.getInt("q_no"),rs.getInt("a_no"),
						rs.getNString("q_title"),rs.getNString("q_content"),
						rs.getNString("a_contnent"),rs.getNString("qid"),
						rs.getNString("aid"),rs.getDate("q_regdate"),
						rs.getDate("a_regdate"),rs.getNString("a_delete"));
			}return qna;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
}
