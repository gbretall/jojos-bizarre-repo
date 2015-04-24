package com.src.pkg;

import java.util.HashSet;

public class textLinker {
	public textLinker()
	{}
	public static String linkStars(HashSet<StarsInfo> starsInFilm )
	{
		String stripedTable = new String();
		int position = 0;
		for (StarsInfo s : starsInFilm) {
			stripedTable+="<a href='SingleStarPage?starID="+s.id+"'>";
			if (position != starsInFilm.size() - 1)
				stripedTable += s.first_name + " " + s.last_name +"</a>"+ ", ";
			else
				stripedTable += s.first_name + " " + s.last_name +"</a>";
			
			position++;
		}
		return stripedTable;
	}
	
	public static String linkGenres(HashSet<String> genres)
	{
		String stripedTable = new String();
		int position = 0;
		stripedTable += "</td>" + "<td>";
		for (String g : genres) {
			stripedTable+="<a href='BrowseByGenre?CreateSpecificGenreList=true&GenreField="+g+"'>";
			if (position != genres.size() - 1) {
				stripedTable += g + "</a>"+ ", ";
			} else {
				stripedTable += g + "</a>";
			}
			position++;
		}
		return stripedTable;
	}
	public static String linkMovie(String movieName, String id)
	{
		return "<a href='SingleMoviePage?movieID="+id+"'>"+movieName+"</a>";
	}
}
