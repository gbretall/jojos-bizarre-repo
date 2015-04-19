package com.src.pkg;



import java.io.*;
import java.util.Properties;





import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

///**
// * Servlet implementation class MyServlet
// */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
	

	/** The name of the MySQL account to use (or empty for anonymous) */
	private String userName = "testuser";

	/** The password for the MySQL account (or empty for anonymous) */
	private String password = "testpass";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/**
	 * The name of the database we are testing with (this default is installed
	 * with MySQL)
	 */
	private final String dbName = "moviedb";

	private Connection conn = null;
	
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://" + this.serverName
				+ ":" + this.portNumber + "/" + this.dbName, connectionProps);

		return conn;
	}
	
    public MyServlet() throws ClassNotFoundException {
        super();
        // TODO Auto-generated constructor stub
        Class.forName("com.mysql.jdbc.Driver");
    }
    
    private boolean logInCheck(String username, String password) throws SQLException{
    	conn = getConnection();
    	
    	Statement loginSelect = conn.createStatement();
    	System.out.println(username);
    	System.out.println(password);
    	ResultSet loginResult = loginSelect.executeQuery("SELECT * "
    			+ "FROM moviedb.customers c "
    			+ "where (c.email = '"+username+ "'"
    					+ " and c.password = '"+password+"');");
    	//conn.close();
    	System.out.println(loginResult.isBeforeFirst());
    	return loginResult.isBeforeFirst();
    }
    
    
    
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	     response.setContentType("text/html");
	     PrintWriter out = response.getWriter();
	     
	     try {
			if (logInCheck(request.getParameter("Username"), request.getParameter("Password")))
			{
				out.println("logged in");
				conn.close();
			}
			else
			{
				out.println("not logged in");
				conn.close();
			}
		
		} 
	    catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
	    }
	      
//		  String title = "Using GET Method to Read Form Data";
//	      String docType =
//	      "<!doctype html public \"-//w3c//dtd html 4.0 " +
//	      "transitional//en\">\n";
//	      out.println(docType +
//	                "<html>\n" +
//	                "<head><title>" + title + "</title></head>\n" +
//	                "<body bgcolor=\"#f0f0f0\">\n" +
//	                "<h1 align=\"center\">" + title + "</h1>\n" +
//	                "<ul>\n" +
//	                "  <li><b>First Name</b>: "
//	                + request.getParameter("Username") + "\n" +
//	                "  <li><b>Last Name</b>: "
//	                + request.getParameter("Password") + "\n" +
//	                "</ul>\n" +
//	               "</body></html>");
	  }
	
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//PrintWriter out = response.getWriter();
		//out.println("hello post");
		doGet(request, response);
	}
//
}
