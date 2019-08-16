package vn.de.lipo.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LiquorSelectServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter output = res.getWriter();
		output.println("Liquor Selection Counter<br>");
		String c = req.getParameter("type");
		output.println("Liquor color: " + c);
	}
}
