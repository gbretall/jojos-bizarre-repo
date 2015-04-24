package com.src.pkg;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;

public class SearchTableSort {
	public HashMap<Integer, MovieInfo> moviesTable = new HashMap<Integer, MovieInfo>();
	public ArrayList<searchIndex> SearchIndex = new ArrayList<searchIndex>();
	
	public SearchTableSort()
	{
	
		System.out.println("SearchTableSort is made");
	}
	
	public void addIndex(int id, String year, String title)
	{
		SearchIndex.add(new searchIndex(new Integer(id).toString(), title, year));
	}
	
	public void add(int id, String title,String year, String director, String banner_url,
							String genreInfoConcat, String starInfoConcat )
	{
		//System.out.println("Add is called");
		//check if id is in the hash map
		//if yes, then add genre and star info to movie
		MovieInfo mi; 
		if (moviesTable.containsKey(id))
		{
			//System.out.println("value was in hashmap");
			mi= moviesTable.get(id);
		}
		else
		//if not, then add the info to the index and create the movie object
		{
			//System.out.println("not in set");
			mi = new MovieInfo(id, title, banner_url, director, year);
			//System.out.println("MovieInfo: "+ id + " "+ title+ " "+ banner_url+ " "
				//	+ director +" "+ year);
		}
		mi.parseStarsResults(starInfoConcat);
		mi.parseGenreResults(genreInfoConcat);
		moviesTable.put(id, mi);
		addIndex(id, year, title);
	}
	
	public void sortByYear(boolean reverse)
	{
		Collections.sort(SearchIndex, searchIndex.yearComparator);
		if(reverse)
			Collections.reverse(SearchIndex);
	}
	
	public void sortByTitle(boolean reverse)
	{
		Collections.sort(SearchIndex, searchIndex.titleComparator);
		if(reverse)
			Collections.reverse(SearchIndex);
	}
	
	

}
