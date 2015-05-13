package com.src.adminSite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

public class AdminSQLHandler {
	private Connection	conn;
	private Statement	statement;
	private ResultSet	result;
		
	public AdminSQLHandler()
	{
		result = null;
		statement = null;
		conn = null;
	}
	public ResultSet query(String SQL) throws SQLException, NamingException
	{
		openConnections();
		result		= statement.executeQuery(SQL);
		return result;
	}
	
	public boolean execute(String SQL) throws SQLException, NamingException
	{
		openConnections();
		return statement.execute(SQL);
		
	}
	
	private void openConnections() throws SQLException, NamingException
	{
		close();
		conn		= AdminGetConnection.getConnection();
		statement	= conn.createStatement();
	}
	
	public void close() 
	{
	try{
			if (result	 !=null){result.close();}
			result	 = null;
			if (statement!=null){statement.close();}
			statement= null;
			if (conn	 !=null){conn.close();}
			conn	 = null;
			
		}
		catch(SQLException e)
		{
			System.out.println("An error occurred when closing the connections");
		}
		
	}
}
