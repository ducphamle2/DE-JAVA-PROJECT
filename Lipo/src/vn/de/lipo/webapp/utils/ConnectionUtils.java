package vn.de.lipo.webapp.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtils {
	public static Connection getPostgresConnection(String url, String email, String password) throws SQLException, ClassNotFoundException, NullPointerException {
		return PostgresConnectionUtils.getPostgresConnection(url, email, password);
	}

	public static void main(String[] args) {
		final String url = "jdbc:postgresql://localhost:5432/lipodb";
		final String username = "ducpham";
		final String password = "123456";
		Connection connection = null;
		System.out.println("Get connection ...");
		try {
			connection = ConnectionUtils.getPostgresConnection(url, username, password);
			Statement statement = connection.createStatement();
			System.out.println("Reading records...");
			ResultSet result = statement.executeQuery("SELECT * FROM public.Liquor");
			while (result.next()) {
				System.out.printf("liquorId: %s\n", result.getString("liquor_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Connection status: " + connection);
		}
	}
}
