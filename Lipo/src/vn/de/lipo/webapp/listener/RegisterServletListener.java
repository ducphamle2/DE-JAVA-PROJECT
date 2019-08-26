package vn.de.lipo.webapp.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import vn.de.lipo.webapp.model.DatabaseModel;

public class RegisterServletListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext(); // get the context params of database
		String databaseURL = context.getInitParameter("databaseURL");
		String email = context.getInitParameter("username"); // these are located at web.xml
		String password = context.getInitParameter("password");
		DatabaseModel model = new DatabaseModel(databaseURL, email, password);
		context.setAttribute("database", model);
	}
	
}
