package wateon.DB;

import java.sql.*;

public class DBConnector {
	
	private Connection conn;
	private String connStr = "jdbc:mysql://localhost:3306/wateon";
	private String id = "root";
	private String password = "1234";
	
	public void connect() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connStr, id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		if(conn==null)
			connect();
		return conn;
	}
	
	public void disconnect() {

		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
