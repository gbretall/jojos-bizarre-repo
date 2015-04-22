package com.src.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private class moviesInCart{
		private String movieTitle = "";
		private float price = 0;
		private int quantity = 0;
		
    	public moviesInCart(String movieTitle, float price, int quantity){
    		this.setMovieTitle(movieTitle);
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
    	
    	
    }
    
    private float moneyTotal = 0;

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	    HttpSession session = request.getSession();
	    ArrayList previousItems = (ArrayList)session.getAttribute("previousItems");
	    Set<String> movieId = (Set<String>)session.getAttribute("movieId");
	    
	    if (previousItems == null) {
	      previousItems = new ArrayList();
	      session.setAttribute("previousItems", previousItems);
	    }
	    
	    if (movieId == null) {
		      movieId = new HashSet<String>();
		      session.setAttribute("movieId", movieId);
		}

	    String newItem = request.getParameter("newItem");
	    //moviesInCart newMovie = new moviesInCart(request.getParameter("movieId"), 15.99f, 0);
	    moviesInCart newMovie = new moviesInCart("blank", 15.99f, 0);
	    
	    
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
	    
	    if(request.getParameter("moveId") != null && request.getParameter("adding") != null){
	    	if(request.getParameter("adding").equals("true")){
	    		
	    		System.out.println("adding movie "+ request.getParameter("moveId"));
//	    		previousItems.add(request.getParameter("moveId"));
	    	}
	    	
	    	if(request.getParameter("adding").equals("false")){
	    		System.out.println("deleting movie " + request.getParameter("moveId"));
//	    		previousxItems.remove(request.getParameter("moveId"));
	    	}
	    }
		if (newItem != null)
		{
		    previousItems.add(newItem);
		}

	   // The following two statements show how this thread can access an
	   // object created by a thread of the ShowSession servlet
	   // Integer accessCount = (Integer)session.getAttribute("accessCount");
	   // out.println("<p>accessCount = " + accessCount);
	   out.println(makeTable(previousItems));
	   NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
	   
	   out.println("<h1> Grand total: "+defaultFormat.format(getMoneyTotal())+"</h1>");
	   out.println("</BODY></HTML>");
		
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
	
	private String makeTable(ArrayList previousItems){
    	
    	float total = 0;
    	String stripedTable = "<table class='table table-hover'>"
		  		+ "<thead>"
		  		+ "<tr>"
		  		+ "<th>Movie Title</th>"
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
	        for(int i=0; i<previousItems.size(); i++) {
	          //out.println("<LI>" + (String)previousItems.get(i));
	        	total += 15.99f;
	        	stripedTable += "<tr>"
	        			+ "<td>"+ "title"+"</td>"
	        			+ "<td>"+ "$15.99"+"</td>"
	        			+ "<td>"+ "quant"+"</td>"
	        			+ "<td>"+ "<form class='form-signin' action = 'ShoppingCart' method='get'>"
	        					+ "<input name = 'moveId' type='hidden' value =" + ' ' + ">"
	        					+ "<input name = 'adding' type='hidden' value = true>"
	        					+ "<button class = 'btn' type = 'submit'>Add</button></form>"
	        			+ "</td>"
	        			+ "<td>"+ "<form class='form-signin' action = 'ShoppingCart' method='get'>"
		    					+ "<input name = 'moveId' type='hidden' value =" + ' ' + ">"
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
