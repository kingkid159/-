package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//p586
// connection을 제공하는 목적의 클래스
public class ConnectionProvider {
		//throws절을 갖는 메소드는 메소드의 코드를 실행하다가 에러가 발생할 수 있고
		//에러가 발생하면 throws절에 명시한 클래스가 에러를 처리하게 된다.
		//static은 정적메소드 -> 클래스명.메서드명으로 접근할 수 있다.
		public static Connection getConnection() throws SQLException {
				return DriverManager.getConnection("jdbc:apache:commons:dbcp:board");
		}
}
