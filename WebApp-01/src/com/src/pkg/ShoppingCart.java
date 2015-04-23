package com.src.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
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

import com.src.pkg.ShoppingCart.moviesInCart;

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public class moviesInCart{
		private String movieTitle = "";
		private String movieID = "";
		private float price = 0;
		private int quantity = 0;
		
    	public moviesInCart(String movieTitle, String movieID, float price, int quantity){
    		this.setMovieTitle(movieTitle);
    		this.setmovieID(movieID);
    		this.setPrice(price);
    		this.setQuantity(quantity);
    	}
    	

		public String getMovieTitle() {
			return movieTitle;
		}
		public void setMovieTitle(String movieTitle) {
			this.movieTitle = movieTitle;
		}
		public float getPrice() {
			return price;
		}
		public void setPrice(float price) {
			this.price = price;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}


		public String getmovieID() {
			return movieID;
		}


		public void setmovieID(String movieID) {
			this.movieID = movieID;
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
	
	private String getMovieTitle(String movieID) throws SQLException, NamingException{
		conn = getConnection();
		Statement movieTitleStatment = conn.createStatement();
		System.out.println("getMovieTitle is called with id= "+ movieID);
		ResultSet resultTitle = movieTitleStatment.executeQuery("SELECT m.title "
				+ "FROM moviedb.movies m "
				+ "where m.id = '"+movieID+"';");
		while(resultTitle.next()){
			return resultTitle.getString("title");
		}
		//return null;
		return "No Movie Found";
	}

    
    private float moneyTotal = 0;

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	    HttpSession session = request.getSession();
	    
	    ArrayList<moviesInCart> previousItems = (ArrayList<moviesInCart>)session.getAttribute("previousItems");
	    Set<String> movieIDSet = (Set<String>)session.getAttribute("movieIDSet");
	    
	    String movieID = request.getParameter("movieID");
	    String adding = request.getParameter("adding");
	    
	    if (previousItems == null) {
	      previousItems = new ArrayList();
	      session.setAttribute("previousItems", previousItems);
	    }
	    
	    if (movieIDSet == null) {
	    	movieIDSet = new HashSet<String>();
		      session.setAttribute("movieIDSet", movieIDSet);
		}

	    //moviesInCart newMovie = new moviesInCart(request.getParameter("movieID"), 15.99f, 0);
	    moviesInCart newMovie = null;
	    
	    
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println(makeTopOfPage());
	    
	    if(request.getParameter("logout") != null)
	    {  
		    System.out.println(request.getParameter("logout"));
		    session.invalidate();
			String site = new String("index.html");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
	    }
//	    System.out.println(request.getParameter("movieID"));
	    if(movieID != null && adding != null){
	    	if(adding.equals("true")){
	    		
//	    		previousItems.add(request.getParameter("movieID"));
	    		if (movieIDSet.add(movieID)){
	    			//System.out.println(movieIDSet.add(movieID));
	    			System.out.println(movieIDSet.size());
						try {
							//System.out.println("What is this: "+getMovieTitle(request.getParameter("movieID"))+ " "+request.getParameter("movieID").getClass());
							newMovie = new moviesInCart(getMovieTitle(movieID), movieID , 15.99f, 1);
							previousItems.add(newMovie);
							    
						} catch (SQLException | NamingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for(String x: movieIDSet){
							System.out.println(x);
						}
	    		}
	    		else{
	    			System.out.println("In Set");
	    			for (moviesInCart c: previousItems){
	    				if(c.getmovieID().equals(movieID)){
	    					System.out.println("already here");
	    					c.setQuantity(c.getQuantity()+1);
	    				}
	    			}
	    			//System.out.println(movieIDSet.size());
	    		}
	    	}
	    	
	    	if(adding.equals("false")){
	    		//System.out.println("delete item called");
	    		int toDelete = -1;
	    		System.out.println("deleting movie " + movieID);
	    		for (moviesInCart c: previousItems){
    				if(c.getmovieID().equals(movieID)){
    					//System.out.println("already here");
    					c.setQuantity(c.getQuantity()-1);
    					if (c.getQuantity() <= 0){
    						toDelete = previousItems.indexOf(c);
    					}
    				}
    				
    			}
	    		if(toDelete >= 0){
	    			System.out.println("delete item called");
	    			previousItems.remove(toDelete);
	    			}
	    	}
	    }

	    
	   out.println(makeTable(previousItems));
	   
	   
	   NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
	   
	   out.println("<h1> Grand total: "+defaultFormat.format(getMoneyTotal())+"</h1>");
	   out.println("</BODY></HTML>");
	   if(conn != null){
			try {
					conn.close();
				} 
			catch (SQLException e) {
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
	}
	

	private String makeTopOfPage(){
		String topOfPage= "<!DOCTYPE html>"
				+"<html>"
				+"<head lang='en'>"
				    +"<meta charset='utf-8'>"
				    +"<meta http-equiv='X-UA-Compatible' content='IE=edge'>"
				    +"<meta name='viewport' content='width=device-width, initial-scale=1'>"
				    + "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>"
				    +"<link rel='stylesheet' type='text/css' href='topbarcss.css'>"
				    +"<title>M.L.G. Videos</title>"
				+"</head>"
				+"<body>"
					+"<div id='header'>"
						    +"M.L.G.Videos  "
						    +"<button type='button' id ='main-button'>Main</button>"
						    +"<button type='button' id ='search-button'>Search</button>"
						    +"<button type='button' id = 'browse-button'>Browse</button>"
						    +"<button type='button' id = 'cart-button'>Cart</button>"
						    +"<button type='button' id = 'checkout-button'>Checkout</button>"
						    +"<form class='form' id = 'log-out-button' action = 'ShoppingCart' method='get'>"
							+"<input name = 'logout' type='hidden' value ='true'>"
							+"<button class = 'btn' type = 'submit'>Logout</button>"
							+"</form>"
					+"</div>";
		return topOfPage;
	}
	
	private String makeTable(ArrayList<moviesInCart> previousItems){
    	
    	float total = 0;
    	String stripedTable = "<table class='table table-hover'>"
		  		+ "<thead>"
		  		+ "<tr>"
		  		+ "<th>Movie Title</th>"
		  		+ "<th>Movie ID</th>"
		  		+ "<th>Price</th>"
		  		+ "<th>Quantity</th>"
		  		+ "<th>add</th>"
		  		+ "<th>subtract</th>"
		  		+ "</tr>"
		  		+ "</thead>"
		  		+"<tbody>";
	      if (previousItems.size() == 0) {
	    	  stripedTable = "<h1>No items</h1>";
	      } else {
	        //out.println("<UL>");
	        for(moviesInCart c: previousItems) {
	          //out.println("<LI>" + (String)previousItems.get(i));
	        	total += 15.99f * c.getQuantity();
	        	stripedTable += "<tr>"
	        			+ "<td><a href ='SingleMoviePage?movieID="+ c.getmovieID() +"'>"+ c.getMovieTitle()+"</a></td>"
	        			+ "<td>"+ c.getmovieID()+ "</td>"
	        			+ "<td>"+ c.getPrice() +"</td>"
	        			+ "<td>"+ c.getQuantity()+"</td>"
	        			+ "<td>"+ "<form class='form-signin' action = 'ShoppingCart' method='get'>"
	        					+ "<input name = 'movieID' type='hidden' value =" + c.getmovieID() + ">"
	        					+ "<input name = 'adding' type='hidden' value = true>"
	        					+ "<button class = 'btn' type = 'submit'>Add</button></form>"
	        			+ "</td>"
	        			+ "<td>"+ "<form class='form-signin' action = 'ShoppingCart' method='get'>"
		    					+ "<input name = 'movieID' type='hidden' value =" + c.getmovieID()+ ">"
		    					+ "<input name = 'adding' type='hidden' value = false>"
		    					+ "<button class = 'btn' type = 'submit'>Subtract</button></form>"
    					+ "</td>"
	        			+ "</tr>";
	        }
	      }
	    setMoneyTotal(total);
    	return stripedTable +"</tbody></table>";
    }


	public float getMoneyTotal() {
		return moneyTotal;
	}

	public void setMoneyTotal(float moneyTotal) {
		this.moneyTotal = moneyTotal;
	}

}
