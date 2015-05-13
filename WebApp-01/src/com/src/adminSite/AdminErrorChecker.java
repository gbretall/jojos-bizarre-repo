package com.src.adminSite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

public class AdminErrorChecker {

	public static String createTableFromQuery(ArrayList<String> titles, String SQLQuery, String noneFound)
	{
		String table = "";
		AdminSQLHandler connections = null;
		try{		
			connections = new AdminSQLHandler();
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
			//standard flow closes the connections after it is done with it
			connections.close();
		}catch (SQLException | NamingException e) {
			// we still need to close the connectinos even if they throw an exception
			if (connections!= null){connections.close();}
			e.printStackTrace();
		}
		
		return table;
	}
	
}
