package com.src.pkg;

public class AddTopBar {
	public AddTopBar() {

	}

	public static String makeTopOfPage() {
		String topOfPage = "<!DOCTYPE html>"
				+ "<head lang='en'>"
				+ "<meta charset='utf-8'>"
				+ "<meta http-equiv='X-UA-Compatible' content='IE=edge'>"

				+ "<meta name='viewport' content='width=device-width, initial-scale=1'>"
				+ "<script src='resources/external/jquery/jquery.js'></script>"
				+ "<script src='resources/jquery-ui.min.js'></script>"

				+ "<script type='text/javascript' src='resources/searchAutocomplete.js'></script>"

				+ "<!-- Latest compiled and minified CSS -->"
				+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>"

				+ "<!-- Optional theme -->"
				+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css'>"

				+ "<!-- Latest compiled and minified JavaScript -->"
				+ "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js'></script>"
				+ "<link rel= 'stylesheet' type='text/css' href='index.css' >"
				+ "<link rel = 'stylesheet' type='text/css' href='resources/jquery-ui.css'>"

				+ "<link rel='stylesheet' type='text/css' href='topbarcss.css'>"

				+ "<title>M.L.G. Videos</title>"
				+ "</head>"
				+ "<body>"
				+ "<div id='header'>"
				+ "M.L.G.Videos  "
				+ "<form class='form' id = 'main-button' action = 'MainPage.html' method='get'>"
				+ "<button type='submit' id ='main-button'>Main</button>"
				+ "</form>"

				+ "<form class='form' id = 'search-button' action = 'search.html' method='get'>"
				+ "<button type='submit' id ='search-button'>Search</button>"
				+ "</form>"

				+ "<form class='form' id = 'browse-button' action = 'Browse.html' method='get'>"
				+ "<button type='submit' id = 'browse-button'>Browse</button>"
				+ "</form>"

				+ "<form class='form' id = 'cart-button' action = 'ShoppingCart'>"
				+ "<button type='submit' id = 'cart-button'>Cart</button>"
				+ "</form>"

				+ "<form class='form' id = 'checkout-button' action = 'ShoppingCart' method='get'>"
				+ "<button type='submit' id = 'checkout-button'>Checkout</button>"
				+ "</form>"

				+ "<form class='form' id = 'log-out-button' action = 'LogOutServlet' method='get'>"
				+ "<input name = 'logout' type='hidden' value ='true'>"
				+ "<button class = 'btn' type = 'submit'>Logout</button>"
				+ "</form>"

				+ "<div id ='form-quick-search'>"
				+ "<form action='SingleMoviePage' method='get'>"
				+ "<input id='movie' type = 'movie'"
				+ "class='ui-widget ui-widget-content ui-corner-all' required='' placeholder='Search...'>"
				+ "<input type='hidden' id = 'hidden-movie-id' name = 'movieID' value=''>"
				+ "<button class='btn btn-primary ' id='quick-search-button'"
				+ "type='submit'>search</button>" 
				+ "</form>"
				+ "</div>"
				+"</div>";
		return topOfPage;
	}

}
