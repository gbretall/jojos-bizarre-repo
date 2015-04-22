package com.src.pkg;
import java.util.HashSet;

public class MovieInfo 
{
	int id;
	String title;
	String banner_url;
	String director;
	String year;
	HashSet<StarsInfo> starsInFilm = new HashSet<StarsInfo>();
	HashSet<String> genres = new HashSet<String>();
	
	public MovieInfo(int id, String title, String banner_url, String director, String year)
	{
		this.id = id;
		this.title = title;
		this.banner_url = banner_url;
		this.director = director;
		this.year = year;
	}
	
	public boolean addStar(StarsInfo s)
	{
		return starsInFilm.add(s);
	}
	
	public boolean addGenre(String g)
	{
		return genres.add(g);
	}
	

}
