package handler.sql;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GetConnection {
	private static Connection	conn;
	private static Statement	statement;
	private static ResultSet	result;

	public GetConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();;

	}

	public static Connection getConnection() throws SQLException,
			NamingException {
		Context initCtx = new InitialContext();
		if (initCtx == null)
			System.out.println("initCtx is NULL");

		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		if (envCtx == null)
			System.out.println("envCtx is NULL");

		// Look up our data source
		DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");

		if (ds == null)
			System.out.println("ds is null.");
		return ds.getConnection();
	}

	public static ResultSet getResultsOfQuery(String SQL) throws SQLException,
			NamingException {
		conn = GetConnection.getConnection();
		statement = conn.createStatement();
		result = statement.executeQuery(SQL);
		return result;
	}
	
	public void close() throws SQLException
	{
		result.close();
		statement.close();
		conn.close();
	}

}
