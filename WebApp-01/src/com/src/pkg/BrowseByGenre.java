package com.src.pkg;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
import java.util.*;

/**
 * Servlet implementation class BrowseByGenre
 */
@WebServlet("/BrowseByGenre")
public class BrowseByGenre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseByGenre() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Connection getConnection() throws SQLException, NamingException {
		Context initCtx = new InitialContext();
		if (initCtx == null)
			System.out.println("initCtx is NULL");

		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		if (envCtx == null)
			System.out.println("envCtx is NULL");

		// Look up our data source
		DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");

		if (ds == null)
			System.out.println("ds is null.");
		return ds.getConnection();
	}
    
    private ResultSet performSearchQuery(String query) throws SQLException, NamingException
    {
		conn = getConnection();
		Statement dbSearch = conn.createStatement();
		ResultSet searchResult = dbSearch.executeQuery(query);
    	return searchResult;
    }
    
    private String createGenreList(ResultSet genreList) throws SQLException
    {
    	String stripedTable = "";
    	stripedTable += "<table class='table table-hover'>";
    	if(!genreList.isBeforeFirst())
    	{
    		return "<h>Something clearly went wrong here</h>";
    	}
    	while(genreList.next())
    	{
    			stripedTable += "<form class='form-signin' action = 'BrowseByGenre' method='post'>"
						+"<input name = 'CreateSpecificGenreList' type='hidden' value=true>"
    					+"<input name ='GenreField' type='hidden' value='" + genreList.getString(1)
						+"'>"
						+ "<tr><button class = 'btn' name = 'GenreButton' type = 'submit'>"
						+ genreList.getString(1)
						+"</button></tr></form>";
    	}
    	return stripedTable + "</table></BODY></HTML>";
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String genresQuery;
		if(request.getParameter("CreateSpecificGenreList") != null)
		{
			System.out.println((String)request.getParameter("GenreField"));
			genresQuery = "SELECT MT.id, MT.title, MT.year, MT.director, group_concat(distinct G.name), group_concat(DISTINCT S.first_name, ' ', S.last_name)"
					+ " FROM((SELECT * FROM moviedb.movies M ORDER BY M.id) MT "
					+ "JOIN (moviedb.genres G JOIN moviedb.genres_in_movies GIM on G.id=GIM.genre_id) ON GIM.movie_id=MT.id)"
					+ " JOIN (moviedb.stars_in_movies SIM JOIN moviedb.stars s ON SIM.star_id = s.id) ON SIM.movie_id = MT.id "
					+ " WHERE G.name = '"
					+ (String)request.getParameter("GenreField")
					+ "'group by MT.id;";
			System.out.println(genresQuery);
			try {
				ResultSet genreList = performSearchQuery(genresQuery);
				out.println(AddTopBar.makeTopOfPage());
				out.println(createMoviesInGenre(genreList));
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			genresQuery = "SELECT DISTINCT g.name FROM moviedb.genres g ORDER BY g.name";
			try {
				ResultSet genreList = performSearchQuery(genresQuery);
				out.println(AddTopBar.makeTopOfPage());
				out.println(createGenreList(genreList));
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String createMoviesInGenre(ResultSet genreList) throws SQLException 
	{
		String stripedTable = "<table class='table table-hover'>"
		  		+ "<thead>"
		  		+ "<tr>"
		  		+ "<th>Movie ID</th>"
		  		+ "<th>Movie Title</th>"
				+ "<th>Year</th>"
		  		+ "<th>Director</th>"
		  		+ "<th>Stars</th>"
		  		+ "</tr>"
		  		+ "</thead>"
		  		+"<tbody>";
	      if(!genreList.isBeforeFirst())
	      {
	    	  return stripedTable += "<br><h>No movies in this genre</h></BODY></HTML>";
	      }
	      else
	      {
	    	  while(genreList.next())
	    		  stripedTable += "<tr><td>"+genreList.getInt(1)+"</td>"
	    				  + "<td>"+genreList.getString(2)+"</td>"
	    				  + "<td>"+genreList.getString(3)+"</td>"
	    				  + "<td>"+genreList.getString(4)+"</td>"
	    				  + "<td>"+genreList.getString(6)+"</td>";
	    	  stripedTable += "</tbody></table></BODY></HTML>";
	      }
	      return stripedTable;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
