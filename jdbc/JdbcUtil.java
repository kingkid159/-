package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//객체반납, 트랜잭션 rollback기능 제공 클래스
public class JdbcUtil {

	//ResultSet객체 반납
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
			}
		}
	}
	
	//Statement객체반납
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
			}
		}
	}
	
	//Connection객체반납
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
			}
		}
	}

	//
	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();	//트랜잭션의 rollback
			} catch (SQLException ex) {
			}
		}
	}
}
