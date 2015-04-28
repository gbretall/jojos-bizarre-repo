package com.src.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.src.pkg.ShoppingCart.moviesInCart;

/**
 * Servlet implementation class LogOutServlet
 */
@WebServlet("/LogOutServlet")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
	    if(request.getParameter("logout") != null) {  
		    System.out.println(request.getParameter("logout"));
		    session.invalidate();
		    out.println(AddTopBar.makeTopOfPage());
		    out.println("<h1>Thank you for Shopping with us!</h1>");
		    out.println("<h1>You have been logged out</h1>");
		    out.println("The home page can be found <a href='' target='_top'>here!</a>");
		    out.println("<img src='MCHammer.gif' alt='HTML tutorial' style='width:80px;height:80px;border:0'/>");
	    }
	    
	    if(request.getParameter("checkout") !=null){
		    session.invalidate();
		    out.println(AddTopBar.makeTopOfPage());
		    out.println("<h1>Your Order has Been logged!</h1>");
		    out.println("<h1>You have been logged out</h1>");
		    out.println("The home page can be found <a href='' target='_top'>here!</a>");
		    
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
