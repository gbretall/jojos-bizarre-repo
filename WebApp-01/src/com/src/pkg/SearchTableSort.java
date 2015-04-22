package com.src.pkg;
import java.util.HashMap;
import java.util.ArrayList;

public class SearchTableSort {
	public HashMap<Integer, MovieInfo> moviesTable = new HashMap<Integer, MovieInfo>();
	public ArrayList<ArrayList<String>> searchIndex = new ArrayList<ArrayList<String>>();
	
	public SearchTableSort()
	{
	
		System.out.println("SearchTableSort is made");
	}
	
	public void addIndex(int id, String year, String title)
	{
		ArrayList<String> toAdd = new ArrayList<String>();
		toAdd.add(new Integer(id).toString());
		toAdd.add(title);
		toAdd.add(year);
		searchIndex.add(toAdd);
	}
	
	public void add(int id, String title,String year, String director, String banner_url,
							String genre, int sid, String first_name, String last_name )
	{
		//System.out.println("Add is called");
		//check if id is in the hash map
		//if yes, then add genre and star info to movie
		if (moviesTable.containsKey(id))
		{
			//System.out.println("value was in hashmap");
			MovieInfo mi = moviesTable.get(id);
			mi.addStar(new StarsInfo(sid,first_name, last_name));
			mi.addGenre(genre);
			moviesTable.put(id, mi);
		}
		else
		//if not, then add the info to the index and create the movie object
		{
			//System.out.println("not in set");
			MovieInfo mi = new MovieInfo(id, title, banner_url, director, year);
			//System.out.println("MovieInfo: "+ id + " "+ title+ " "+ banner_url+ " "
				//	+ director +" "+ year);
			mi.addGenre(genre);
			mi.addStar(new StarsInfo(sid,first_name, last_name));
			moviesTable.put(id, mi);
			addIndex(id, year, title);
		}		
	}
	

}
