import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;




public class SQLGenerator {
	private DocumentContainer document;
	
	public SQLGenerator()
	{
		document = null;
	}
	
	public SQLGenerator(DocumentContainer doc)
	{
		this.document = new DocumentContainer(doc);
	}
	
	//Inserts editors and authors into the people table so long as 
	//they aren't already in the table (duplication will be considered on names)
	public String insert_people_tbl() throws SQLException, NamingException
	{
		String SQL = "";
		if(document.getAuthors().attributeDetected())
		{
			SQL += "CALL insert_person("
					+ document.getAuthors().toString()
					+ "); ";
		}
		if(document.getEditor().attributeDetected())
		{
			SQL += "CALL insert_person("
					+ document.getEditor().toString()
					+ "); ";
		}
		return SQL;
	}
	
	public String insert_publisher_tbl() throws SQLException, NamingException
	{
		String SQL = "";
		if(document.getPublisher().attributeDetected())
		{
			SQL += "CALL insert_publisher("
					+ document.getPublisher().toString()
					+ "); ";
		}
		return SQL;
	}
	
	public String insert_genre_tbl() throws SQLException, NamingException
	{
		String SQL = "";
		if(document.getGenre().attributeDetected())
		{
			SQL += "CALL insert_genre("
					+ document.getGenre().toString()
					+ "); ";
		}
		return SQL;
	}
	
	public String insert_booktitle_tbl() throws SQLException, NamingException
	{
		String SQL = "";
		if(document.getBooktitle().attributeDetected())
		{
			SQL += "CALL insert_booktitle("
					+ document.getBooktitle().toString()
					+ "); ";
		}
		return SQL;
	}
	
	//Inserts the relevant records into the tbl_dblp_document
	//Is to be run after the functions that update tbl_people, tbl_publisher, tbl_genres,
	//and tbl_booktitle
	public String insert_tbl_dblp_document() throws SQLException, NamingException
	{
		//Creates the beginning of the insert statement including the columns to be inserted into.
		boolean isFirstItem = true;
		String SQL = "INSERT INTO moviedb.tbl_dblp_document (";
		if(document.getEditor().attributeDetected())
    	{
			if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += "editor_id";
    	}
    	if(document.getTitle().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += "title";
    	}
    	if(document.getGenre().attributeDetected())
    	{
    		if(isFirstItem)
    			isFirstItem = false;
    		else
    		{
    			SQL += ", ";
    		}
    		SQL += "genre_id";
    		
    	}
    	if(document.getBooktitle().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += "booktitle_id";
    	}
    	if(document.getPages().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += "start_page, end_page";
    	}
    	
    	if(document.getYear().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += "year";
    	}
    	if(document.getVolume().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += "volume";
    	}
    	if(document.getNumber().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += "number";
    	}
    	if(document.getUrl().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += "url";
    	}
    	if(document.getEe().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += "ee";
    	}
    	if(document.getCdrom().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += "cdrom";
    	}
    	if(document.getCitations().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += "cite";
    	}
    	if(document.getPublisher().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += "publisher";
    	}
    	if(document.getCrossrefs().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += "crossref";
    	}
    	if(document.getIsbn().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += "isbn";
    	}
    	if(document.getSeries().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += "series";
    	}
		
		SQL += ") VALUES(";
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Appends the values that are to be added into the tbl_dblp_document 
		isFirstItem = true;
		if(document.getEditor().attributeDetected())
    	{
			if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += get_id(document.getEditor().toString(), "editor");
    	}
    	if(document.getTitle().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += document.getTitle().toString();
    	}
    	if(document.getGenre().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += get_id(document.getGenre().toString(), "genre");
    	}
    	if(document.getBooktitle().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += get_id(document.getBooktitle().toString(), "booktitle");
    	}
    	if(document.getPages().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			String[] startEndPages = document.getPages().toString().split("-");
			SQL += startEndPages[0] + ", "  + startEndPages[1];
    	}
    	if(document.getYear().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += document.getYear().toString();
    	}
    	if(document.getVolume().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
    		SQL += document.getVolume().toString();
    	}
    	if(document.getNumber().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
    		SQL += document.getNumber().toString();
    	}
    	if(document.getUrl().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
    		SQL += document.getUrl().toString();
    	}
    	if(document.getEe().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
    		SQL += document.getEe().toString();
    	}
    	if(document.getCdrom().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
    		SQL += document.getCdrom().toString();
    	}
    	if(document.getCitations().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += document.getCitations().toString();
    	}
    	if(document.getPublisher().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
    		SQL += document.getPublisher().toString();
    	}
    	if(document.getCrossrefs().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
			SQL += document.getCrossrefs().toString();
    	}
    	if(document.getIsbn().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
    		SQL += document.getIsbn().toString();
    	}
    	if(document.getSeries().attributeDetected())
    	{
    		if(isFirstItem)
			{
				isFirstItem = false;
			}
			else
			{
				SQL += ", ";
			}
    		SQL += document.getSeries().toString();
    	}
		SQL += ");";
		return SQL;
	}
	
