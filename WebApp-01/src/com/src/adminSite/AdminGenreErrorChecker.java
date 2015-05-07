package com.src.adminSite;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminGenreErrorChecker
 */
@WebServlet("/AdminGenreErrorChecker")
public class AdminGenreErrorChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminGenreErrorChecker() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println(adminTopBar.adminTopPage());
	    out.println(getGenreErrors());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	private String getGenreErrors()
	{
		String tables = "";
		tables	+= "<h2>Genres With no Movies</h2>";
		tables	+= getGenresNoMovieTable();
		tables	+= "<h2>Genres With Duplicates</h2>";
		tables	+= getDuplicateGenresTable();
		return tables;
	}
	
	private String getGenresNoMovieTable()
	{
		ArrayList<String> titles  = new ArrayList<String>();
		titles.add("Genre");
		
		String table = "";
		String sql = "SELECT G.id, G.name"
				+ " FROM moviedb.genres G LEFT JOIN moviedb.genres_in_movies GIM"
				+ " ON G.id = GIM.genre_id"
				+ " WHERE GIM.Movie_id IS NULL"
				+ " ORDER BY G.id;";
		try{		
			ResultSet	genreResult		= AdminGetConnection.getResultsOfQuery(sql);
			
			AdminResultContainer containerTemplate	= new AdminResultContainer(titles);
			ArrayList<AdminResultContainer> results	= new ArrayList<AdminResultContainer>();
			
			if (genreResult.isBeforeFirst())
			{
				while(genreResult.next()){
					//get things other than the id
					//note: there may be more thing added if we 
					//decide to show more rows than just id and genre
					ArrayList<String> data = new ArrayList<String>();
					data.add(genreResult.getString(2));//Genre
					//put the shit into a result container
					results.add(containerTemplate.createNewContainer(genreResult.getInt(1), data));
				}
				table+=AdminResultContainer.generateTableFromResultContainers(results);
			}
			else
			{
				table+="<table><th>No genres found</th></table>";
			}
			
			
		}catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		return table;
	}

	private String getDuplicateGenresTable()
	{
		ArrayList<String> titles  = new ArrayList<String>();
		titles.add("Genre");
		
		String table = "";
		String sql = "SELECT	GROUP_CONCAT(DISTINCT G1.id SEPARATOR ', '), G1.name"
				+ " FROM	(moviedb.genres G1"
				+ " INNER JOIN moviedb.genres G2" 
				+ " ON G1.name LIKE G2.name	AND G1.id != G2.id)" 
				+ "	GROUP BY G1.name"
				+ " ORDER BY G1.name;";
		try{		
			ResultSet	genreResult		= AdminGetConnection.getResultsOfQuery(sql);
			
			AdminResultContainer containerTemplate	= new AdminResultContainer(titles);
			ArrayList<AdminResultContainer> results	= new ArrayList<AdminResultContainer>();
			
			if (genreResult.isBeforeFirst())
			{
				while(genreResult.next()){
					//get things other than the id
					//note: there may be more thing added if we 
					//decide to show more rows than just id and genre
					ArrayList<String> data = new ArrayList<String>();
					data.add(genreResult.getString(2));//Genre
					//put the shit into a result container
					results.add(containerTemplate.createNewContainer(genreResult.getString(1), data));
				}
				table+=AdminResultContainer.generateTableFromResultContainers(results);
			}
			else
			{
				table+="<table><th>No genres found</th></table>";
			}
			
		}catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		return table;
	}


}
