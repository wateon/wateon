package wateon.DB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class DBConnector {
	private static Properties properties = null;
	private Connection conn;
	private String connStr;
	private String id = "mashdown";
	private String password = "wd1212";
	
	public DBConnector() {
		if (properties == null)
			initializeProperties();
		
		String host = properties.getProperty("db.host");
		String port = properties.getProperty("db.port");
		String name = properties.getProperty("db.name");
		
		connStr = "jdbc:mysql://" + host + ":" + port + "/" + name;
		id = properties.getProperty("db.user");
		password = properties.getProperty("db.password");
	}

	private void initializeProperties() {
		properties = new Properties();
		FileInputStream fis = null;
		String SEPARATOR = java.io.File.separator;
		
		URL url = getClass().getResource("");
		String filename = url.getPath().substring(0, url.getPath().indexOf("/WEB-INF"));
		filename = filename.replace("%20", " ") + SEPARATOR + "WEB-INF" + SEPARATOR + "wateon.properties";
		
		//System.out.println(filename);
		
		try {
			fis = new FileInputStream(filename);
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				}
				catch(IOException ex) {
				}
			}
		} // end of try
	}

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
