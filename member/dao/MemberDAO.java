package member.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.model.MemberDTO;


//DAO(Data Access Object):

//p592
//회원관련 DB작업 클래스
public class MemberDAO {
		//필드
		//생성자
	
		//메서드
		//회원개인정보 조회-p592 15라인
		public  MemberDTO selectById(Connection conn,String id) throws SQLException {
				System.out.println("MemberDAO의 selectById(String id)="+id);
				//파라미터 id에는 로그인폼에서 유저가 입력한 id가 담겨진다
				//1.드라이버로딩	-> web.xml와 DBCPInitListener.java에서 작업
				//2.커넥션얻기 -> ConnectionProvider.java에서 작업
//				Connection conn = ConnectionProvider.getConnection();
				
				//3.실행을 위한 (객체)준비-p592 19라인
				//쿼리문 작성시, 공백주의, 마지막에 ;미포함
				String sql="SELECT ID,PASSWORD,NAME,GENDER,EMAIL,PHONE,ADDRESS,BIRTH,REGDATE,U_DELETE,DELDATE,GRADE"+ 
									" FROM member"+ 
									" WHERE id=?";
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					pstmt = conn.prepareStatement(sql);
					//4.쿼리문실행
					/*
					 * pstmt.executeQuery() : select문실행 
					 * pstmt.executeUpdate() : insert,delete,update문 실행*/
					//pstmt.set자바데이터타입(?순번,값)
					//pstmt.setString(1,user가 입력한 id값);
					pstmt.setString(1,id);
					//pstmt.setDate(2,new Date());
					//pstmt.setInt(3,1);
					rs = pstmt.executeQuery();
					//select 실행경과의 레코드수가 몇개인지 모르므로 
					//반복해서 가져와야할 반복횟수를 예측하기 힘드므로 while문 사용하는데
					//여기에서는 레코드가 존재하면 단 1개의 행을 리턴하므로 if문 사용하였다
					
					//교재에서는 (p591)Member.java로 작성하였다
					//select결과로서 회원정보를 담기위한 변수선언 및 초기화
					MemberDTO memberDTO = null;
					//예) request.setAttribute("memberDTO",memberDTO);
					
//					String id=null;
					String name=null;
					//p592 24라인
					if(rs.next()){		//가져올 행이 있으면 true리턴 ->select결과물이 있다면
						//record마다 반복해서 각 각의
							/*rs.get자바데이터타입(String 컬럼명)
							rs.get자바데이터타입(int select순서)	:	columnIndex는 1부터시작. 첫번째 컬럼은 1*/
						/*	mno=rs.getInt(1);	//회원번호
							id=rs.getString(2);	//회원id
							mname=rs.getString(3);	//회원명	*/
							
//							id=rs.getString("id");	//회원id
							name=rs.getString("name");	//회원명
							
							
							//기본생성자를 이용하여 해당클래스의 필드값을 불러온다
							memberDTO = new MemberDTO();
							memberDTO.setId(rs.getString("id"));
							memberDTO.setPassword(rs.getString("password"));
							memberDTO.setName(name);
							memberDTO.setGender(rs.getString("gender"));
							memberDTO.setEmail(rs.getString("email"));
							memberDTO.setPhone(rs.getString("phone"));
							memberDTO.setAddress(rs.getString("address"));
							//toDate(rs.getTimestamp("birth"));
							memberDTO.setBirth(rs.getString("birth"));
							//toDate(rs.getTimestamp("regdate"));
							memberDTO.setRegDate(rs.getDate("regdate"));
							//toDate(rs.getTimestamp("delDate"));
							memberDTO.setDelDate(rs.getDate("delDate"));
							memberDTO.setGrade(rs.getString("grade"));
							
							//다양한타입의 (컬럼)값을 모아 MemberDTO클래스의 객체에 담아
							//Service클래스의 login메서드로 리턴해준다
					}
					System.out.println("memberDTO="+memberDTO);	//콘솔 확인용
					
					return memberDTO;
				}finally {
					//5.사용한 객체&커넥션 반납
					JdbcUtil.close(rs);
					JdbcUtil.close(pstmt);
				}
				

		}//selectById()

		
		//회원가입-p593 42라인
		//JoinService에서 회원정보를 매개변수의 MemberDTO객체로 넘겨받았다
		//insert into member(mid,mname,pwd,regdate,isshow) values(?,?,?,?,?);
		public int insertMember(Connection conn, MemberDTO memberDTO) throws SQLException {
			//3.실행을 위한 (객체)준비
			String sql = "INSERT INTO MEMBER(ID,PASSWORD,NAME,GENDER,EMAIL,PHONE,ADDRESS,BIRTH,REGDATE,U_DELETE,DELDATE,GRADE)"+
								 " VALUES(?,?,?,?,?,?,?,?,sysdate,?,sysdate,?)";
			//?에 SYSDATE 로 넣을것 그리고 SETDate 쓸것
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//4.쿼리문실행 -?설정후 ->쿼리실행 순서로 작업한다
			//pstmt.set자바데이터타입(?순서,값)
			pstmt.setString(1,memberDTO.getId());
			pstmt.setString(2,memberDTO.getPassword());
			pstmt.setString(3,memberDTO.getName());
			pstmt.setString(4,memberDTO.getGender());
			pstmt.setString(5,memberDTO.getEmail());			
			pstmt.setString(6,memberDTO.getPhone());
			pstmt.setString(7,memberDTO.getAddress());
			pstmt.setString(8,memberDTO.getBirth());
			//pstmt.setTimestamp(8,new Timestamp( memberDTO.getBirth().getTime()));
			//pstmt.setTimestamp(9,new Timestamp( memberDTO.getRegDate().getTime()));
			pstmt.setString(9,memberDTO.getU_delete());
			//pstmt.setTimestamp(4, new Timestamp( memberDTO.getRegDate().getTime()));
			//pstmt.setTimestamp(11,new Timestamp( memberDTO.getDelDate().getTime()));
			pstmt.setString(10,memberDTO.getGrade());
			
			int n =pstmt.executeUpdate();	//insert,delete,update문 실행	
			return  n;
		}
		
		
		//Timestamp타입을 Date타입으로 변경
//		private Date toDate(Timestamp date) {
//			return date==null? null:new Date(date.getTime());
//		}
		public void update(Connection conn, MemberDTO memberDTO) throws SQLException {
			String sql="update member set name = ?, password = ? where id=?";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1,memberDTO.getName());
				pstmt.setString(2,memberDTO.getPassword());
				pstmt.setString(3,memberDTO.getId());
				pstmt.executeUpdate();
			}
		}
	
}//class
