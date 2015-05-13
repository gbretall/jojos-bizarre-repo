package com.src.adminSite;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.src.adminSite.AdminGetConnection;
import com.src.adminSite.adminTopBar;
import com.src.pkg.ShoppingCart.moviesInCart;

/**
 * Servlet implementation class AdminDbUsers_Miles
 */
@WebServlet("/AdminDbUsers_Miles")
public class AdminDbUsers_Miles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDbUsers_Miles() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public class dbUsers{
    	private String name = "";
    	private String privlage = "";
    	
    	public dbUsers(String name, String privlage){
    		this.setName(name);
    		this.setPrivlage(privlage);
    	}
    	
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPrivlage() {
			return privlage;
		}
		public void setPrivlage(String privlage) {
			this.privlage = privlage;
		}
    	
    }
    
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private void makeTable(String tableName, ArrayList<dbUsers> users, ArrayList<String> arrayOfTables){
    	//TODO make table
    	
    	String debugOffest = "....................";
    	System.out.println(debugOffest+ "+++++++ Make table Hit ++++++");
    	System.out.println(debugOffest + tableName);
    	
    	String pageHeader = "<div class = 'page-header container'>"
    			+ "<h1>"+tableName+"</h1>"
    			+ "</div>";
    	
    	String tableHeader = "<thead>"
    			+ "<tr>"
    			+ "<th>Name</th>"
    			+ "<th>Current Privlages</th>"
    			+ "<th>Grant</th>"
    			+ "<th>Revoke</th>"
    			+ "</tr>"
    			+ "</thead>";
    	
    	String returnTable="<div class = 'container  col-md-6'>"
    			+ pageHeader
    			+ "<table class = 'table table-bordered table-striped'>"
    			+ tableHeader;   
    	
    	//String revokeButton = "";
    	
    	for (dbUsers user: users){
    		
        	String grantButton = "<form action = 'GrantPrivileges' method='get'>"
        			+ "<input type='hidden' name='username' value='"+ user.getName()+"'>"
        			+ "<button class='btn btn-lg btn-primary btn-block' id = 'signInButton' type='submit'>Grant</button>"
        			+ "</form>";
        	String revokeButton = "<form action = 'RevokePrivileges' method='get'>"
        			+ "<input type='hidden' name='username' value='"+ user.getName()+"'>"
        			+ "<button class='btn btn-lg btn-primary btn-block' id = 'signInButton' type='submit'>Revoke</button>"
        			+ "</form>";
    		
    		returnTable += "<tr>"
    				+ "<td>"+ user.getName()+"</td>"
    				+ "<td>"+ user.getPrivlage() +"</td>"
    				+ "<td>"+ grantButton +"</td>"
    				+ "<td>"+revokeButton+"</td>"
    				+ "</tr>";
    		System.out.println(debugOffest+ user.getName() +"  "+ user.getPrivlage());
    	}
    	System.out.print("\n");
    	
    	/* CLOSING TABS GO HERE!*/
    	returnTable += "</table>"
    			+ "</div>";
    	
    	arrayOfTables.add(returnTable);
    }
    
	private ArrayList<String> privlageInfoTableArray(
			ArrayList<String> tableNames, ArrayList<String> arrayOfTables) {

		ArrayList<String> tablesToMake = new ArrayList<String>();

		String sql = "select DISTINCT User, Table_name, Table_priv "
				+ "from mysql.tables_priv " + "where Table_name = ";
		String whereClause = "";

		System.out.println("----- Printing from privlageInfoTableArray -----");
		
		try {
			Connection conn = AdminGetConnection.getConnection();
			for (String tableName : tableNames) {
				System.out.println("privlages for: " + tableName + "\n");
				
				whereClause = "'" + tableName + "'" + ";";

				// System.out.println("SQL Query: " + sql + whereClause);

				Statement tableStatement = conn.createStatement();
				ResultSet resultTB = tableStatement.executeQuery(sql
						+ whereClause);
				
				if (resultTB.isBeforeFirst()) {
					ArrayList<dbUsers> users = new  ArrayList<dbUsers>();
					String currentTable = "";
					while (resultTB.next()) {						
						dbUsers currentUser = new dbUsers(resultTB.getString("User"), resultTB.getString("Table_priv"));
						
						if (currentTable.equals("")){
							currentTable = resultTB.getString("Table_name");
						}
						
						System.out.print(currentUser.getName() + "   ");
						System.out.print(resultTB.getString("Table_name")
								+ "   ");
						System.out.print(currentUser.getPrivlage() + "   ");
						System.out.println("");
						users.add(currentUser);
					}
					makeTable(currentTable, users, arrayOfTables);
					
				}
				resultTB.close();
				tableStatement.close();
			}
			conn.close();
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}

		 return tablesToMake;
	}
    
    private ArrayList<String> getMDBTableNames(){
    	
    	ArrayList<String> tableNames = new ArrayList<String>();
		try {
			Connection conn = AdminGetConnection.getConnection();
			Statement dbSwitch = conn.createStatement();
			dbSwitch.execute("use moviedb");
			dbSwitch.close();
			
			Statement tableStatement = conn.createStatement();
			ResultSet resultTB = tableStatement.executeQuery("show tables");
			
			String tableName;
			
			while (resultTB.next())
			{
				tableName = resultTB.getString(1);
				tableNames.add(tableName);
				//System.out.println(tableName);
			}
			resultTB.close();
			tableStatement.close();
			
			conn.close();
		} catch (SQLException | NamingException e) {
			// 
			e.printStackTrace();
		} 
		
		if (tableNames != null){
			System.out.println("======= From AdminDbUsers_Miles: table names =======");
			for(String name: tableNames){
				System.out.println(name);
			}
		}
		return tableNames;
    	
    }
    
    private ArrayList<String> usersWithoutPrivlages(){
    	
    	ArrayList<String> nonPrivlagesUsers = new ArrayList<String>();
    	
    	String sql = "select DISTINCT U1.User, U1.host "
    			+ "from mysql.user U1 "
    			+ "left outer join mysql.tables_priv U2 "
    			+ "on U1.User = U2.User "
    			+ "where U2.User is null and U1.host = 'localhost' and U1.User != 'root';";
    	
    	try {
			Connection conn = AdminGetConnection.getConnection();
			Statement findUPUsers = conn.createStatement();
			ResultSet UPUsers = findUPUsers.executeQuery(sql);
			
			if (UPUsers.isBeforeFirst()){
				while(UPUsers.next()){
					nonPrivlagesUsers.add(UPUsers.getString("User"));
					System.out.println(UPUsers.getString("User"));					
				}
			}
			UPUsers.close();
			findUPUsers.close();
			conn.close();
			
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		
    	return nonPrivlagesUsers;
    }

    private String tableOfUPUsers(){
    	  	
    	ArrayList<String> nonPrivlagesUsers = usersWithoutPrivlages();
    	String returnTable = "";
    	
    	if(nonPrivlagesUsers.size() > 0){
	    	String pageHeader = "<div class = 'page-header'>"
	    			+ "<h1>Non-Privlaged Users</h1>"
	    			+ "</div>";
	    	
	    	String tableHeader = "<thead>"
	    			+ "<tr>"
	    			+ "<th>Name</th>"
	    			+ "<th>Grant Privlages</th>"
	    			+ "</tr>"
	    			+ "</thead>";
	    	
	    	returnTable +="<div class = 'col-md-6 container'>"

	    			+ "<table class = 'table table-bordered table-striped'>"
	    			+ tableHeader; 
	    	
	    	for(String user: nonPrivlagesUsers){
	        	String grantButton = "<form action = 'GrantPrivileges' method='get'>"
	        			+ "<input type='hidden' name='username' value='"+ user+"'>"
	        			+ "<button class='btn btn-lg btn-primary btn-block' id = 'signInButton' type='submit'>Grant</button>"
	        			+ "</form>";
	        	
	    		returnTable += "<tr>"
	    				+ "<td>"+ user+"</td>"
	    				+ "<td>"+ grantButton +"</td>"
	    				+ "</tr>";   	
	    	}
	    	
	    	returnTable += "</table>"
	    			+ "</div>";  	
    	}
    	return returnTable;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    ArrayList<String> arrayOfTables = new ArrayList<String>();
		

		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println(adminTopBar.adminTopPage());
	    System.out.println("doGet");

	   
	    ArrayList<String> tableNames = getMDBTableNames();
	    ArrayList<String> tablesToMake = privlageInfoTableArray(tableNames, arrayOfTables); // arrayOfTables populated here

	    String pageHeader = "<div class = 'page-header'> <h1>"+"Current privilege"+"</h1></div>";
	    out.println(pageHeader);
	    for(String table : arrayOfTables){
	    	out.println(table);
	    }
	    
	    String privlageCheck =tableOfUPUsers();
	    if (!privlageCheck.equals("")){
	    	out.print("<br/>"
	    			+ "<br/>"
	    			+ "<br/>");
		    String pageHeader2 = "<div class = 'page-header'> <h1>"+"Users without privlage"+"</h1></div>";
		    out.println(pageHeader2);
		    out.println(privlageCheck);
	    }
	    
	    out.println("</html>"); 
	    //usersWithoutPrivlages();
	    
	    out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
