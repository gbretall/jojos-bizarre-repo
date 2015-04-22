package com.src.pkg;
import java.util.HashSet;

public class MovieInfo 
{
	int id;
	String title;
	String banner_url;
	String trailer_url;
	String director;
	String year;
	HashSet<StarsInfo> starsInFilm = new HashSet<StarsInfo>();
	HashSet<String> genres = new HashSet<String>();
	
	public MovieInfo(int id, String title, String banner_url, String trailer_url, String director, String year)
	{
		this.id = id;
		this.title = title;
		this.banner_url = banner_url;
		this.trailer_url = trailer_url;
		this.director = director;
		this.year = year;
	}
	
	public boolean addStar(StarsInfo s)
	{
		return starsInFilm.add(s);
	}
	
	public boolean genres(String g)
	{
		return genres.add(g);
	}
	

}
