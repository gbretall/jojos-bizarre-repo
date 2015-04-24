package com.src.pkg;

import java.util.ArrayList;

public class StarsInfo {
	int id;
	String first_name;
	String last_name;
	
	public StarsInfo(int id, String first_name, String last_name)
	{
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	public  StarsInfo(String starInfo)
	{
		parseStarInfo(starInfo);		
	}
	public void parseStarInfo(String starInfo)
	{
		System.out.println("##"+starInfo);
		String[] tokens = starInfo.split(":|\\s");
		System.out.println("->"+tokens[0]+tokens[1]+tokens[2]);
		if (tokens.length>2)
		{
			this.first_name = tokens[1];
			this.last_name = tokens[2];
		}
		else if (tokens.length==2)
		{
			this.first_name="";
			this.last_name= tokens[1];
		}
		this.id = Integer.parseInt(tokens[0]);	
	}
	
}
