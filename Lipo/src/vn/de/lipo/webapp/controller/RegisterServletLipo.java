package vn.de.lipo.webapp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.de.lipo.webapp.model.DatabaseModel;
import vn.de.lipo.webapp.utils.ConnectionUtils;
import vn.de.lipo.webapp.utils.ConstantNameUtils;

public class RegisterServletLipo extends HttpServlet {
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
			
			String email = req.getParameter(ConstantNameUtils.EMAIL);
			String password = req.getParameter(ConstantNameUtils.PASSWORD);
			String username = req.getParameter(ConstantNameUtils.USERNAME);
			
			// prepare statement is a better choice for inserting and updating database
			PreparedStatement existedEmailChecking = connection.prepareStatement("Select email from Lipo_user where email = ?");
			
			PreparedStatement preparedStatement = connection.prepareStatement("Insert into Lipo_user values (?,?,?,?,?,?,?,?,?)");
			int result = registerUser(existedEmailChecking, preparedStatement, username, email, password, -1, null, null, java.time.LocalDate.now(), java.time.LocalDate.now());
			
			if (result == -1) {
				req.setAttribute("error", "Invalid email or username or password input, pls check again");
			}
			else if (result == -2) {
				req.setAttribute("error", "Email has been registered before.");
			}
			else {
				// set attribute for JSP
				req.setAttribute("email", email);
				req.setAttribute("username", username);
			}
			
			RequestDispatcher view = req.getRequestDispatcher("after_register.jsp");
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
	
	/**
	 * this function is used to register user with basic information as username, password and email
	 * other properties are set default.
	 * @param preparedStatement - statement to execute update
	 * @param username - username of the user
	 * @param email
	 * @param password
	 * @param age - default is -1
	 * @param gender - default is null
	 * @param city - default is null
	 * @param createdAt - set automatically as the created date
	 * @param updatedAt - same as created date
	 * @throws SQLException
	 */
	
	int registerUser(PreparedStatement existedEmailChecking, PreparedStatement preparedStatement, String username, String email, String password, int age, String gender, String city, 
			java.time.LocalDate createdAt, java.time.LocalDate updatedAt) throws SQLException {
		// check syntax error
		if (username == null || email == null || password == null || username.length() > 30
			|| email.length() > 30 || password.length() > 30) {
			return -1;
		}
		// existing email error
		int emailCheckingResult = checkExistingUser(existedEmailChecking, email);
		if (emailCheckingResult == -1) {
			return -2;
		}
		preparedStatement.setInt(1, (int)(Math.random() * 999999 + 100000));
		preparedStatement.setString(2, username);
		preparedStatement.setString(3, email);
		preparedStatement.setString(4, password);
		preparedStatement.setInt(5, age);
		preparedStatement.setString(6, gender);
		preparedStatement.setString(7, city);
		preparedStatement.setDate(8, java.sql.Date.valueOf(createdAt));
		preparedStatement.setDate(9, java.sql.Date.valueOf(updatedAt));
		
		preparedStatement.executeUpdate();
		
		return 0;
	}
	
	int checkExistingUser(PreparedStatement preparedStatement, String input) throws SQLException {
		int count = 0;
		preparedStatement.setString(1, input); // prepare to query to check if email has existed or not
		ResultSet result = preparedStatement.executeQuery();
		while (result.next()) {
			count++; // if size of the set is 0 then it is a new email
		}
		if (count == 0) {
			return 0;
		}
		return -1;
	}
}
