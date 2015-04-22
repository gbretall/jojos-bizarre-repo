package com.src.pkg;

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
	public boolean equals(StarsInfo S)
	{
		return (S.id==id && S.first_name.equals(first_name) && S.last_name.equals(last_name));
	}
	
}
