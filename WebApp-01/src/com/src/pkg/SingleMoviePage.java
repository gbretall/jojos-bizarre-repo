package com.src.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    	private String price = "15.99";
    	private String banner_url;
    	private String trailer_url;
    	private Set<String> stars;
    	private Set<String> genre;
    	
    	public SingleMovie(String movie_id, String title, String year, String director, String banner_url, 
    			String trailer_url, Set<String> stars, Set<String> genre){
    		this.setMovie_id(movie_id);
    		this.setTitle(title);
    		this.setYear(year);
    		this.setDirector(director);
    		this.banner_url = banner_url;
    		this.trailer_url = trailer_url;
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
		public String getBanner_url()
		{
			return banner_url;
		}
		public void setBanner_url(String banner)
		{
			banner_url = banner;
		}
		public String getTrailer_url()
		{
			return trailer_url;
		}
		public void setTrailer_url(String trailer)
		{
			trailer_url = trailer;
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
    
	
    private Set<String> getSetOfGenres(String movieID) throws SQLException
    {
    	Statement genreSetQuery = conn.createStatement();
    	String query = "SELECT GROUP_CONCAT(DISTINCT G.Name) as genres"
    			+ "FROM moviedb.genres_in_movies GiM"
    			+"inner join moviedb.genres G"
    			+"on G.id = GiM.genre_id"
    			+"where movie_id = '"
    			+ movieID
    			+"' group by movie_id;";
    	ResultSet genreResult = genreSetQuery.executeQuery(query);
    	HashSet<String> genreSet = new HashSet<String>();
    	if(!genreResult.isBeforeFirst())
    		return null;
    	while(genreResult.next())
    	{
    		genreSet.add(genreResult.getString(1));
    	}
    	return genreSet;
    }
	
	private Set<String> getSetOfStars(String movieID) throws SQLException
	{
		Statement starSetQuery = conn.createStatement();
		String query = "select GROUP_CONCAT(DISTINCT S.first_name, ' ', S.last_name) as stars" 
				+"from ((moviedb.movies M"
				+"inner join moviedb.stars_in_movies SiM"
				+"on  M.id = SiM.movie_id) INNER JOIN moviedb.stars S ON SiM.star_id = S.id)" 
				+"where M.id = '"
				+ movieID
				+"' group by M.id;";
		ResultSet starsResults = starSetQuery.executeQuery(query);
		HashSet<String> starsInFilm = new HashSet<String>();
		if(!starsResults.isBeforeFirst())
		{
			return null;
		}
		while(starsResults.next())
		{
			starsInFilm.add(starsResults.getString(1));
		}
		return starsInFilm;
	}
	
	private SingleMovie createMovieObjectFromQuery(String movieID) throws SQLException
	{
		Statement movieQuery = conn.createStatement();
		String query = "SELECT * FROM moviedb.movies WHERE id = "
				+ movieID 
				+ "';";
		ResultSet movieInfo = movieQuery.executeQuery(query);
		if(!movieInfo.isBeforeFirst())
			return null;
		SingleMovie newMovie = new SingleMovie(new Integer(movieInfo.getInt(1)).toString(), movieInfo.getString(2), 
				movieInfo.getString(3), movieInfo.getString(4), movieInfo.getString(5), movieInfo.getString(6), 
				getSetOfStars(movieID), getSetOfGenres(movieID));
		return newMovie;
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
			
		try {
			SingleMovie movieOnThisPage = createMovieObjectFromQuery(movieID);
			out.println(createMoviePage(movieOnThisPage));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	    
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

	private String createMoviePage(SingleMovie movieOnThisPage) 
	{
		String pageContent = "";
		pageContent += "<div id='poster'><img src='"
		+ movieOnThisPage.getBanner_url() 
		+ "'></div>"
		+ "<table class='table table-hover'>"
		+ "<thead>"
		+ "<tr>"
		+ "<td><b>"
		+ movieOnThisPage.getTitle()
		+ "</b></td></tr></thead>"
		+ "<tbody><tr><td>Year: "
		+ movieOnThisPage.getYear()
		+ " Director: "
		+ movieOnThisPage.getDirector()
		+ " ID: "
		+ movieOnThisPage.getMovie_id()
		+ "</td></tr>"
		+ "<tr><td>"
		+ "<a href='"
		+ movieOnThisPage.getTrailer_url()
		+ "'>Trailer For This Film</a></td></tr></tbody></BODY></HTML>";
		return pageContent;
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
