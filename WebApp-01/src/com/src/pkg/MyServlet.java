package com.src.pkg;

import java.io.*;
import java.util.Properties;

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

///**
// * Servlet implementation class MyServlet
// */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {

	public MyServlet() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
		Class.forName("com.mysql.jdbc.Driver");
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
	
	private String logInCheck(String username, String password)
			throws SQLException, NamingException {
		String userID = null;
		
		Connection conn = getConnection();

		Statement loginSelect = conn.createStatement();

		ResultSet loginResult = loginSelect.executeQuery("SELECT C.id "
				+ "FROM moviedb.customers C " + "where (C.email = '" + username
				+ "'" + " and C.password = '" + password + "');");
		// conn.close();
		//System.out.println(loginResult.isBeforeFirst());
		if( loginResult.isBeforeFirst()){
			while(loginResult.next()){
				userID = loginResult.getString("id");
			}
			loginResult.close();
		}
		loginSelect.close();
		conn.close();
		return userID;
	}	

	//
	// /**
	// * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	// response)
	// */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String userID = null;
		
		try {
			userID = logInCheck(request.getParameter("Username"),request.getParameter("Password"));
			System.out.println(userID);
		} catch (SQLException | NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

			if ( userID != null) {

				session.setAttribute("userID", userID);
				String site = new String("MainPage.html");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
			} else {
				// out.println("not logged in");
				// Set response content type
				// New location to be redirected
				String site = new String("wrongPassword.html");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site);
			}

		
	}

	//
	// /**
	// * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	// response)
	// */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// PrintWriter out = response.getWriter();
		// out.println("hello post");
		doGet(request, response);
	}

	//
}
