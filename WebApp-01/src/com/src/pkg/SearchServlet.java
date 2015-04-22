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
 * Servlet implementation class SearchServlet
 */
@WebServlet(description = "It searches with all the power of Web 1.0!", urlPatterns = { "/SearchServlet" })
public class SearchServlet extends HttpServlet {
	
	/*public class SearchResults
	{
		String year = 
	}*/
	private static final long serialVersionUID = 1L;
    private Connection conn = null;
	
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
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private ResultSet performSearchQuery(String query) throws SQLException, NamingException
    {
		conn = getConnection();
		Statement dbSearch = conn.createStatement();
		ResultSet searchResult = dbSearch.executeQuery(query);
    	return searchResult;
    }
    
    private SearchTableSort createMoviesTable(ResultSet results) throws SQLException
    {
    	if(!results.isBeforeFirst())
    	{   
    		System.out.println("No movies found by those parameters");
    		return null;
    	}
    	else
    	{
    		SearchTableSort movies = new SearchTableSort();
    		while(results.next())
    		{
    			movies.add(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5), results.getString(6), results.getInt(7), results.getString(8), results.getString(9));
    		}
    		for(int i = 0; i < movies.searchIndex.size(); i++)
    		{
    			int index = Integer.valueOf(movies.searchIndex.get(i).get(0));
    			System.out.println("line 82= " +index);
    			System.out.print(movies.moviesTable.get(index).id + " ");
    			for(StarsInfo si: movies.moviesTable.get(index).starsInFilm)
    			{
    				System.out.print(si.first_name + " " + si.last_name + " ");
    			}
    			for(String g: movies.moviesTable.get(index).genres)
    			{
    				System.out.print(g + " ");
    			}
    			System.out.println();
    		}
    		return movies;
    	}
    }
    



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		boolean firstStatement = true;
		HttpSession session = request.getSession();
		String searchQuery = "SELECT MD.id, MD.title, MD.year, MD.director, MD.banner_url, G.name, S.id, S.first_name, S.last_name "
				+ "FROM ((SELECT * FROM moviedb.movies M ";
		if(request.getParameter("TitleCheck")!=null)
		{
			if(firstStatement)
			{
				searchQuery += "WHERE M.title LIKE '%"+request.getParameter("Title") + "%'";
				firstStatement = false;
			}
		}
	
		if(request.getParameter("YearCheck")!=null)
		{
			if(firstStatement)
			{
				searchQuery += "WHERE M.year LIKE '%"+request.getParameter("Year") +"%'";
				firstStatement = false;
			}
			else
			{
				searchQuery += " AND M.year LIKE '%"+request.getParameter("Year")+"%'";
			}
		}
		if(request.getParameter("DirectorCheck")!=null)
		{
			if(firstStatement)
			{
				searchQuery += "WHERE M.director LIKE '%"+request.getParameter("Director") + "%'";
				firstStatement = false;
			}
			else
			{
				searchQuery += " AND M.director LIKE '%"+request.getParameter("Director") +"%'";
			}
		}
		firstStatement = true;
		searchQuery += "ORDER BY M.id) MD "
				+ "JOIN (moviedb.genres G JOIN moviedb.genres_in_movies GIM ON G.id=GIM.genre_id)  ON GIM.movie_id = MD.id) "
				+ "JOIN (moviedb.stars_in_movies SIM JOIN moviedb.stars S ON SIM.star_id = S.id ) ON SIM.movie_id = MD.id ";
		if(request.getParameter("fNameCheck")!=null)
		{
			if(firstStatement)
			{
				searchQuery += " WHERE S.first_name LIKE '%"+request.getParameter("f_name") +"%'";
				
				firstStatement = false;
			}
		}
		if(request.getParameter("lNameCheck")!=null)
		{
			if(firstStatement)
			{
				searchQuery += " WHERE S.last_name LIKE '"+request.getParameter("l_name") + "%'";
				firstStatement = false;
			}
			else
			{
				searchQuery += " AND S.last_name LIKE '"+request.getParameter("l_name") + "%'";
			}
		}
		
		searchQuery += ";";
		try
		{
			System.out.println(searchQuery);
			ResultSet searchResults = performSearchQuery(searchQuery);
			SearchTableSort movies = createMoviesTable(searchResults);
			session.setAttribute("movies", movies);
			ServletContext context = this.getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/MovieList");

			// change your request and response accordingly

			dispatcher.forward(request, response);
			//getServletContext().getRequestDispatcher("MovieList").forward(request,response);
			/*out.println(makeTopOfPage());
			out.println(makeTable(movies));
			out.println("</BODY></HTML>");*/
			
		}
		catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
