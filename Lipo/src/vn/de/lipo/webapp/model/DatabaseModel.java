package vn.de.lipo.webapp.model;

public class DatabaseModel {
	private String databaseURL;
	private String username;
	private String password;
	
	public DatabaseModel(String databaseURL, String username, String password) {
		this.databaseURL = databaseURL;
		this.username = username;
		this.password = password;
	}
	
	public String getDatabaseURL() {
		return databaseURL;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
}
