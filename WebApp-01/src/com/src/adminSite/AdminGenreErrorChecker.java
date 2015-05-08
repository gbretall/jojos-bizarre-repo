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
       
	private static String GENRES_NO_MOVIES = ""
			+ "SELECT G.id, G.name"
			+ " FROM moviedb.genres G LEFT JOIN moviedb.genres_in_movies GIM"
			+ " ON G.id = GIM.genre_id"
			+ " WHERE GIM.Movie_id IS NULL"
			+ " ORDER BY G.id;";
	
	private static String DUPLICATE_GENRES =""
			+ "SELECT	GROUP_CONCAT(DISTINCT G1.id SEPARATOR ', '), G1.name"
			+ " FROM	(moviedb.genres G1"
			+ " INNER JOIN moviedb.genres G2" 
			+ " ON G1.name LIKE G2.name	AND G1.id != G2.id)" 
			+ "	GROUP BY G1.name"
			+ " ORDER BY G1.name;";
	
	private static String NO_GENRES_FOUND ="<table><th>No genres found</th></table>";
	
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
		ArrayList<String> titles  = new ArrayList<String>();
		titles.add("Genre");
		
		String tables = "";
		tables	+= "<h2>Genres With no Movies</h2>";
		tables	+= AdminErrorChecker.createTableFromQuery(titles, GENRES_NO_MOVIES, NO_GENRES_FOUND);
		tables	+= "<h2>Genres With Duplicates</h2>";
		tables	+= AdminErrorChecker.createTableFromQuery(titles, DUPLICATE_GENRES, NO_GENRES_FOUND);
		return tables;
	}
	

}
