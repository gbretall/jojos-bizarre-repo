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
 * Servlet implementation class GrantPrivileges
 */
@WebServlet("/GrantPrivileges")
public class GrantPrivileges extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GrantPrivileges() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private ResultSet getTableNames() throws SQLException, NamingException
    {
    	ResultSet returnedResultSet = null;
    	try
    	{
    		Connection conn = AdminGetConnection.getConnection();
    		Statement dbNameStatement = conn.createStatement();
    		ResultSet dbResults = dbNameStatement.executeQuery("show databases;");
    		String dbName;
    		while(dbResults.next())
    		{
    			dbName = dbResults.getString(1);
    			if(dbName.equals("moviedb"))
    			{
    				Statement moviedbStatement = conn.createStatement();
    				moviedbStatement.execute("use moviedb;");
    				Statement tableNameStatement = conn.createStatement();
    				ResultSet tableResults = tableNameStatement.executeQuery("show tables;");
    				returnedResultSet = tableResults;
    				//tableResults.close();
    				//tableNameStatement.close();
    				//moviedbStatement.close();
    			}
    		}
    		//dbResults.close();
    		//dbNameStatement.close();
    		//conn.close();
    	}
    	catch(SQLException e)
    	{
    	}
    	if(returnedResultSet != null)
    	{
    		System.out.println("Is good mang");
    	}
    	return returnedResultSet;
    }
    
    private String createGrantForm(String username) throws SQLException, NamingException
    {
    	String form = "<h1>USER: "
    			+ username
    			+ "</h1><br>"
    			+ "<form action='GrantPrivileges' method='get'>"
    			+ "<input type='hidden' name='username' value='"+username+"'>"
    			+ "<input type='checkbox' name='privType' value='SELECT'>SELECT"
    			+ "<input type='checkbox' name='privType' value='UPDATE'>UPDATE"
    			+ "<input type='checkbox' name='privType' value='INSERT'>INSERT"
    			+ "<input type='checkbox' name='privType' value='DELETE'>DELETE"
    			+ "<input type='checkbox' name='privType' value='EXECUTE'>EXECUTE";
    	ResultSet tableNames = getTableNames();
    	if(tableNames.isBeforeFirst())
    	{
    		form += "<table class='table table-hover'>"
    				+ "<thead><th>Table Names</th></thead>"
    				+ "<tbody>";
    		while(tableNames.next())
    		{
    			form += "<tr><td><input type='checkbox' name='checkTableName' value='"
    					+ tableNames.getString(1)
    					+ "'>"
    					+ tableNames.getString(1)
    					+ "</td></tr>";
    		}
    		form += "</tbody>";
    	}
    	form += "<button class='btn btn-lg btn-primary btn-block' id = 'Grant Privileges' type='submit'>Submit</button>";
    	tableNames.close();
    	return form;
    }
    

	private String performGrant(String[] privilegesToGrant, String[] relevantTables, String username) throws SQLException, NamingException 
	{
		//try
		//{
			Connection conn = AdminGetConnection.getConnection();
			Statement grantStatement = conn.createStatement();
			for(int i = 0; i < privilegesToGrant.length; i++)
			{
				if(privilegesToGrant[i].equals("EXECUTE"))
				{
					grantStatement.execute("GRANT EXECUTE ON moviedb.* TO '" + username + "'@'localhost';");
				}
				else
				{
					for(int j = 0; j < relevantTables.length; j++)
					{
						grantStatement.execute("GRANT "+privilegesToGrant[i]+" ON moviedb."+relevantTables[j]+
								" TO '"+username+"'@'localhost';");
					}
				}
			}
		//}
		/*catch(SQLException e)
		{
			return "An error occurred";
		}*/
		return "Privileges successfully granted";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println(adminTopBar.adminTopPage());
		String username = request.getParameter("username");
		String[] privilegesToGrant = request.getParameterValues("privType");
		String[] relevantTables = request.getParameterValues("checkTableName");
		if(privilegesToGrant != null && relevantTables != null)
		{
			try {
				//for(String t: relevantTables)
					//System.out.println(t + " ");
				out.println(performGrant(privilegesToGrant, relevantTables, username));
			} catch (SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				out.println(createGrantForm(username));
			} catch (SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
