package com.src.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
 * Servlet implementation class BrowseByTitle
 */
@WebServlet("/BrowseByTitle")
public class BrowseByTitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseByTitle() {
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
    
    private String createAlphabet() throws SQLException
    {
    	String stripedTable = "";
    	stripedTable += "<table class='table table-hover'>";
    	for(int i = 0; i < 36; i++)
    	{
    		char currentChar = 0;
    		if(i < 10)
    		{
    			currentChar = (char) (48+i);
    			
    		}
    		else
    		{
    			currentChar = (char) (55+i);
    		}
    		stripedTable += "<form class='form-signin' action = 'BrowseByTitle' method='post'>"
					+"<input name = 'CreateTitleList' type='hidden' value=true>"
					+"<input name ='StartCharacter' type='hidden' value='" + currentChar
					+"'>"
					+ "<tr><button class = 'btn' name = 'GenreButton' type = 'submit'>"
					+ currentChar
					+"</button></tr></form>";
    	}
    	return stripedTable + "</table></BODY></HTML>";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if(request.getParameter("CreateTitleList") != null)
		{
			String sqlQuery = "SELECT MT.id, MT.title, MT.year, MT.director, group_concat(distinct G.name), group_concat(DISTINCT S.first_name, ' ', S.last_name)"
					+ " FROM((SELECT * FROM moviedb.movies M ORDER BY M.id) MT "
					+ "JOIN (moviedb.genres G JOIN moviedb.genres_in_movies GIM on G.id=GIM.genre_id) ON GIM.movie_id=MT.id)"
					+ " JOIN (moviedb.stars_in_movies SIM JOIN moviedb.stars s ON SIM.star_id = s.id) ON SIM.movie_id = MT.id "
					+ " WHERE  MT.title LIKE '"
					+ (String)request.getParameter("StartCharacter")
					+ "%'group by MT.title;";
				try {
					ResultSet titles = performSearchQuery(sqlQuery);
					out.println(AddTopBar.makeTopOfPage());
					out.println(createMoviesByAlpha(titles));
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
			try {
				out.println(createAlphabet());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String createMoviesByAlpha(ResultSet titles) throws SQLException {
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
	      if(!titles.isBeforeFirst())
	      {
	    	  return stripedTable += "<br><h>No movies in this genre</h></BODY></HTML>";
	      }
	      else
	      {
	    	  while(titles.next())
	    		  stripedTable += "<tr><td>"+titles.getInt(1)+"</td>"
	    				  + "<td>"+titles.getString(2)+"</td>"
	    				  + "<td>"+titles.getString(3)+"</td>"
	    				  + "<td>"+titles.getString(4)+"</td>"
	    				  + "<td>"+titles.getString(6)+"</td>";
	    	  stripedTable += "</tbody></table></BODY></HTML>";
	      }
	      return stripedTable;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
