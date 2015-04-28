package com.src.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class CheckOut
 */

@WebServlet("/CheckOutCheck")
public class CheckOutCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection conn = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutCheck() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public boolean checkUserInfo(String firstName, String lastName,
    		String creditNumber, String expiration) throws SQLException {
    	String sql ="SELECT * "
    			+ "FROM moviedb.creditcards CC"
    			+ " where CC.first_name ='"+firstName+"'"
    			+ "and CC.last_name = '"+lastName+"' "
    			+ "and CC.id = '"+creditNumber+"' "
    			+ "and CC.expiration = '"+expiration+"';";
    	
    	Statement getCardStatment = conn.createStatement();
    	ResultSet resultCard = getCardStatment.executeQuery(sql);
    	
		return resultCard.isBeforeFirst();
	}
    
    public void addToDatabase(Set<String> movieIDSet, String userID) throws SQLException{
    	Statement getCardStatment = conn.createStatement();
    	for (String movieIds: movieIDSet){
        	String sql = "INSERT INTO moviedb.sales(customer_id, movie_id, sale_date) " +
                    "VALUES ("
                    + "'"+userID+"', "
                    + "'"+movieIds+"', "
                    + " date(now()))";	
        	getCardStatment.executeUpdate(sql);
    	}
    	
    }
    
    
	public Connection getConnection() throws SQLException, NamingException {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			conn = getConnection();
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println(AddTopBar.makeTopOfPage());		
		
		String creditNumber = request.getParameter("creditNumber");
		String expiration = request.getParameter("expiration");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		//String testParam = (String) request.getAttribute("varName");
		String userID = (String)session.getAttribute("userID");
		System.out.println(userID);
		Set<String> movieIDSet = (Set<String>)session.getAttribute("movieIDSet");
		
		
		
		if(movieIDSet ==null){
			out.println("<h1>Ya nothing in your cart, ya dingus</h1>");
		}
		if (userID == null) {
			System.out.println("this if was hit in CheckOutCheck");
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String site = new String("index.html");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}
		
		
		
		if (creditNumber == null || expiration == null ||
				firstName == null || lastName == null){
			out.println(makeCheckOutPage());
			//System.out.println("One of the values was null");
		}
		
		else{
			try {
				if (checkUserInfo(firstName, lastName, creditNumber, expiration)){
					System.out.println("login worked");
					if(movieIDSet != null){
					addToDatabase(movieIDSet, userID);
					session.setAttribute("userID", userID);
					response.sendRedirect("/LogOutServlet?checkout=true");
					//String site = new String("index.html");
					//response.setStatus(response.SC_MOVED_TEMPORARILY);
					//response.setHeader("Location", site);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(creditNumber);
			System.out.println(expiration);
			System.out.println(firstName);
			System.out.println(lastName);
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
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
	
	private String makeCheckOutPage(){
		String pageToGetCheckoutInfo ="	<div class='container'>"
      +"<form class='form-signin' action = 'CheckOutCheck' method='post'>"
        +"<h2 class='form-signin-heading'>Please Check In</h2>"
        
        +"<label for='inputEmail' class='sr-only'>First Name</label>"
        +"<input type='text' id='inputEmail' class='form-control' placeholder='John' required='' autofocus='' name ='firstName'>"
        
        +"<label for='inputPassword' class='sr-only'>Last Name</label>"
        +"<input type='text' id='inputPassword' class='form-control' placeholder='Doe' required='' name = 'lastName'>"
        
        +"<label for='inputEmail' class='sr-only'>Credit Card Number</label>"
        +"<input type='text' id='inputEmail' class='form-control' placeholder='XXXX-XXXX' required='' autofocus='' name ='creditNumber'>"
        
        +"<label for='inputEmail' class='sr-only'>Exp. Date</label>"
        +"<input type='text' id='inputEmail' class='form-control' placeholder='yyyy-mm-dd' required='' autofocus='' name ='expiration'>"
        
        +"<button class='btn btn-lg btn-primary btn-block' type='submit'>Sign in</button>"
      +"</form>"
    +"</div>";
		return pageToGetCheckoutInfo;
	};

}
