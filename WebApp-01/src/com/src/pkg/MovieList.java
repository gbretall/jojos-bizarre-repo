package com.src.pkg;

import java.io.*;

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
 * Servlet implementation class MovieList
 */
@WebServlet("/MovieList")
public class MovieList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String moviesTable;
	private int resultsLimit = 10;
	private int resultsPosition = 1;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieList() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String makeTable(SearchTableSort movies, int sortMode,
			boolean titleReverse, boolean yearReverse) {

		String stripedTable = "<table class='table table-hover'>"
				+ "<thead>"
				+ "<tr>"
				+ "<th>Movie ID</th>"
				+ "<form class='form-signin' action = 'MovieList' method='post'>"
				+ "<input name = 'SortByTitle' type='hidden' value = true>"
				+ "<th><button class = 'btn' type = 'submit'>Movie Title</button></th></form>"
				+ "<form class='form-signin' action = 'MovieList' method='post'>"
				+ "<input name = 'SortByYear' type='hidden' value = true>"
				+ "<th><button class = 'btn' type = 'submit'>Year</button></th></form>"
				+ "<th>Director</th>" + "<th>Stars</th>" + "<th>Genres</th>"
				+ "</tr>" + "</thead>" + "<tbody>";
		if (movies == null) {
			stripedTable = "<h1>No items</h1>";
		} else {
			// out.println("<UL>");
			switch (sortMode) {
			case 1:
				stripedTable+=populateMovieTable(movies);
				break;
			case 2:
				movies.sortByTitle(titleReverse);
				stripedTable+=populateMovieTable(movies);
				break;
			default:
				movies.sortByYear(yearReverse);
				stripedTable+=populateMovieTable(movies);
				break;
			}
		}
		stripedTable += "</tbody></table>"
				+ "Limit <a href='MovieList?limit=10&position="
				+ resultsPosition
				+ "'>10 </a>"
				+ "<a href='MovieList?limit=20&position="
				+ resultsPosition
				+ "'>20 </a>"
				+ "<a href='MovieList?limit=50&position="
				+ resultsPosition
				+ "'>50 </a>"
				+ "<a href='MovieList?limit=100&position="
				+ resultsPosition
				+ "'>100</a>"
				+ "<br>";
		for(int i = 0; i < (movies.SearchIndex.size()/resultsLimit)+1; i++)
		{
			if(i == resultsPosition-1)
			{
				stripedTable += (i+1) + " ";
			}
			else
			{
				stripedTable += "<a href='MovieList?position="+(i+1)+"&limit="
						+ resultsLimit
						+ "'>"+(i+1)+" "+"</a>";
			}
		}
				
				return stripedTable + "</BODY></HTML>";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		SearchTableSort movies = (SearchTableSort) session
				.getAttribute("movies");
		if(request.getParameter("limit")!= null)
		{
			resultsLimit = Integer.parseInt(request.getParameter("limit"));
		}
		if(request.getParameter("position")!= null)
		{
			resultsPosition = Integer.parseInt(request.getParameter("position"));
		}
		PrintWriter out = response.getWriter();
		int sortMode = 1;
		if (request.getParameter("SortByTitle") != null) {
			sortMode = 2;
			session.setAttribute("titleReverse",
					!(boolean) session.getAttribute("titleReverse"));
		}
		if (request.getParameter("SortByYear") != null) {
			sortMode = 3;
			session.setAttribute("yearReverse",
					!(boolean) session.getAttribute("yearReverse"));
		}
		out.println(AddTopBar.makeTopOfPage());
		out.println(makeTable(movies, sortMode,
				(boolean) session.getAttribute("titleReverse"),
				(boolean) session.getAttribute("yearReverse")));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String populateMovieTable( SearchTableSort movies) {
		String stripedTable = new String();
		System.out.println((resultsPosition * resultsLimit) + " " + movies.SearchIndex.size());
		if(movies.SearchIndex.size() > (resultsLimit*resultsPosition))
		{
			
			for (int i = ((resultsPosition-1)*resultsLimit); i < (resultsLimit*resultsPosition); i++) 
			{
				//System.out.println(i);
				MovieInfo currentMovie = movies.moviesTable.get(Integer
						.valueOf(movies.SearchIndex.get(i).get(0)));
				System.out.println(currentMovie);
				stripedTable += "<tr>" + "<td>" + Integer.toString(currentMovie.id)+ "</td>" 
						+ "<td>"
						+ textLinker.linkMovie(currentMovie.title, String.valueOf(currentMovie.id))
						+ "</td>" 
						+ "<td>"
						+ currentMovie.year + "</td>" + "<td>"
						+ currentMovie.director + "</td>" + "<td>";
				stripedTable+=textLinker.linkStars(currentMovie.starsInFilm);
				stripedTable+=textLinker.linkGenres(currentMovie.genres);
				stripedTable += "</td>" + "</tr>";
			}
		}
		else
		{
			for (int i = (resultsLimit*(resultsPosition-1)); i < movies.SearchIndex.size(); i++) {
				MovieInfo currentMovie = movies.moviesTable.get(Integer
						.valueOf(movies.SearchIndex.get(i).get(0)));
				//System.out.println(currentMovie);
				stripedTable += "<tr>" + "<td>" + Integer.toString(currentMovie.id)+ "</td>" 
						+ "<td>"
						+ textLinker.linkMovie(currentMovie.title, String.valueOf(currentMovie.id))
						+ "</td>" 
						+ "<td>"
						+ currentMovie.year + "</td>" + "<td>"
						+ currentMovie.director + "</td>" + "<td>";
				stripedTable+=textLinker.linkStars(currentMovie.starsInFilm);
				stripedTable+=textLinker.linkGenres(currentMovie.genres);
				stripedTable += "</td>" + "</tr>";
			}
		}
		return stripedTable;
	}

	
}
