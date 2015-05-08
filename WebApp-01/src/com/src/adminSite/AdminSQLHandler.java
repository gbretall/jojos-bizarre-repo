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
		conn		= AdminGetConnection.getConnection();
		statement	= conn.createStatement();
	}
	
	public void close() throws SQLException
	{
		if (result!=null)
		{
		result.close();
		}
		statement.close();
		conn.close();
	}
}
