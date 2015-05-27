
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.naming.NamingException;


import java.sql.Statement;



public class SQLHandler {
	private Connection conn;
	private Statement statement;
	private ResultSet result;
	/** The name of the MySQL account to use (or empty for anonymous) */
	private String userName = "xmlparser";

	/** The password for the MySQL account (or empty for anonymous) */
	private String password = "21284961";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/**
	 * The name of the database we are testing with (this default is installed
	 * with MySQL)
	 */
	private final String dbName = "bookdb";
	
	private Properties connectionProps = new Properties();

	public SQLHandler() {
		//result = null;
		//statement = null;
		//conn = null;
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
	}

	public ResultSet query(String SQL) throws SQLException, NamingException {
		openConnections();
		//statement = conn.prepareStatement(SQL);
		statement = conn.createStatement();
		result = statement.executeQuery(SQL);
		return result;
	}

	public boolean execute(String SQL) throws SQLException, NamingException {
		openConnections();
		//statement = conn.prepareStatement(SQL);
		statement = conn.createStatement();
		return statement.execute(SQL);
	}

	private void openConnections() throws SQLException, NamingException {
		close();
		//conn = getConnection();
		conn = DriverManager.getConnection("jdbc:mysql://" + this.serverName
				+ ":" + this.portNumber + "/" + this.dbName, connectionProps);
	
	}

	public void close()  
	{
		try{
			//if connections are not null, close them , then set them null
			if(result != null)	{result.close();}
			//result	 = null;
		}
		catch(SQLException e)
		{
			System.out.println("An error occurred when closing the results");
		}
		try
		{
			if(statement != null) {statement.close();}
		}
		catch(SQLException e)
		{
			System.out.println("An error occurred when closing the statement");
		}
			//statement= null;
		try
		{
			if(conn != null) {conn.close();}
		}
			//conn	 = null;
		catch(SQLException e)
		{
			System.out.println("An error occurred when closing the connection");
		}
	}
	
	private Connection getConnection() throws SQLException, NamingException {
		/*Context initCtx = new InitialContext();
		
		if (initCtx == null)
			System.out.println("initCtx is NULL");

		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		if (envCtx == null)
			System.out.println("envCtx is NULL");

		// Look up our data source
		DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");

		if (ds == null)
			System.out.println("ds is null.");
		
		return ds.getConnection();*/
		Connection newConn = DriverManager.getConnection("jdbc:mysql://" + this.serverName
				+ ":" + this.portNumber + "/" + this.dbName, connectionProps);

		return newConn;
	}
}
