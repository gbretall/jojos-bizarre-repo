package com.src.adminSite;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminCustomerErrorChecker
 */
@WebServlet("/AdminCustomerErrorChecker")
public class AdminCustomerErrorChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static String INVALID_EMAILS_QUERY = ""
			+"SELECT C.id, C.first_name, C.last_name, C.email"
			+ " FROM moviedb.customers C"
			+ " WHERE C.email NOT LIKE \"%@%\""
			+ " ORDER BY C.last_name, C.first_name, C.id;";
	
	private static String EXPIRED_CCS_QUERY = "SELECT CC.id, CC.first_name, CC.last_name, CC.expiration"
			+ " FROM moviedb.creditcards  CC inner join moviedb.customers CUST on CC.id =CUST.cc_id"
			+ " WHERE CC.expiration < curdate()"
			+ " ORDER BY CC.last_name, CC.first_name, CC.id;";

	
	private static String NO_INVLAID_EMAILS = "<table><th>No invalid emails found</th></table>";
	private static String NO_EXPIRED_CARDS  = "<table><th>No Expired Cards Found</th></table>";
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCustomerErrorChecker() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println(adminTopBar.adminTopPage());
	    out.println(getCustomerErrors());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private String getCustomerErrors()
	{
		ArrayList<String> titlesMalformed  = new ArrayList<String>();
		titlesMalformed.add("First Name");
		titlesMalformed.add("Last Name");
		titlesMalformed.add("Email");
		
		String table = "";
		table	+= "<h2>Customers With Emails Not Containing '@' Signs</h2>";
		table	+= AdminErrorChecker.createTableFromQuery(titlesMalformed, INVALID_EMAILS_QUERY, NO_INVLAID_EMAILS);
		ArrayList<String> titlesExpired  = new ArrayList<String>();
		titlesExpired.add("First Name");
		titlesExpired.add("Last Name");
		titlesExpired.add("Expiration");
		
		table	+= "<h2>Expired Customer Credit Cards</h2>";
		table	+= AdminErrorChecker.createTableFromQuery(titlesExpired, EXPIRED_CCS_QUERY, NO_EXPIRED_CARDS);
		
		return table;
	}
	
	private String getMalformedCustomerEmailsTable()
	{
		ArrayList<String> titles  = new ArrayList<String>();
		titles.add("First Name");
		titles.add("Last Name");
		titles.add("Email");
		
		String table = "";
		String sql = "SELECT C.id, C.first_name, C.last_name, C.email"
				+ " FROM moviedb.customers C"
				+ " WHERE C.email NOT LIKE \"%@%\""
				+ " ORDER BY C.last_name, C.first_name, C.id;";
		try{		
			ResultSet	customerResult		= AdminGetConnection.getResultsOfQuery(sql);
			
			AdminResultContainer containerTemplate	= new AdminResultContainer(titles);
			ArrayList<AdminResultContainer> results	= new ArrayList<AdminResultContainer>();
			
			if (customerResult.isBeforeFirst())
			{
				while(customerResult.next()){
					//get things other than the id
					//note: there may be more thing added if we 
					//decide to show more rows than just id and email
					ArrayList<String> data = new ArrayList<String>();
					data.add(customerResult.getString(2));//first name
					data.add(customerResult.getString(3));//last name
					data.add(customerResult.getString(4));//email
					//put the shit into a result container
					results.add(containerTemplate.createNewContainer(customerResult.getInt(1), data));
				}
				table+=AdminResultContainer.generateTableFromResultContainers(results);
			}
			else
			{
				table+="<table><th>No invalid emails found</th></table>";
			}
			
		}catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		return table;
	}
	
	private String getExpiredCustomerCreditCardTable()
	{
		ArrayList<String> titles  = new ArrayList<String>();
		titles.add("First Name");
		titles.add("Last Name");
		titles.add("Expiration");
		
		String table = "";
		String sql = "SELECT CC.id, CC.first_name, CC.last_name, CC.expiration"
				+ " FROM moviedb.creditcards  CC inner join moviedb.customers CUST on CC.id =CUST.cc_id"
				+ " WHERE CC.expiration < curdate()"
				+ " ORDER BY CC.last_name, CC.first_name, CC.id;";
		try{		
			ResultSet	customerResult		= AdminGetConnection.getResultsOfQuery(sql);
			
			AdminResultContainer containerTemplate	= new AdminResultContainer(titles);
			ArrayList<AdminResultContainer> results	= new ArrayList<AdminResultContainer>();
			
			if (customerResult.isBeforeFirst())
			{
				while(customerResult.next()){
					//get things other than the id
					//note: there may be more thing added if we 
					//decide to show more rows than just id and email
					ArrayList<String> data = new ArrayList<String>();
					data.add(customerResult.getString(2)); //first name
					data.add(customerResult.getString(3)); //last name
					data.add(customerResult.getString(4)); //expiration
					
					//put the shit into a result container
					results.add(containerTemplate.createNewContainer(customerResult.getString(1), data));
				}
				table+=AdminResultContainer.generateTableFromResultContainers(results);
			}
			else
			{
				table+="<table><th>No Expired Cards Found</th></table>";
			}
			
		}catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		return table;
	}
	

}
