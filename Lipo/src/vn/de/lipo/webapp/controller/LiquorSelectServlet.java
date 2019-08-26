package vn.de.lipo.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.de.lipo.webapp.model.LiquorSelectModel;

public class LiquorSelectServlet extends HttpServlet {
	LiquorSelectModel liquorModel;
	
	// init instance variables for models
	public void init() {
		liquorModel = new LiquorSelectModel();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		init();
		if (req.getParameter("type").equals("yellow")) {
			liquorModel.setType("Yellow Liquor");
		}
		else if (req.getParameter("type").equals("white")) {
			liquorModel.setType("White Liquor");
		}
		else {
			liquorModel.setType("Unknown");
		}
		
		/*
		res.setContentType("text/html");
		PrintWriter output = res.getWriter();
		output.println("Liquor Selection Counter<br>");
		String c = liquorModel.getType();
		output.println("Liquor type: " + c);
		*/
		
		req.setAttribute("types", liquorModel.getType()); // ????
		
		RequestDispatcher view = req.getRequestDispatcher("result.jsp");
		
		view.forward(req, res);
	}
}
