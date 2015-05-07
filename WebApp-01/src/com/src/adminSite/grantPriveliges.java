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
 * Servlet implementation class grantPriveliges
 */
@WebServlet("/grantPriveliges")
public class grantPriveliges extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public grantPriveliges() {
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
    				tableResults.close();
    				tableNameStatement.close();
    				moviedbStatement.close();
    			}
    		}
    		dbResults.close();
    		dbNameStatement.close();
    		conn.close();
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
    			+ "<form action='grantPrivelige?success=true&username="
    			+ username
    			+ "' method='get'>"
    			+ "<input type='checkbox' name='privType' value='select'>SELECT"
    			+ "<input type='checkbox' name='privType' value='update'>UPDATE"
    			+ "<input type='checkbox' name='privType' value='insert'>INSERT"
    			+ "<input type='checkbox' name='privType' value='delete'>DELETE"
    			+ "<input type='checkbox' name='privType' value='execute'>EXECUTE";
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
    		form += "</tbody>"
    				+ "<button class='btn btn-lg btn-primary btn-block' id = 'Grant Privileges' type='submit'>Submit</button>";
    	}
    	form += "";
    	return form;
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
			for(String s: privilegesToGrant)
			{
				System.out.print(s + " ");
			}
			System.out.println("");
			for(String s: relevantTables)
			{
				System.out.print(s + " ");
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
