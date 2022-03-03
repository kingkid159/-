package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.model.MemberDTO;

public class ChangePasswordService {

	
	private MemberDAO memberDAO = new MemberDAO();
	
	public void changePassword(String userId, String curPwd, String newPwd) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			MemberDTO memberDTO = memberDAO.selectById(conn,  userId);
			if (memberDTO == null) {
				throw new MemberNotFoundException();
			
			}
			
			if (!memberDTO.matchPassword(curPwd)) {
				throw new InvalidPasswordException();
			}
			
			memberDTO.setPassword(newPwd);
			memberDAO.update(conn,memberDTO);
			conn.commit();
	
			} catch (SQLException e) {
				JdbcUtil.rollback(conn);
				throw new RuntimeException(e);
			
			} finally {
				JdbcUtil.close(conn);
			}
				
	}
}
