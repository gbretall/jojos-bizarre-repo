package com.src.pkg;



import java.io.*;
import java.util.Properties;







import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;


///**
// * Servlet implementation class MyServlet
// */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {

	private Connection conn = null;
	
	public Connection getConnection() throws SQLException, NamingException {
		Context initCtx = new InitialContext();
        if (initCtx == null) System.out.println ("initCtx is NULL");
	   
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
       if (envCtx == null) System.out.println ("envCtx is NULL");
		
      // Look up our data source
      DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
      
      if (ds == null)
		   System.out.println ("ds is null.");
		return ds.getConnection();
	}
	
    public MyServlet() throws ClassNotFoundException {
        super();
        // TODO Auto-generated constructor stub
        Class.forName("com.mysql.jdbc.Driver");
    }
    
    private boolean logInCheck(String username, String password) throws SQLException, NamingException{
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
		
	    } catch (NamingException e) {
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
