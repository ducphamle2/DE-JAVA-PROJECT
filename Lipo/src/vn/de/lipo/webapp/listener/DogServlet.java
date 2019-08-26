package vn.de.lipo.webapp.listener;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DogServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Dog dog = (Dog) getServletContext().getAttribute("dog");
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		out.println("test context attributes set by listener<br>");
		out.println("dog breed: " + dog.getBreed());
	}
}
