
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SQLHandler {
	private Connection conn;
	private Statement statement;
	private ResultSet result;

	public SQLHandler() {
		result = null;
		statement = null;
		conn = null;
	}

	public ResultSet query(String SQL) throws SQLException, NamingException {
		openConnections();
		result = statement.executeQuery(SQL);
		return result;
	}

	public boolean execute(String SQL) throws SQLException, NamingException {
		openConnections();
		return statement.execute(SQL);

	}

	private void openConnections() throws SQLException, NamingException {
		close();
		conn = getConnection();
		statement = conn.createStatement();
	}

	public void close() 
	{
	try{
			//if connections are not null, close them , then set them null
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
	
	private Connection getConnection() throws SQLException, NamingException {
		Context initCtx = new InitialContext();
		
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		if (envCtx == null)
			System.out.println("envCtx is NULL");

		// Look up our data source
		DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");

		if (ds == null)
			System.out.println("ds is null.");
		
		return ds.getConnection();
	}
}
