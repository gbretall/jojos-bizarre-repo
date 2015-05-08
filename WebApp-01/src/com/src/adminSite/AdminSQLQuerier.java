package com.src.adminSite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

public class AdminSQLQuerier {
	private Connection	conn;
	private Statement	statement;
	private ResultSet	result;
		
	public AdminSQLQuerier()
	{
	
	}
	public ResultSet query(String SQL) throws SQLException, NamingException
	{
		conn		= AdminGetConnection.getConnection();
		statement	= conn.createStatement();
		result		= statement.executeQuery(SQL);
		
		return result;
	}
	public void close() throws SQLException
	{
		result.close();
		statement.close();
		conn.close();
	}
}
