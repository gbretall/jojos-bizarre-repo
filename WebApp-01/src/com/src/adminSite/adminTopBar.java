package com.src.adminSite;

public class adminTopBar {

	public adminTopBar(){
		
	}
	
	public static String adminTopPage(){
		String topPage =  "<!DOCTYPE html>"
				+"<html>"
				+"<head lang='en'>"
				+"<meta charset='utf-8'>"
			    +"<meta http-equiv='X-UA-Compatible' content='IE=edge'>"
			    +"<meta name='viewport' content='width=device-width, initial-scale=1'>"
			    + "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>"
			    +"<link rel='stylesheet' type='text/css' href='adminStuff/admin.css'>"
			    +"<title >M.L.G. Videos admin page</title>"
			    +"</head>"
			    +"<body>"
				+"<div id='header'>"
				+"<p id = 'admin-title'>M.L.G.Videos Admin Page. ((Normies Not Welcome))</p> "
				+ "</div>";
		return topPage;
	}
	
}
