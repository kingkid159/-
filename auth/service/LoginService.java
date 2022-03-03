package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.model.MemberDTO;

//p605
//로그인처리를 위한 서비스 클래스이다

//컨트롤러<->Service<->DAO<->DB
//서비스클래스는 컨트롤러의 기능을 분담하는 클래스로서
//컨트롤러 클래스에서 필요기능이 호출되고,
//db연동을 위한 DAO클래스를 연결하는 역할
public class LoginService {
		//필드	p605 12라인
		private MemberDAO memberDAO = new MemberDAO();
		//생성자
		//메서드
		//로그인처리
		//첫 번째 파라미터 id는 로그인폼에서 유저가 입력한 id
		//두 번째 파라미터 password는 로그인폼에서 유저가 입력한 비번
		//컨트롤러를 통해서 전달받았다
		public User  login(String id, String password) {
			System.out.println("LoginService의 login(id)="+id);
			//파라미터 id, password에는 로그인폼에서 입력한 유저의 id와 암호가 담아진다
			//db연동을 위한 DAO클래스의 메서드호출
			//p605 16라인
			MemberDTO memberDTO;
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				memberDTO = memberDAO.selectById(conn,id);
				System.out.println("LoginService의 login()후"+memberDTO);
				if (memberDTO==null) {	//user가 입력한 id를 가진 회원이 존재하지 않는다면
					throw new LoginFailException();//로그인 실패예외 처리
				}
				if(!memberDTO.matchPassword(password)) {//user가 입력한 비번이 기존회원의 비번과 일치하지 않는다면
					throw new LoginFailException();	//로그인 실패예외 처리
				}
				
				//매개변수가 있는 생성자를 이용하여 객체생성
				return new User(memberDTO.getId(),memberDTO.getName(),memberDTO.getPassword(),memberDTO.getBirth(),memberDTO.getGender(),memberDTO.getEmail(),memberDTO.getPhone(),memberDTO.getAddress());
			} catch (SQLException e) {
				e.printStackTrace();	//콘솔 발생한 파일과 위치,원인
				throw new RuntimeException(e);
			}
		}//login()
}//class
