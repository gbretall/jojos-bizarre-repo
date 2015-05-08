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
 * Servlet implementation class AdminStarErrorChecker
 */
@WebServlet("/AdminStarErrorChecker")
public class AdminStarErrorChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String STARS_NO_MOVIES ="" 
			+"SELECT S.id, S.first_name, S.last_name"
			+" FROM moviedb.stars S LEFT JOIN moviedb.stars_in_movies SIM"
			+" ON S.id = SIM.star_id"
			+" WHERE SIM.star_id IS NULL"
			+" ORDER BY S.last_name, S.first_name;";
	
	private static String STARS_NO_FIRST_LAST =""
			+"SELECT S.id, S.first_name, S.last_name"
			+" FROM moviedb.stars S"
			+" WHERE S.last_name = \"\" OR S.first_name = \"\""
			+" ORDER BY S.last_name, S.first_name;";
	
	private static String STARS_DUPLICATES =""
			+"SELECT	GROUP_CONCAT(DISTINCT S1.id SEPARATOR ', '), S1.first_name, S1.last_name, S1.DOB"
			+" FROM	(moviedb.stars S1 INNER JOIN moviedb.stars S2"
			+" ON S1.first_name LIKE S2.first_name" 
			+" AND S1.last_name LIKE S2.last_name"
			+" AND S1.id != S2.id"
			+" AND S1.DOB = S2.DOB)"
			+" GROUP BY  S1.first_name, S1.last_name, S1.DOB"
			+" ORDER BY S1.last_name, S1.first_name;";
	
	private static String STARS_TOO_YOUNG ="SELECT S.id, S.first_name, S.last_name, S.DOB"
			+" FROM moviedb.stars	S"
			+" WHERE S.dob>curdate()"
			+" ORDER BY S.last_name, S.last_name;";
	
	private static String NO_STARS_FOUND = "<table><th>No stars found</th></table>";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminStarErrorChecker() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println(adminTopBar.adminTopPage());
	    out.println(getStarErrors());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	private String getStarErrors()
	{
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("First Name");
		titles.add("Last Name");
		String tables = "";
		tables	+= "<h2>Stars With No Movies</h2>";		
		tables	+= AdminErrorChecker.createTableFromQuery(titles, STARS_NO_MOVIES, NO_STARS_FOUND);
		tables	+= "<h2>Stars With No First Name Or No Last Name</h2>";
		tables	+= AdminErrorChecker.createTableFromQuery(titles, STARS_NO_FIRST_LAST, NO_STARS_FOUND);
		tables	+= "<h2>Stars That Have Duplicates</h2>";
		tables	+= AdminErrorChecker.createTableFromQuery(titles, STARS_DUPLICATES, NO_STARS_FOUND);
		tables	+= "<h2>Stars Born After the Current Date</h2>";
		tables	+= AdminErrorChecker.createTableFromQuery(titles, STARS_TOO_YOUNG, NO_STARS_FOUND);
		
		return tables;
	}
		
	
}
