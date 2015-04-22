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

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	    HttpSession session = request.getSession();
	    ArrayList previousItems = (ArrayList)session.getAttribute("previousItems");
	    if (previousItems == null) {
	      previousItems = new ArrayList();
	      session.setAttribute("previousItems", previousItems);
	    }

	    String newItem = request.getParameter("newItem");

	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String title = "Items Purchased";
	    String docType =
	      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	      "Transitional//EN\">\n";
	    out.println("<!DOCTYPE html>"+
"<html>"+
"<head lang='en'>"+
    "<meta charset='utf-8'>"+
    "<meta http-equiv='X-UA-Compatible' content='IE=edge'>"+
    "<meta name='viewport' content='width=device-width, initial-scale=1'>"+
    "<link rel='stylesheet' type='text/css' href='topbarcss.css'>"+
    "<title>M.L.G. Videos</title>"+
"</head>"+
"<body>"+
	"<div id='header'>"+
		    "M.L.G.Videos  "+
		    "<button type='button' id ='search-button'>Main</button>"+
		    "<button type='button' id ='search-button'>Search</button>"+
		    "<button type='button' id = 'browse-button'>Browse</button>"+
		    "<button type='button' id = 'cart-button'>Cart</button>"+
		    "<button type='button' id = 'checkout-button'>Checkout</button>"+
	"</div>");


	   synchronized(previousItems) {
	      if (newItem != null) {
	        previousItems.add(newItem);
	      }
	      if (previousItems.size() == 0) {
	        out.println("<I>No items</I>");
	      } else {
	        out.println("<UL>");
	        for(int i=0; i<previousItems.size(); i++) {
	          out.println("<LI>" + (String)previousItems.get(i));
	        }
	        out.println("</UL>");
	      }
	    }

	   // The following two statements show how this thread can access an
	   // object created by a thread of the ShowSession servlet
	   // Integer accessCount = (Integer)session.getAttribute("accessCount");
	   // out.println("<p>accessCount = " + accessCount);

	   out.println("</BODY></HTML>");

		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
