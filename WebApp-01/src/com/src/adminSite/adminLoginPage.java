package com.src.adminSite;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * Servlet implementation class adminLoginPage
 */
@WebServlet("/adminLoginPage")
public class adminLoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminLoginPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws NamingException 
	 * @throws SQLException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private String getEmployeeEmail(String email, String password){
    	String employeeEmail = null;
    	
    	String sql = ""
    			+ "SELECT E.employee_email "
    			+ "FROM moviedb.employees E "
    			+ "where E.employee_email = '"+email+"' "
    			+ "and E.employee_password = '"+password+"';";
    	
    	try {
			Connection conn = AdminGetConnection.getConnection();
			
			Statement emailStatement = conn.createStatement();
			
			ResultSet emailResult = emailStatement.executeQuery(sql);
			
			if( emailResult.isBeforeFirst()){
				while(emailResult.next()){
					employeeEmail = emailResult.getString("employee_email");
				}
				emailResult.close();
			}
			emailStatement.close();
			conn.close();			
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return employeeEmail;
    }
    public String makeLoginForm(){
    	String form =""
    			+ "<div class='container'>"
    			+ "<form class='form-signin' action = 'adminLoginPage' method='post'>"
    			+ "<h2 class='form-signin-heading'>Please sign in</h2>"
    			+ "<label for='inputEmail' class='sr-only'></label>"
    			+ "<input type='email' id='inputEmail' class='form-control' placeholder='Email address' required='' autofocus='' name ='Username'>"
    			+ "<label for='inputPassword' class='sr-only'></label>"
    			+ "<input type='password' id='inputPassword' class='form-control' placeholder='Password' required='' name = 'Password'>"
    			+ "<button class='btn btn-lg btn-primary btn-block' id = 'signInButton' type='submit'>Sign in</button>"
    			+ "</form>"
    			+ "</div>";
    	return form;
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println(adminTopBar.adminTopPage());
		out.println(makeLoginForm());
		
		String submittedEmail = request.getParameter("Username");
		String submittedPassword = request.getParameter("Password");
		String employeeEmail = null;
		
		if( submittedEmail != null && submittedPassword != null){
			System.out.println("From admin site: email: " + submittedEmail + " password: "+ submittedPassword);
			System.out.println("button hit. I'll do things later");
			employeeEmail = getEmployeeEmail(submittedEmail, submittedPassword);
			System.out.println(employeeEmail);
			
			if(employeeEmail != null){
				response.sendRedirect("AdminMainPage.html");
				System.out.println("login is good");
			}
			else{
				System.out.println("login was no good");
			}
		}
		
		HttpSession session = request.getSession();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
