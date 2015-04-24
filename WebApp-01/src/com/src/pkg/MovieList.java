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
				// for(int i=0; i<movies.SearchIndex.size(); i++)
				// {
				// MovieInfo currentMovie =
				// movies.moviesTable.get(Integer.valueOf(movies.SearchIndex.get(i).get(0)));
				// System.out.println(currentMovie);
				// stripedTable += "<tr>"
				// + "<td>" + Integer.toString(currentMovie.id) + "</td>"
				// + "<td>" + currentMovie.title + "</td>"
				// + "<td>" + currentMovie.year + "</td>"
				// + "<td>"+ currentMovie.director + "</td>"
				// +"<td>";
				// int position = 0;
				// for(StarsInfo s: currentMovie.starsInFilm)
				// {
				// if(position != currentMovie.starsInFilm.size()-1)
				// stripedTable += s.first_name + " " + s.last_name + ", ";
				// else
				// stripedTable += s.first_name + " " + s.last_name;
				// position++;
				// }
				// position = 0;
				// stripedTable += "</td>"
				// + "<td>";
				// for(String g: currentMovie.genres)
				// {
				// if(position!=currentMovie.genres.size()-1)
				// {
				// stripedTable += g + ", ";
				// }
				// else
				// {
				// stripedTable += g;
				// }
				// position++;
				// }
				// stripedTable += "</td>"
				// +"</tr>";
				// }
				stripedTable+=populateMovieTable(movies);
				break;
			case 2:
				movies.sortByTitle(titleReverse);
//				for (int i = 0; i < movies.SearchIndex.size(); i++) {
//					MovieInfo currentMovie = movies.moviesTable.get(Integer
//							.valueOf(movies.SearchIndex.get(i).get(0)));
//					System.out.println(currentMovie);
//					stripedTable += "<tr>" + "<td>"
//							+ Integer.toString(currentMovie.id) + "</td>"
//							+ "<td>" + currentMovie.title + "</td>" + "<td>"
//							+ currentMovie.year + "</td>" + "<td>"
//							+ currentMovie.director + "</td>" + "<td>";
//					int position = 0;
//					for (StarsInfo s : currentMovie.starsInFilm) {
//						if (position != currentMovie.starsInFilm.size() - 1)
//							stripedTable += s.first_name + " " + s.last_name
//									+ ", ";
//						else
//							stripedTable += s.first_name + " " + s.last_name;
//						position++;
//					}
//					position = 0;
//					stripedTable += "</td>" + "<td>";
//					for (String g : currentMovie.genres) {
//						if (position != currentMovie.genres.size() - 1) {
//							stripedTable += g + ", ";
//						} else {
//							stripedTable += g;
//						}
//						position++;
//					}
//					stripedTable += "</td>" + "</tr>";			
//				}
				stripedTable+=populateMovieTable(movies);
				break;
			default:
				movies.sortByYear(yearReverse);
//				for (int i = 0; i < movies.SearchIndex.size(); i++) {
//					MovieInfo currentMovie = movies.moviesTable.get(Integer
//							.valueOf(movies.SearchIndex.get(i).get(0)));
//					System.out.println(currentMovie);
//					stripedTable += "<tr>" + "<td>"
//							+ Integer.toString(currentMovie.id) + "</td>"
//							+ "<td>" + currentMovie.title + "</td>" + "<td>"
//							+ currentMovie.year + "</td>" + "<td>"
//							+ currentMovie.director + "</td>" + "<td>";
//					int position = 0;
//					for (StarsInfo s : currentMovie.starsInFilm) {
//						if (position != currentMovie.starsInFilm.size() - 1)
//							stripedTable += s.first_name + " " + s.last_name
//									+ ", ";
//						else
//							stripedTable += s.first_name + " " + s.last_name;
//						position++;
//					}
//					position = 0;
//					stripedTable += "</td>" + "<td>";
//					for (String g : currentMovie.genres) {
//						if (position != currentMovie.genres.size() - 1) {
//							stripedTable += g + ", ";
//						} else {
//							stripedTable += g;
//						}
//						position++;
//					}
//					stripedTable += "</td>" + "</tr>";
//				}
				stripedTable+=populateMovieTable(movies);
				break;
			}
		}
		return stripedTable + "</tbody></table></BODY></HTML>";
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
		for (int i = 0; i < movies.SearchIndex.size(); i++) {
			MovieInfo currentMovie = movies.moviesTable.get(Integer
					.valueOf(movies.SearchIndex.get(i).get(0)));
			System.out.println(currentMovie);
			stripedTable += "<tr>" + "<td>" + Integer.toString(currentMovie.id)+ "</td>" 
					+ "<td><a href='SingleMoviePage?movieID="+currentMovie.id+"'>" + currentMovie.title + "</a></td>" + "<td>"
					+ currentMovie.year + "</td>" + "<td>"
					+ currentMovie.director + "</td>" + "<td>";
			int position = 0;
			for (StarsInfo s : currentMovie.starsInFilm) {
				stripedTable+="<a href='SingleStarPage?movieID="+s.id+"'>";
				if (position != currentMovie.starsInFilm.size() - 1)
					stripedTable += s.first_name + " " + s.last_name +"</a>"+ ", ";
				else
					stripedTable += s.first_name + " " + s.last_name +"</a>";
				
				position++;
			}
			position = 0;
			stripedTable += "</td>" + "<td>";
			for (String g : currentMovie.genres) {
				if (position != currentMovie.genres.size() - 1) {
					stripedTable += g + ", ";
				} else {
					stripedTable += g;
				}
				position++;
			}
			stripedTable += "</td>" + "</tr>";
		}
		return stripedTable;
	}

}
