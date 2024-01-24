package common;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTemplate {

	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "MEMBERWEB";
	private static final String PASSWORD = "MEMBERWEB";
	
	private static JDBCTemplate instance;
	private static Connection conn;
	
	
	private JDBCTemplate() {
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// static 꼭 붙여주세요!
	public static Connection getConnection() {
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return conn;
	}
	
}










