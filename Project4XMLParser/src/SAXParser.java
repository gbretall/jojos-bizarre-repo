//package com.journaldev.xml.sax;
 
 

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 
public class SAXParser extends DefaultHandler 
{
	DocumentContainer currentDocument = new DocumentContainer();
	
	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException 
	{
		// These are the tags relating to the document's genre
		
		if(qName.equalsIgnoreCase("article"))
		{
			currentDocument.setGenre("article");
		}
		else if(qName.equalsIgnoreCase("inproceedings"))
		{
			currentDocument.setGenre("inproceedings");
		}
		else if(qName.equalsIgnoreCase("proceedings"))
		{
			currentDocument.setGenre("proceedings");
		}
		else if(qName.equalsIgnoreCase("book"))
		{
			currentDocument.setGenre("book");
		}
		else if(qName.equalsIgnoreCase("incollection"))
		{
			currentDocument.setGenre("incollection");
		}
		else if(qName.equalsIgnoreCase("phdthesis"))
		{
			currentDocument.setGenre("phdthesis");
		}
		else if(qName.equalsIgnoreCase("mastersthesis"))
		{
			currentDocument.setGenre("masterthesis");
		}
		else if(qName.equalsIgnoreCase("www"))
		{
			currentDocument.setGenre("www");
		}
		
		//These tags are the content inside of a given document (i.e. the bulk of what will get entered in the database)
		
		else if(qName.equalsIgnoreCase("author"))
		{
			currentDocument.detectAuthor();
		}
		else if(qName.equalsIgnoreCase("editor"))
		{
			currentDocument.detectEditor();
		}
		else if(qName.equalsIgnoreCase("title"))
		{
			currentDocument.detectTitle();
		}
		else if(qName.equalsIgnoreCase("booktitle"))
		{
			currentDocument.detectBooktitle();
		}
		else if(qName.equalsIgnoreCase("pages"))
		{
			currentDocument.detectPages();
		}
		else if(qName.equalsIgnoreCase("year"))
		{
			currentDocument.detectYear();
		}
		else if(qName.equalsIgnoreCase("address"))
		{
			currentDocument.detectAddress();
		}
		else if(qName.equalsIgnoreCase("journal"))
		{
			currentDocument.detectJournal();
		}
		else if(qName.equalsIgnoreCase("volume"))
		{
			currentDocument.detectVolume();
		}
		else if(qName.equalsIgnoreCase("number"))
		{
			currentDocument.detectNumber();
		}
		else if(qName.equalsIgnoreCase("month"))
		{
			currentDocument.detectMonth();
		}
		else if(qName.equalsIgnoreCase("url"))
		{
			currentDocument.detectUrl();
		}
		else if(qName.equalsIgnoreCase("ee"))
		{
			currentDocument.detectEe();
		}
		else if(qName.equalsIgnoreCase("cdrom"))
		{
			currentDocument.detectCdrom();
		}
		else if(qName.equalsIgnoreCase("cite"))
		{
			currentDocument.detectCitation();
		}
		else if(qName.equalsIgnoreCase("publisher"))
		{
			currentDocument.detectPublisher();
		}
		else if(qName.equalsIgnoreCase("note"))
		{
			currentDocument.detectNote();
		}
		else if(qName.equalsIgnoreCase("crossref"))
		{
			currentDocument.detectCrossref();
		}
		else if(qName.equalsIgnoreCase("isbn"))
		{
			currentDocument.detectIsbn();
		}
		else if(qName.equalsIgnoreCase("series"))
		{
			currentDocument.detectSeries();
		}
		else if(qName.equalsIgnoreCase("school"))
		{
			currentDocument.detectSchool();
		}
		else if(qName.equalsIgnoreCase("chapter"))
		{
			currentDocument.detectChapter();
		}
		
    }
 
 
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException 
    {
        
    }
 
 
    @Override
    public void characters(char ch[], int start, int length) throws SAXException 
    {
 
    }

}
