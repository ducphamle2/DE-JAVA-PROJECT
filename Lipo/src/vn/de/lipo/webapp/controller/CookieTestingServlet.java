package vn.de.lipo.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// get from cookie.html
// cookie.jsp gives a link to cookie.html
public class CookieTestingServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		Cookie[] cookies = req.getCookies();
		
		if (cookies.equals(null)) {
			out.println("null cookies");
		}
		else {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("email")) {
					out.println("email: " + cookie.getValue());
				}
				if (cookie.getName().equals("password")) {
					out.println("password: " + cookie.getValue());
				}
			}	
		}
	}
}
