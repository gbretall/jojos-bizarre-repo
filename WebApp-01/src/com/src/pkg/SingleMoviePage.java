package com.src.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class singleMoviePage
 */
@WebServlet("/SingleMoviePage")
public class SingleMoviePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleMoviePage() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public class SingleMovie{
    	private String title;
    	private String year;
    	private String director;
    	private String movie_id;
    	private String price;
    	private Set<String> stars;
    	private Set<String> genre;
    	
    	public SingleMovie(String title, String year, String director, String movie_id,
    			String price, Set<String> stars, Set<String> genre){
    		this.setTitle(title);
    		this.setYear(year);
    		this.setDirector(director);
    		this.setMovie_id(movie_id);
    		this.setPrice(price);
    		this.setStars(stars);
    		this.setGenre(genre);
    	}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getDirector() {
			return director;
		}
		public void setDirector(String director) {
			this.director = director;
		}
		public String getMovie_id() {
			return movie_id;
		}
		public void setMovie_id(String movie_id) {
			this.movie_id = movie_id;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public Set<String> getStars() {
			return stars;
		}
		public void setStars(Set<String> stars) {
			this.stars = stars;
		}
		public Set<String> getGenre() {
			return genre;
		}
		public void setGenre(Set<String> genre) {
			this.genre = genre;
		}
    	
    }
    
	
    private Set<String> getSetOfGenres(String movieID){
    	return null;
    }
	
	private Set<String> getSetOfStars(String movieID){
		return null;
	}
	
	private SingleMovie createMovieObjectFromQuery(String movieID){
		return null;
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
		
		String movieID = request.getParameter("movieID");

	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println(makeTopOfPage());
		
		try {
			conn = getConnection();
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		createMovieObjectFromQuery(movieID);
		

	    
	    if(movieID != null){
	    	System.out.println("printing from SingleMoviePage: "+movieID);
	    };
	    
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

}
