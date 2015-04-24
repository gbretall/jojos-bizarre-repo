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

import com.src.pkg.SingleMoviePage.SingleMovie;

/**
 * Servlet implementation class SingleStarPage
 */
@WebServlet("/SingleStarPage")
public class SingleStarPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleStarPage() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public class SingleStar{
    	private String starID;
    	private String name;
    	private String birthday;
    	private String photo_url;
    	private Set<String> movieSet;
    	
    	public SingleStar(String starID, String name, String birthday, String photo_url, Set<String> movieSet){
    		this.setStarID(starID);
    		this.setName(name);
    		this.setBirthday(birthday);
    		this.photo_url = photo_url;
    		this.setMovieSet(movieSet);
    	}    	
		public String getStarID() {
			return starID;
		}
		public void setStarID(String starID) {
			this.starID = starID;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getPhoto_url()
		{
			return photo_url;
		}
		public String setPhoto_url(String url)
		{
			return photo_url = url;
		}
		public Set<String> getMovieSet() {
			return movieSet;
		}
		public void setMovieSet(Set<String> movieSet) {
			this.movieSet = movieSet;
		}
    }
    
    private Set<String> getMoviesOfStar(String starID) throws SQLException
    {
    	Statement movieSetQuery = conn.createStatement();
		String query = "SELECT GROUP_CONCAT(DISTINCT SiM.movie_id, ':', M.title) as movies "  
				+"FROM ((moviedb.stars S "
				+"inner join moviedb.stars_in_movies SiM "
				+"on  S.id = SiM.star_id) inner join moviedb.movies M on SiM.movie_id = M.id) "
				+"where S.id = "
				+ starID
				+" group by S.id;";
		ResultSet moviesResults = movieSetQuery.executeQuery(query);
		HashSet<String> moviesFeaturingStar = new HashSet<String>();
		if(!moviesResults.isBeforeFirst())
		{
			moviesResults.close();
			return null;
		}
		while(moviesResults.next())
		{
			String moviesList = moviesResults.getString(1);
			for(String movie: moviesList.split(","))
			{
				moviesFeaturingStar.add(movie);
			}
			
		}
		moviesResults.close();
		return moviesFeaturingStar;
    }
    
    private SingleStar getStarInfo(String starID) throws SQLException
    {
    	Statement movieQuery = conn.createStatement();
		String query = "SELECT * FROM moviedb.stars WHERE id = "
				+ starID 
				+ ";";
		ResultSet starInfo = movieQuery.executeQuery(query);
		if(!starInfo.isBeforeFirst())
		{
			starInfo.close();
			return null;
		}
		SingleStar newStar = null;
		while(starInfo.next())
			newStar = new SingleStar(new Integer(starInfo.getInt(1)).toString(), starInfo.getString(2) + " " + starInfo.getString(3), 
					starInfo.getString(4), starInfo.getString(5), getMoviesOfStar(starID));
		starInfo.close();
		return newStar;
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
		
		String starID = request.getParameter("starID");
		
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println(AddTopBar.makeTopOfPage());
	    
		try {
			conn = getConnection();
			SingleStar starOnThisPage = getStarInfo(starID);
			out.println(makeStarPage(starOnThisPage));
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    if(starID != null){
	    	System.out.println("printing from SingleMoviePage: "+starID);
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

	private String makeStarPage(SingleStar starOnThisPage) 
	{
		String pageContent = "";
		pageContent += "<div id='poster'><img src='"
		+ starOnThisPage.getPhoto_url() 
		+ "'></div>"
		+ "<table class='table table-hover'>"
		+ "<thead>"
		+ "<tr>"
		+ "<td><b>"
		+ starOnThisPage.getName()
		+ "</b></td></tr></thead>"
		+ "<tbody><tr><td>Born: "
		+ starOnThisPage.getBirthday()
		+ " ID: "
		+ starOnThisPage.getStarID()
		+ "</td></tr>"
		+ "<tr><td>";
		int position = 0;
		for(String movie: starOnThisPage.getMovieSet())
		{
			String[] movieParts = movie.split(":");
			//System.out.println(movieParts[1]);
			if(position != starOnThisPage.getMovieSet().size()-1)
			{
				pageContent += "<a href='SingleMoviePage?movieID="
						+ movieParts[0]
						+ "'>"
						+ movieParts[1] + ", "
						+ " </a>";
				position++;
			}
			else
			{
				pageContent += "<a href='SingleMoviePage?movieID="
						+ movieParts[0]
						+ "'>"
						+ movieParts[1]
						+ " </a>";
				position++;
			}
		}
		pageContent += "</td></tr></tbody></table></BODY></HTML>";
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
