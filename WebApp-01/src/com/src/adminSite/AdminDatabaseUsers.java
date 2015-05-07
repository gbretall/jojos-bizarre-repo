package com.src.adminSite;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Servlet implementation class AdminDatabaseUsers
 */
@WebServlet("/AdminDatabaseUsers")
public class AdminDatabaseUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDatabaseUsers() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private String createUserTable() throws SQLException, NamingException
    {
    	ArrayList<String>  titles = new ArrayList<String>();
    	titles.add("Database");
    	titles.add("Select Privilege");
    	titles.add("Insert Privilege");
    	titles.add("Update Privilege");
    	titles.add("Delete Privilege");
    	titles.add("Execute Privilege");
    	String sqlQuery = "SELECT User, Db, Select_priv, Insert_priv, Update_priv, Delete_priv, Execute_priv "
    			+ "FROM mysql.db WHERE db = 'moviedb';";
    	UserResultContainer template = new UserResultContainer(titles);
		ArrayList<UserResultContainer> usersTable = new ArrayList<UserResultContainer>();
    	try
    	{
    		Connection conn = AdminGetConnection.getConnection();
    		Statement getUsersStatement = conn.createStatement();
    		ResultSet userResults = getUsersStatement.executeQuery(sqlQuery);
    		if(!userResults.isBeforeFirst())
    		{
    			return "NO USERS FOUND";
    		}
    		while(userResults.next())
    		{
    			if(!userResults.getString(1).equals("root"))
    			{
	    			ArrayList<String> data = new ArrayList<String>();
	    			data.add(userResults.getString(2));
	    			data.add(userResults.getString(3));
	    			data.add(userResults.getString(4));
	    			data.add(userResults.getString(5));
	    			data.add(userResults.getString(6));
	    			data.add(userResults.getString(7));
	    			//put the shit into a result container
	    			usersTable.add(template.createNewContainer(userResults.getString(1), data));
    			}
    		}
    	}
    	catch(SQLException e)
    	{
    		
    	}
    	return UserResultContainer.generateTableFromUserResultContainers(usersTable);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		out.println(adminTopBar.adminTopPage());
		try {
			out.println(createUserTable());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
