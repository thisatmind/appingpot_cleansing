package appingpot.data_cleansing;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/appingpot_db";

	static final String USERNAME = "root";
	static final String PASSWORD = "root";
	
	public static Connection getConnection() throws Exception {
		
		Class.forName(JDBC_DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		return conn;
	}

}
