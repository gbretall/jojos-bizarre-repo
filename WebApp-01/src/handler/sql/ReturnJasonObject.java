package handler.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

public class ReturnJasonObject {
	
	
	public ReturnJasonObject(){
		
	}
	
	private String exicuteQuery(String sql) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		
		GetConnection myConnection = new GetConnection();
//		String objectToReturn = "{ key1: 'value1', key2: 'value2' }";
		String objectToReturn = "{ \"movie\":[";
		try {
			ResultSet tempSet = myConnection.getResultsOfQuery(sql);
			
			if(tempSet.isBeforeFirst()){
				while(tempSet.next()){
					//TODO make json here
					objectToReturn += "{"
							+ "\"movieId\":"
							+ tempSet.getInt("id") + ","
							+ "\"movieTitle\":"
							+ "\"" + tempSet.getString("title")+"\","
							
							+ "\"year\":"
							+ "\""+ tempSet.getString("year")+"\","

							+ "\"director\":"
							+ "\""+ tempSet.getString("director")+"\","
							
							+ "\"banner_url\":"
							+ "\""+ tempSet.getString("banner_url")+"\","
							
							
							+ "\"actors\":"
							+ "\""+ tempSet.getString("actors")+"\","
							
							+ "\"genre\":"
							+ "\""+ tempSet.getString("genre")+"\""
							
							+ ""
							+ "},";
				}

				objectToReturn = objectToReturn.substring(0,objectToReturn.length()-1); // takes off trailing ","
				objectToReturn += "]}";
			}
			else{
				objectToReturn="{}";
			}
			myConnection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

//		myConnection.close();
		System.out.println(objectToReturn);
//		return "{\"movie\":[{\"movieTitle\": \"test\"}]}";
		return objectToReturn;
	}
	
	public String returnJason(String title) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		
		String [] splitTitle = title.split(" ");
		
		String fixedTitle = "";
		
		for (String s: splitTitle){
			System.out.println(s);
			fixedTitle +="%"+ s+"%";
		}
		System.out.println(fixedTitle);
		
//		String sql1 = "SELECT * FROM moviedb.movies m where m.title like '"+fixedTitle+"' limit 5;";
		String sql1 ="SELECT MD.id, MD.title, MD.year, MD.director, MD.banner_url, "
				+ "GROUP_CONCAT(DISTINCT G.name) as genre, "
				+ "GROUP_CONCAT(DISTINCT ' ', S.first_name, ' ', S.last_name) as actors "
				+ "FROM ((SELECT * FROM moviedb.movies M WHERE M.title LIKE '%"+fixedTitle+"%'ORDER BY M.id) MD "
						+ "JOIN (moviedb.genres G JOIN moviedb.genres_in_movies GIM ON G.id=GIM.genre_id)  "
						+ "ON GIM.movie_id = MD.id) JOIN (moviedb.stars_in_movies SIM JOIN moviedb.stars S ON SIM.star_id = S.id ) "
						+ "ON SIM.movie_id = MD.id  GROUP BY MD.id "
						+ "LIMIT 5;";
		return exicuteQuery(sql1);
	}

}
