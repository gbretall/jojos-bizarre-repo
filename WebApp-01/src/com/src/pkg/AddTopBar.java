package com.src.pkg;

public class AddTopBar {
	public AddTopBar()
	{
		
	}
	
	public static String makeTopOfPage(){
		String topOfPage= "<!DOCTYPE html>"
				+"<html>"
				+"<head lang='en'>"
				+"<meta charset='utf-8'>"
			    +"<meta http-equiv='X-UA-Compatible' content='IE=edge'>"
			    +"<meta name='viewport' content='width=device-width, initial-scale=1'>"
			    + "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>"
			    +"<link rel='stylesheet' type='text/css' href='topbarcss.css'>"
			    +"<title>M.L.G. Videos</title>"
			    +"</head>"
			    +"<body>"
				+"<div id='header'>"
				+"M.L.G.Videos  "
				+"<button type='button' id ='main-button'>Main</button>"
				+"<button type='button' id ='search-button'>Search</button>"
				+"<button type='button' id = 'browse-button'>Browse</button>"
				+"<button type='button' id = 'cart-button'>Cart</button>"
				+"<button type='button' id = 'checkout-button'>Checkout</button>"
				+"<form class='form' id = 'log-out-button' action = 'ShoppingCart' method='get'>"
				+"<input name = 'logout' type='hidden' value ='true'>"
				+"<button class = 'btn' type = 'submit'>Logout</button>"
				+"</form>"
				+"</div>";
	return topOfPage;
}

}
