package com.src.pkg;

import handler.sql.GetConnection;
import handler.sql.ReturnJasonObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReturnQuery
 */
@WebServlet("/ReturnQuery")
public class ReturnQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @see HttpServlet#HttpServlet()
     */
    public ReturnQuery() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        super();
		Class.forName("com.mysql.jdbc.Driver").newInstance();;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ReturnQuery update: 2");
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
	
		ReturnJasonObject jason= new ReturnJasonObject();
		
		String testTitle = request.getParameter("testTitle");
		System.out.println(testTitle);
//		
		try {
			if (testTitle == null){
			out.write(jason.returnJason("good"));
			}
			else{
				out.write(jason.returnJason(testTitle));
			}
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
