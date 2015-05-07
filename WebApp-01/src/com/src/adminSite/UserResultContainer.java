package com.src.adminSite;

import java.util.ArrayList;
/*initial intended flow
	-create blank container with just the column titles specified
	-create new objects as needed with the create new container method
*/
public class UserResultContainer extends AdminResultContainer{
	public  String			id;
	public	ArrayList<String>	columnTitles;
	public	ArrayList<String>	columns;
	
	public UserResultContainer(ArrayList<String> columnTitles, String iD2, ArrayList<String> columns2) 
	{
		super(columnTitles, iD2, columns2);
		// TODO Auto-generated constructor stub
	}
	
	public UserResultContainer(ArrayList<String> columnTitles)
	{
		super(columnTitles);
	}
	
	public String toRowHTML()
	/***
	 * returns <tr>(<td>[row column]</td>)*</tr>
	 */
	{
		String row = "<tr><td>"+id+"</td>";
		for(String col: columns)
		{
			row+="<td><a href='grantPriveliges?username="+col+"'>"+col+"</a></td>";
		}
		return row+"</tr>";
	}
	
	public static String generateTableFromUserResultContainers(ArrayList<UserResultContainer> results)
	{
		String table = "<table class='table table-hover'>";
		table+=results.get(0).titleRowHTML()+"<tbody>";
		for (UserResultContainer result:results)
		{
			table+=result.toRowHTML();
		}		
		return table+"</tbody></table>";
	}
	
	public UserResultContainer createNewContainer(String ID, ArrayList<String> columns)
	{
		return new UserResultContainer(this.columnTitles, ID, columns);
	}
	
	
	
}
