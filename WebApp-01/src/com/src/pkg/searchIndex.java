package com.src.pkg;
import java.util.ArrayList;
import java.util.Comparator;

public class searchIndex implements Comparator<searchIndex>{
	String id;
	String title;
	String year;
	
	public searchIndex(String id, String title, String year)
	{
		this.id = id;
		this.title = title;
		this.year = year;
	}
	
	public String get(int index)
	{
		switch(index)
		{
		case 0:
			return id;
		case 1:
			return title;
		default:
			return year;
		}
	}
	
	public static final Comparator<searchIndex> yearComparator = new Comparator<searchIndex>()
	{
		public int compare(searchIndex s1, searchIndex s2)
		{
			return s1.year.compareTo(s2.year);
		}
	};
	
	public static final Comparator<searchIndex> titleComparator = new Comparator<searchIndex>()
			{
				@Override
				public int compare(searchIndex o1, searchIndex o2) {
					return o1.title.compareTo(o2.title);
				}
			};

	@Override
	public int compare(searchIndex o1, searchIndex o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

}