	//Updates the authors and genre mapping table; should be 
	//called once for every instance of author in the collection
	public String insert_author_document_mapping() throws SQLException, NamingException
	{
		String SQL = "";
		if(document.getAuthors().attributeDetected())
		{
			String[] authors = document.getAuthors().toString().split(",");
			for(String author: authors)
			{
				SQL += "INSERT INTO moviedb.tbl_author_document_mapping (doc_id, author_id) VALUES("
						+ get_id(document.getTitle().toString(), "document")
						+ ", "
						+ get_id(author, "author")
						+"); ";
			}
		}
		return SQL;
	}
	
	//Makes all of the string fields in the document compliant with their length 
	//limitations as stated in the database schema
	public void limitStringLength()
	{
		if(document.getAuthors().attributeDetected())
    	{
    		document.getAuthors().limitContentLength(61);
    	}
    	if(document.getEditor().attributeDetected())
    	{
    		if(document.getEditor().getContent().length() > 61)
    		{
    			document.getEditor().set(document.getEditor().getContent().substring(0, 61));
    		}
    	}
    	if(document.getTitle().attributeDetected())
    	{
    		if(document.getTitle().getContent().length() > 300)
    		{
    			document.getTitle().set(document.getTitle().getContent().substring(0, 300));
    		}
    	}
    	if(document.getBooktitle().attributeDetected())
    	{
    		if(document.getBooktitle().getContent().length() > 300)
    		{
    			document.getBooktitle().set(document.getBooktitle().getContent().substring(0, 300));
    		}
    	}
    	if(document.getUrl().attributeDetected())
    	{
    		if(document.getUrl().getContent().length() > 200)
    		{
    			document.getUrl().set(document.getUrl().getContent().substring(0, 200));
    		}
    	}
    	if(document.getEe().attributeDetected())
    	{
    		if(document.getEe().getContent().length() > 200)
    		{
    			document.getEe().set(document.getEe().getContent().substring(0, 200));
    		}
    	}
    	if(document.getCdrom().attributeDetected())
    	{
    		if(document.getCdrom().getContent().length() > 75)
    		{
    			document.getCdrom().set(document.getCdrom().getContent().substring(0, 75));
    		}
    	}
    	if(document.getCitations().attributeDetected())
    	{
    		document.getCitations().limitContentLength(75);
    	}
    	if(document.getPublisher().attributeDetected())
    	{
    		if(document.getPublisher().getContent().length() > 300)
    		{
    			document.getPublisher().set(document.getPublisher().getContent().substring(0,300));
    		}
    	}
    	if(document.getCrossrefs().attributeDetected())
    	{
    		document.getCrossrefs().limitContentLength(75);
    	}
    	if(document.getIsbn().attributeDetected())
    	{
    		if(document.getIsbn().getContent().length() > 21)
    		{
    			document.getIsbn().set(document.getIsbn().getContent().substring(0, 21));
    		}
    	}
    	if(document.getSeries().attributeDetected())
    	{
    		if(document.getSeries().getContent().length() > 100)
    		{
    			document.getSeries().set(document.getSeries().getContent().substring(0, 100));
    		}
    	}

	}

	//Takes a name and table as parameters and subsequently returns the corresponding
	//id from the corresponding table as a string
	private String get_id(String reference_name, String id_type) throws SQLException, NamingException
	{
		SQLHandler id_selector = new SQLHandler();
		String table_name = "";
		//Checks for what table I need to get the id out of
		if(id_type.equals("author") || id_type.equals("editor"))
		{
			table_name = "tbl_people";
		}
		else if(id_type.equals("publisher"))
		{
			table_name = "tbl_publisher";
		}
		else if(id_type.equals("genre"))
		{
			table_name = "tbl_genres";
		}
		else if(id_type.equals("booktitle"))
		{
			table_name = "tbl_booktitle";
		}
		else if(id_type.equals("document"))
		{
			table_name = "tbl_dblp_document";
		}
		//We start to write the actual query for the id
		String SQL = "SELECT id FROM moviedb."
				+ table_name
				+ " WHERE ";
		//This gets what the where condition to find the id on will be
		if(id_type.equals("author") || id_type.equals("editor"))
		{
			SQL += "name = ";
		}
		else if(id_type.equals("publisher"))
		{
			SQL += "publisher_name = ";
		}
		else if(id_type.equals("genre"))
		{
			SQL += "genre_name = ";
		}
		else if(id_type.equals("booktitle") || id_type.equals("document"))
		{
			SQL += "title = ";
		}
		SQL += reference_name + " LIMIT 1;";
		//Runs the query and returns the result from it
		ResultSet rs = id_selector.query(SQL);
		String result_id = "";
		if(rs.isBeforeFirst())
		{
			while(rs.next())
			{
				result_id = Integer.toString(rs.getInt(1));
			}
		}
		id_selector.close();
		return result_id;
	}
}
