package com.src.pkg;
import java.util.Collections;
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

	public MovieInfo()
	{
		
	}
	public MovieInfo(int id, String title, String banner_url, String director, String year, String genreInfoConcat, String starInfoConcat)
	{
		setMovieInfo(id, title, banner_url, director, year, genreInfoConcat, starInfoConcat);
	}
	
	public boolean addStar(StarsInfo s)
	{
		return starsInFilm.add(s);
	}
	
	public boolean addGenre(String g)
	{
		return genres.add(g);
	}

	public void parseStarsResults(String starInfoConcat)
	{
		System.out.println(starInfoConcat);
		String[] stars = starInfoConcat.split(",");
		for (int i = 0 ; i<stars.length; ++i)
		{
			starsInFilm.add(new StarsInfo(stars[i]));
		}
		
	}
	public void parseGenreResults(String genreInfoConcat)
	{
		String[] genreStrings = genreInfoConcat.split(",");
		Collections.addAll(genres, 	genreStrings);
		
	}
	public void setMovieInfo(int id, String title, String banner_url, String director, String year, String genreInfoConcat, String starInfoConcat)
	{
		this.id = id;
		this.title = title;
		this.banner_url = banner_url;
		this.director = director;
		this.year = year;
		parseGenreResults(genreInfoConcat);
		parseStarsResults(starInfoConcat);
	}

	public void clearMovieInfo()
	{
		this.id = 0;
		this.title = "";
		this.banner_url = "";
		this. director  = "";
		this.year = "";
		this.genres.clear();
		this.starsInFilm.clear();
	}
}
