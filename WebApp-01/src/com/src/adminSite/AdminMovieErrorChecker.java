package com.src.adminSite;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class AdminMovieErrorChecker
 */
@WebServlet("/AdminMovieErrorChecker")
public class AdminMovieErrorChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static String MOVIES_NO_STAR = ""
			+ "SELECT M.id, M.title"
			+" FROM moviedb.movies M LEFT JOIN moviedb.stars_in_movies SIM"
			+" ON M.id = SIM.movie_id"
			+" WHERE SIM.Movie_id IS NULL";
	
	private static String MOVIES_NO_GENRE =""
			+ "SELECT M.id, M.title"
			+" FROM moviedb.movies M LEFT JOIN moviedb.genres_in_movies GIM"
			+" ON M.id = GIM.movie_id"
			+" WHERE GIM.genre_id IS NULL";
	
	private static String MOVIES_DUPLICATES = ""
			+ "SELECT	GROUP_CONCAT( DISTINCT M1.id SEPARATOR ', '), M1.title, M1.year"
			+" FROM	(moviedb.movies M1 INNER JOIN moviedb.movies M2" 
			+" ON M1.title LIKE M2.title AND M1.year=M2.year AND M1.id != M2.id )" 
			+" GROUP BY  M1.title;";
	
	private static String TOO_OLD_MOVIES = ""
			+ "SELECT M.id, M.title, M.year"
			+" FROM moviedb.movies M"
			+" WHERE M.year < 1890;";
	
	private static String NO_MOVIES_FOUND =  "<table><th>No movies found</th></table>";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMovieErrorChecker() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println(adminTopBar.adminTopPage());
	    out.println(getMovieErrors());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	private String getMovieErrors()
	{
		ArrayList<String> titles  = new ArrayList<String>();
		titles.add("Title");
		
		String tables = "";
		tables	+="<h2>Movies With no Star</h2>";
		tables	+= AdminErrorChecker.createTableFromQuery(titles, MOVIES_NO_STAR,	NO_MOVIES_FOUND);
		tables	+="<h2>Movies With no Genre</h2>";
		tables	+= AdminErrorChecker.createTableFromQuery(titles, MOVIES_NO_GENRE,	NO_MOVIES_FOUND);
		
		//add another row to the tables
		titles.add("Year");
		tables	+="<h2>Movies That Have Duplicates</h2>";
		tables	+= AdminErrorChecker.createTableFromQuery(titles, MOVIES_DUPLICATES,NO_MOVIES_FOUND);
		tables	+="<h2>Movies From Before 1890</h2>";
		tables	+= AdminErrorChecker.createTableFromQuery(titles, TOO_OLD_MOVIES,	NO_MOVIES_FOUND);
		return tables;
	}
	
	private String getMoviesNoStarTable()
	{
		ArrayList<String> titles  = new ArrayList<String>();
		titles.add("Title");
		
		String table = "";
		String sql = "SELECT M.id, M.title"
				+" FROM moviedb.movies M LEFT JOIN moviedb.stars_in_movies SIM"
				+" ON M.id = SIM.movie_id"
				+" WHERE SIM.Movie_id IS NULL";
		try{		
			ResultSet	movieResult		= AdminGetConnection.getResultsOfQuery(sql);
			
			
			AdminResultContainer containerTemplate	= new AdminResultContainer(titles);
			ArrayList<AdminResultContainer> results	= new ArrayList<AdminResultContainer>();
			
			if (movieResult.isBeforeFirst())
			{
				while(movieResult.next()){
					//get things other than the id
					//note: there may be more thing added if we 
					//decide to show more rows than just id and title
					ArrayList<String> data = new ArrayList<String>();
					data.add(movieResult.getString(2));
					//put the shit into a result container
					results.add(containerTemplate.createNewContainer(movieResult.getInt(1), data));
				}
				table+=AdminResultContainer.generateTableFromResultContainers(results);
			}
			else
			{
				table+="<table><th>No movies found</th></table>";
			}
			
			
		}catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		return table;
	}
	
	private String getMoviesNoGenreTable()
	{
		ArrayList<String> titles  = new ArrayList<String>();
		titles.add("Title");
		
		String table = "";
		String sql = "SELECT M.id, M.title"
				+" FROM moviedb.movies M LEFT JOIN moviedb.genres_in_movies GIM"
				+" ON M.id = GIM.movie_id"
				+" WHERE GIM.genre_id IS NULL";
		try{		
			ResultSet	movieResult		= AdminGetConnection.getResultsOfQuery(sql);
			
			AdminResultContainer containerTemplate	= new AdminResultContainer(titles);
			ArrayList<AdminResultContainer> results	= new ArrayList<AdminResultContainer>();
			
			if (movieResult.isBeforeFirst())
			{
				while(movieResult.next()){
					//get things other than the id
					//note: there may be more thing added if we 
					//decide to show more rows than just id and title
					ArrayList<String> data = new ArrayList<String>();
					data.add(movieResult.getString(2));
					//put the shit into a result container
					results.add(containerTemplate.createNewContainer(movieResult.getInt(1), data));
				}
				table+=AdminResultContainer.generateTableFromResultContainers(results);
			}
			else
			{
				table+="<table><th>No movies found</th></table>";
			}
			
		}catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		return table;
	}
	
	private String getDuplicateMoviesTable()
	{
		ArrayList<String> titles  = new ArrayList<String>();
		
		titles.add("Title");
		titles.add("Year");
		
		String table = "";
		String sql = "SELECT	GROUP_CONCAT( DISTINCT M1.id SEPARATOR ', '), M1.title, M1.year"
				+" FROM	(moviedb.movies M1 INNER JOIN moviedb.movies M2" 
				+" ON M1.title LIKE M2.title AND M1.year=M2.year AND M1.id != M2.id )" 
				+" GROUP BY  M1.title;";
		try{		
			ResultSet	movieResult		= AdminGetConnection.getResultsOfQuery(sql);
			
			AdminResultContainer containerTemplate	= new AdminResultContainer(titles);
			ArrayList<AdminResultContainer> results	= new ArrayList<AdminResultContainer>();
			
			if (movieResult.isBeforeFirst())
			{
				while(movieResult.next()){
					//get things other than the id
					//note: there may be more thing added if we 
					//decide to show more rows than just id, title, and year
					ArrayList<String> data = new ArrayList<String>();
					data.add(movieResult.getString(2));//title
					data.add(movieResult.getString(3));//year
					//put the shit into a result container
					results.add(containerTemplate.createNewContainer(movieResult.getString(1), data));
				}
				table+=AdminResultContainer.generateTableFromResultContainers(results);
			}
			else
			{
				table+="<table><th>No duplicate movies found</th></table>";
			}
			
			
		}catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		return table;
	}
	
	private String getTooOldMoviesTable()
	{
		ArrayList<String> titles  = new ArrayList<String>();
		titles.add("Title");
		titles.add("Year");
		
		String table = "";
		String sql = "SELECT M.id, M.title, M.year"
				+" FROM moviedb.movies M"
				+" WHERE M.year < 1890;";
		try{		
			ResultSet	movieResult		= AdminGetConnection.getResultsOfQuery(sql);
			
			AdminResultContainer containerTemplate	= new AdminResultContainer(titles);
			ArrayList<AdminResultContainer> results	= new ArrayList<AdminResultContainer>();
			
			if (movieResult.isBeforeFirst())
			{
				while(movieResult.next()){
					//get things other than the id
					//note: there may be more thing added if we 
					//decide to show more rows than just id, title, and year
					ArrayList<String> data = new ArrayList<String>();
					data.add(movieResult.getString(2));//title
					data.add(movieResult.getString(3));//year
					//put the shit into a result container
					results.add(containerTemplate.createNewContainer(movieResult.getInt(1), data));
				}
				table+=AdminResultContainer.generateTableFromResultContainers(results);
			}
			else
			{
				table+="<table><th>No movies found before 1890</th></table>";
			}
			
			
		}catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		return table;
	}
	
	

}
