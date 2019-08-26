package vn.de.lipo.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.de.lipo.webapp.model.DatabaseModel;
import vn.de.lipo.webapp.utils.ConnectionUtils;

public class RegisterServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		DatabaseModel model = (DatabaseModel) getServletContext().getAttribute("database");
		Connection connection = null;
		System.out.println("Get connection ...");
		System.out.println("database url: " + model.getDatabaseURL());
		System.out.println("username: " + model.getUsername());
		System.out.println("password: " + model.getPassword());
		try {
			connection = ConnectionUtils.getPostgresConnection(model.getDatabaseURL(), model.getUsername(), model.getPassword());
			Statement statement = connection.createStatement();
			System.out.println("Reading records...");
			ResultSet result = statement.executeQuery("SELECT * FROM public.Liquor");
			while (result.next()) {
				System.out.printf("liquorId: %s\n", result.getString("liquor_id"));
				res.setContentType("text/html");
				
				PrintWriter out = res.getWriter();
				out.println("test context attributes set by listener<br>");
				out.println("liquorID: " + result.getString("liquor_id"));
			}
			String email = (String) req.getParameter("email");
			String password = (String) req.getParameter("password");
			res.setContentType("text/html");
			
			PrintWriter out = res.getWriter();
			
			out.println("email: " + email);
			out.println("password: " + password);
			
			
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
