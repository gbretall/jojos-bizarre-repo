package com.src.adminSite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

public class AdminErrorChecker {

	public static String createTableFromQuery(ArrayList<String> titles, String SQLQuery, String noneFound)
	{
		String table = "";
		try{		
			AdminSQLHandler connections = new AdminSQLHandler();
			ResultSet	starResult		= connections.query(SQLQuery);
			
			AdminResultContainer containerTemplate	= new AdminResultContainer(titles);
			ArrayList<AdminResultContainer> results	= new ArrayList<AdminResultContainer>();
			
			if (starResult.isBeforeFirst())
			{
				while(starResult.next()){
					//get things other than the id depending on the number of titles
					ArrayList<String> data = new ArrayList<String>();
					for(int i = 2;i<=titles.size()+1;++i)
					{
						data.add(starResult.getString(i));
					}
					results.add(containerTemplate.createNewContainer(starResult.getString(1), data));
				}
				table += AdminResultContainer.generateTableFromResultContainers(results);
			}
			else
			{
				//print the error message
				table += noneFound;
			}
			connections.close();
		}catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		return table;
	}
	
}
