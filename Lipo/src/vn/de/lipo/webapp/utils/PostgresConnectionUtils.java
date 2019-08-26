package vn.de.lipo.webapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionUtils {
	
	public static Connection getPostgresConnection(String url, String username, String password) throws SQLException, ClassNotFoundException, NullPointerException {
		Class.forName("org.postgresql.Driver"); // this is a must before getConnection
		Connection connection = DriverManager.getConnection(url, username, password);
		if (connection == null) {
			System.out.println("connection is null");
			return null;
		}
		else {
			return connection;
		}
	}
}
