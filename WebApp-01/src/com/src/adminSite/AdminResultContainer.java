package com.src.adminSite;

import java.util.ArrayList;
/*initial intended flow
	-create blank container with just the column titles specified
	-create new objects as needed with the create new container method
*/
public class AdminResultContainer {
	public  String			id;
	public	ArrayList<String>	columnTitles;
	public	ArrayList<String>	columns;
	
	public AdminResultContainer(ArrayList<String> columnTitles)
	{
		this.columnTitles = columnTitles;
	}
	
	public AdminResultContainer(ArrayList<String> columnTitles, Integer ID, ArrayList<String>columns)
	{
		this.columnTitles = columnTitles;
		addInfo(ID.toString(), columns);
	}
	public AdminResultContainer(ArrayList<String> columnTitles, String ID, ArrayList<String>columns)
	{
		this.columnTitles = columnTitles;
		addInfo(ID, columns);
	}
	
	public void addInfo(String ID, ArrayList<String>columns)
	{
		this.id=ID;
		this.columns = columns;
	}
	
	//This creates a new container with the column titles the same
	public AdminResultContainer createNewContainer(Integer ID, ArrayList<String> columns)
	{
		return new AdminResultContainer(this.columnTitles, ID, columns);
	}
	
	public AdminResultContainer createNewContainer(String ID, ArrayList<String> columns)
	{
		return new AdminResultContainer(this.columnTitles, ID, columns);
	}
	public String titleRowHTML()
	/***
	 * returns <tr>(<td>[title column]</td>)*</tr>
	 */
	{
		String titleRow = "<thead><tr><th>ID</th>";
		for (String title:columnTitles)
		{
			titleRow += "<th>"+title+"</th>";
		}
		return titleRow+"</tr></thead>";
	}
	
	public String toRowHTML()
	/***
	 * returns <tr>(<td>[row column]</td>)*</tr>
	 */
	{
		String row = "<tr><td>"+id+"</td>";
		for(String col: columns)
		{
			row+="<td>"+col+"</td>";
		}
		return row+"</tr>";
	}

	public static String generateTableFromResultContainers(ArrayList<AdminResultContainer> results)
	{
		String table = "<div class = 'container'>"
				+ "<table class='table table-hover table-striped'>";
		table+=results.get(0).titleRowHTML()+"<tbody>";
		for (AdminResultContainer result:results)
		{
			table+=result.toRowHTML();
		}		
		return table+"</tbody></table></div>";
	}
}
