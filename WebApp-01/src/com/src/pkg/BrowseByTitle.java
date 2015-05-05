package com.src.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

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
	private int resultsPosition = 0;
	private int resultsLimit = 10;
	private String startChar = "";
       
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
		if(request.getParameter("position")!= null)
		{
			resultsPosition = Integer.valueOf(request.getParameter("position"));
		}
		if(request.getParameter("limit")!= null)
		{
			resultsLimit = Integer.valueOf(request.getParameter("limit"));
		}
		if(request.getParameter("CreateTitleList") != null && request.getParameter("StartCharacter") != null)
		{
			startChar = (String)request.getParameter("StartCharacter");
			String sqlQuery = "SELECT MT.id, MT.title, MT.director, MT.year, group_concat(distinct G.name), group_concat(DISTINCT S.id, ':', S.first_name, ' ', S.last_name) "
					+ "FROM((SELECT * FROM moviedb.movies M ORDER BY M.id) MT "
					+ "JOIN (moviedb.genres G JOIN moviedb.genres_in_movies GIM on G.id=GIM.genre_id) ON GIM.movie_id = MT.id)"
					+ " JOIN (moviedb.stars_in_movies SIM JOIN moviedb.stars s ON SIM.star_id = s.id) ON SIM.movie_id = MT.id, "
					+ "(select count(*) as Count from moviedb.movies m) as q "
					+ " WHERE  MT.title LIKE '"
					+ startChar
					+ "%' group by MT.title "
					+ " limit "
					+ resultsLimit+1
					+ " offset "
					+ resultsPosition
					+ ";";
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
		MovieInfo mi = new MovieInfo();
		int countMovies = 0;
		String stripedTable = "<table class='table table-hover'>"
		  		+ "<thead>"
		  		+ "<tr>"
		  		+ "<th>Movie ID</th>"
		  		+ "<th>Movie Title</th>"
				+ "<th>Year</th>"
		  		+ "<th>Director</th>"
		  		+ "<th>Stars</th>"
		  		+ "<th>Genre</th>"
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
	    	  {
	    		  if(countMovies < resultsLimit)
	    		  {
		    		  mi.setMovieInfo(titles.getInt(1), titles.getString(2), "", titles.getString(3),
		    				  titles.getString(4), titles.getString(5), titles.getString(6));
		    		  stripedTable += "<tr><td>"+mi.id+"</td>"
		    				  + "<td>"
		    				  + textLinker.linkMovie(mi.title, String.valueOf(mi.id))
		    				  +"</td>"
		    				  + "<td>"+mi.year+"</td>"
		    				  + "<td>"+mi.director+"</td>";
		    		  stripedTable+="<td>"+textLinker.linkStars(mi.starsInFilm)+"</td>";
		    		  stripedTable+="<td>"+textLinker.linkGenres(mi.genres)+"</td>";
		    		  countMovies++;
		    		  mi.clearMovieInfo();
	    		  }
	    	  }
	    	  stripedTable += "</tbody></table>"
	    		+ "Limit <a href='BrowseByTitle?limit=10&position="
	    		+ resultsPosition
	    		+ "&StartCharacter="
	    		+ startChar
	    		+ "&CreateTitleList=true"
	    		+ "'>10 </a>"
	    		+ "<a href='BrowseByTitle?limit=20&position="
	    		+ resultsPosition
	    		+ "&StartCharacter="
	    		+ startChar
	    		+ "&CreateTitleList=true"
	    		+ "'>20 </a>"
	    		+ "<a href='BrowseByTitle?limit=50&position="
	    		+ resultsPosition
	    		+ "&StartCharacter="
	    		+ startChar
	    		+ "&CreateTitleList=true"
	    		+ "'>50 </a>"
	    		+ "<a href='BrowseByTitle?limit=100&position="
	    		+ resultsPosition
	    		+ "&StartCharacter="
	    		+ startChar
	    		+ "&CreateTitleList=true"
	    		+ "'>100</a>"
	    		+ "<br>";
	    	  if(resultsPosition == 0)
	    	  {
	    		  stripedTable += "<a href='BrowseByTitle?limit="
	  	    			+ resultsLimit
	  	    			+ "&position="
	  	    			+ (resultsPosition + resultsLimit)
	  	    			+ "&StartCharacter="
	  	    			+ startChar
	  	    			+ "&CreateTitleList=true"
	  	    			+ "'>Next</a>";  
	    	  }
	    	  else if(resultsLimit > countMovies)
	    	  {
	    		  stripedTable += "<a href='BrowseByTitle?limit="
		  	    			+ resultsLimit
		  	    			+ "&position="
		  	    			+ (resultsPosition - resultsLimit)
		  	    			+ "&StartCharacter="
		  	    			+ startChar
		  	    			+ "&CreateTitleList=true"
		  	    			+ "'>Prev</a>"; 
	    	  }
	    	  else
	    	  {
	    		  stripedTable += "<a href='BrowseByTitle?limit="
		  	    			+ resultsLimit
		  	    			+ "&position="
		  	    			+ (resultsPosition - resultsLimit)
		  	    			+ "&StartCharacter="
		  	    			+ startChar
	    					+ "&CreateTitleList=true"
		  	    			+ "'>Prev</a>\t"
		  	    			+ "<a href='BrowseByTitle?limit="
		  		  	    	+ resultsLimit
		  		  	    	+ "&position="
		  		  	    	+ (resultsPosition + resultsLimit)
		  		  	    	+ "&StartCharacter="
		  		  	    	+ startChar
	    					+ "&CreateTitleList=true"
		  		  	    	+ "'>Next</a>"; ; 
	    	  }
	    	
	    		
	    	stripedTable += "</BODY></HTML>";
	      }
	      return stripedTable;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String listStarsWithLinks(HashSet<StarsInfo> starsInFilm )
	{
		String stripedTable = new String();
		int position = 0;
		for (StarsInfo s : starsInFilm) {
			stripedTable+="<a href='SingleStarPage?movieID="+s.id+"'>";
			if (position != starsInFilm.size() - 1)
				stripedTable += s.first_name + " " + s.last_name +"</a>"+ ", ";
			else
				stripedTable += s.first_name + " " + s.last_name +"</a>";
			
			position++;
		}
		return stripedTable;
	}
	private String listGenresWithLinks(HashSet<String> genres)
	{
		String stripedTable = new String();
		int position = 0;
		stripedTable += "</td>" + "<td>";
		for (String g : genres) {
			stripedTable+="<a href='BrowseByGenre?CreateSpecificGenreList=true&GenreField="+g+"'>";
			if (position != genres.size() - 1) {
				stripedTable += g + ", ";
			} else {
				stripedTable += g;
			}
			stripedTable+="</a>";
			position++;
		}
		return stripedTable;
	}
	

}
