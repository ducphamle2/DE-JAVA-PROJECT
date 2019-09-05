package vn.de.lipo.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
		DatabaseModel model = null;
		/*
		 * we need to synchronize the attributes cuz of thread safe.
		 * For more info of how to use synchronized keyword: https://www.javatpoint.com/synchronized-block-example
		 * problems with attributes in multithreading apps are stated in chap 5 page 220 of head first servlet JSP
		 * here we need to lock the ServletContext returned by the method getServletContext()
		 */
		synchronized(getServletContext()) {
			model = (DatabaseModel) getServletContext().getAttribute("database");
		}
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
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			Cookie emailCookie = new Cookie("email", email); // create cookie to keep storing data after user refreshs or turns off browser
			Cookie passCookie = new Cookie("password", password);
			emailCookie.setMaxAge(20*60); // the time in sec to keep user data
			passCookie.setMaxAge(20*60);
			
			res.setContentType("text/html");
			res.addCookie(passCookie); // add cookies in the response so we can get the result again
			res.addCookie(emailCookie);
			
			Integer random = (int)(Math.random() * 999999 + 100000); // random for userID with 6 figures (for now)
			
			//PrintWriter out = res.getWriter();
			
			//out.println("email: " + email);
			//out.println("password: " + password);
			
			/*
			if (email != null && password != null) {
				String sql = "insert into Lipo_user values (\'" + random.toString() + "\', \'xxx\', \'" + email + "\', \'" + password + "\', \'20\', \'Male\', \'Hanoi\', \'" + java.time.LocalDate.now() + "\', \'" + java.time.LocalDate.now() + "\')";
				out.println("sql line: " + sql);
				statement.executeUpdate(sql);
			}
			*/
			
			RequestDispatcher view = req.getRequestDispatcher("cookie.jsp");
			view.forward(req, res);
			
			
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
