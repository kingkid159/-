package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.model.MemberDTO;


//p596
//(회원가입 요청컨트롤러에 의해서 호출되어지는) 서비스클래스이다
//컨트롤러 <->서비스<->DAO<->DB
public class JoinService {
	//필드
	private MemberDAO memberDAO= new MemberDAO();
	
	//생성자
		
	//메서드	-p596 16라인
	/*JoinHandler 컨트롤러에서 아래와 같이 넘겨받았다
	 * 			joinReq.setId(request.getParameter("id"));
			joinReq.setName(request.getParameter("name"));
			joinReq.setPassword(request.getParameter("password"));
			joinReq.setConfirmPassword(request.getParameter("confirmPassword"));
			*/
	public int join(JoinRequest joinReq) {
		Connection conn = null;
		int n=0;
		try {
			conn = ConnectionProvider.getConnection();	//p597 19라인
			//프로그램차원에서 transaction관리 추가예정
			conn.setAutoCommit(false); 	//setAutoCommit(true)하면 자동커밋허용
			//setAutoCommit(false)하면 자동커밋허용X
			
			//P596 22라인
			MemberDTO memberDTO = memberDAO.selectById(conn, joinReq.getId());
			if(memberDTO !=null) {	//특정id를 가진 회원이 존재한다면
				JdbcUtil.rollback(conn); //트랜잭션 rollback
				throw new DuplicateIdException();	//id중복예외
			}
			
			//p596 28라인
			//member테이블에 insert쿼리문을 실행하는 insertMember()메서드의 매개변수로
			//MemberDTO객체로 넘겼다
			//new MemberDTO(int mno, String id, String name, String password, Date regDate, String isshow)
			 n=memberDAO.insertMember(conn,
					new MemberDTO(joinReq.getId(), joinReq.getPassword(),joinReq.getName(),
							joinReq.getGender(), joinReq.getEmail(), joinReq.getPhone(), joinReq.getAddress(),joinReq.getBirth(),new Date(), "N",new Date(),""));
//			insert into member(ID,PASSWORD,NAME,GENDER,EMAIL,PHONE,ADDRESS,BIRTH,REGDATE,U_DELETE,DELDATE,GRADE)
	//		values ('admin','1234','웰컴','M','admin@naver.com','010-0000-0000','서울시 구로구 구로동 경영기술원','1995/12/01',sysdate,'N',NULL,'admin');
			
			conn.commit();	//트랜잭션제어 commit
		} catch (SQLException e) {	//p597 36라인
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {	//catch절에 들어가던 들어가지않던 무조건 실행되는 block
			//무조건 반드시 처리해야 할 코드
			JdbcUtil.close(conn);//Connection객체 자원 반납
			return n;
		}
		
		
	}//join

}//class
