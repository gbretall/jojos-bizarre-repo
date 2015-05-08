package com.src.adminSite;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminCreateUser
 */
@WebServlet("/AdminCreateUser")
public class AdminCreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCreateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println(adminTopBar.adminTopPage());
	    String password = request.getParameter("Password");
	    String userName = request.getParameter("Username");
	    
	    if(userName != null && password != null){
		    if (userName.contains (" "))
		    {
		    	out.println("<h1>User Was not Made</h1>");
		    	out.println("<h3>User names may not contain spaces.</h3>");
		    	out.println("<a href=\"CreateUser.html\">Click Here to Try Again</a>");
		    }
		    else if (userExists(userName))
		    {
		    	out.println("<h1>User Was not Made</h1>");
		    	out.println("<h3> The Username Given is Already Taken</h3>");
		    	out.println("<a href=\"CreateUser.html\">Click Here to Try Again</a>");
		    }
		    else
		    {
		    	try {
					createUser(userName, password);
					out.println("<h1>User Was Made</h1>");
					out.println("<a href='AdminMainPage.html'>Return to Main Page</a>");
				} catch (SQLException | NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
	    }
		
	}
	
    private Boolean userExists(String userName)
    {
    	String sql = ""
    			+ "Select * from mysql.user U "
    			+ "where U.User = '"+userName+"' ";
    	
    	try {
    		AdminSQLHandler connections = new AdminSQLHandler();
			ResultSet result = connections.query(sql);
			boolean userFound = result.isBeforeFirst();
			connections.close();
			return userFound;
			
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }
    private Boolean createUser(String userName, String password) throws SQLException, NamingException
    {
    	String sql = "CREATE USER '"+userName+"'@'localhost' IDENTIFIED BY '"+password+"';";
    	
		try{
			AdminSQLHandler connections = new AdminSQLHandler();
			
			boolean result =connections.execute(sql);
			connections.close();
			return result;
		}
		catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		return false;
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
