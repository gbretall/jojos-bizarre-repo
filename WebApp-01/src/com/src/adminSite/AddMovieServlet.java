package com.src.adminSite;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Servlet implementation class AddMovieServlet
 */
@WebServlet("/AddMovieServlet")
public class AddMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private String addMovieToDatabase(String title, String year,
			String director, String banner_url, String trailer_url,
			String first_name, String last_name, String DOB, String photo_url, String genre) throws SQLException, NamingException{
		
    	AdminSQLHandler connections = null;
    	String sqlQuery = "CALL add_movie('"
				+ title
				+ "', "
				+ year
				+ ", '"
				+ director
				+ "', '"
				+ banner_url
				+ "', '"
				+ trailer_url
				+ "', '"
				+ first_name
				+ "', '"
				+ last_name
				+ "', '"
				+ DOB
				+"', '"
				+ photo_url
				+ "', '"
				+ genre
				+"');";
		try
		{
			connections = new AdminSQLHandler();
			connections.query(sqlQuery);
		}
		catch(SQLException e)
		{
			if (connections!= null){connections.close();}
			return "<div><h2>Could not add movie to the database</h2>"
					+ "<br><br>"
					+ "<a href='AddMovieForm.html'>Return to Add Movie Page</a></div></body></html>";
		}
		if(connections!=null){connections.close();}
		return "<div><h2>"
				+ title
				+ " was successfully added to the database</h2>"
				+ "<br><br>"
				+ "<a href='AddMovieForm.html'>Return to Add Movie Page</a></div></body></html>";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("Movie Title");
		String year = request.getParameter("Movie Year");
		String director = request.getParameter("Movie Director");
		String banner_url = request.getParameter("Movie Banner URL");
		String trailer_url = request.getParameter("Movie Trailer URL");
		String first_name = request.getParameter("Star First Name");
		String last_name = request.getParameter("Star Last Name");
		String DOB = request.getParameter("Star DOB");
		String photo_url = request.getParameter("Star Photo URL");
		String genre = request.getParameter("Movie Genre");

		out.println(adminTopBar.adminTopPage());
		try {
			out.println(addMovieToDatabase(title, year, director, banner_url, trailer_url, first_name, last_name, DOB, photo_url, genre));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
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
