//package com.journaldev.xml.sax;
 
 

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;
 
public class SAXParser extends DefaultHandler 
{
	DocumentContainer currentDocument = new DocumentContainer();
	
	private void parseDocument() {
			
			//get a factory
			SAXParserFactory spf = SAXParserFactory.newInstance();
			try 
			{
				spf.setValidating(true);
				spf.setNamespaceAware(true);
				
				//get a new instance of parser
				javax.xml.parsers.SAXParser sp = spf.newSAXParser();
				
				//parse the file and also register this class for call backs
				sp.parse("final-data.xml", this);
				
			}catch(SAXException se) {
				se.printStackTrace();
			}catch(ParserConfigurationException pce) {
				pce.printStackTrace();
			}catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	
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
    public void characters(char ch[], int start, int length) throws SAXException 
    {
    	if(currentDocument.getAuthors().attributeDetected())
    	{
    		//System.out.println("aut"+new String(ch, start, length));
    		currentDocument.setAuthor(new String(ch, start, length));
    	}
    	else if(currentDocument.getEditor().attributeDetected())
    	{
    		//System.out.println("editor"+new String(ch, start,length));
    		currentDocument.setEditor(new String(ch, start, length));
    	}
    	else if(currentDocument.getTitle().attributeDetected())
    	{
    		currentDocument.setTitle(new String(ch, start, length));
    	}
    	else if(currentDocument.getBooktitle().attributeDetected())
    	{
    		currentDocument.setBooktitle(new String(ch, start, length));
    	}
    	else if(currentDocument.getPages().attributeDetected())
    	{
    		currentDocument.setPages(new String(ch, start, length));
    	}
    	else if(currentDocument.getAddress().attributeDetected())
    	{
    		currentDocument.setAddress(new String(ch, start, length));
    	}
    	else if(currentDocument.getYear().attributeDetected())
    	{
    		currentDocument.setYear(new String(ch, start, length));
    	}
    	else if(currentDocument.getJournal().attributeDetected())
    	{
    		currentDocument.setJournal(new String(ch, start, length));
    	}
    	else if(currentDocument.getVolume().attributeDetected())
    	{
    		currentDocument.setVolume(new String(ch, start, length));
    	}
    	else if(currentDocument.getNumber().attributeDetected())
    	{
    		currentDocument.setNumber(new String(ch, start, length));
    	}
    	else if(currentDocument.getMonth().attributeDetected())
    	{
    		currentDocument.setMonth(new String(ch, start, length));
    	}
    	else if(currentDocument.getUrl().attributeDetected())
    	{
    		currentDocument.setUrl(new String(ch, start, length));
    	}
    	else if(currentDocument.getEe().attributeDetected())
    	{
    		currentDocument.setEe(new String(ch, start, length));
    	}
    	else if(currentDocument.getCdrom().attributeDetected())
    	{
    		currentDocument.setCdrom(new String(ch, start, length));
    	}
    	else if(currentDocument.getCitations().attributeDetected())
    	{
    		currentDocument.setCitation(new String(ch, start, length));
    	}
    	else if(currentDocument.getPublisher().attributeDetected())
    	{
    		currentDocument.setPublisher(new String(ch, start, length));
    	}
    	else if(currentDocument.getNote().attributeDetected())
    	{
    		currentDocument.setNote(new String(ch, start, length));
    	}
    	else if(currentDocument.getCrossrefs().attributeDetected())
    	{
    		currentDocument.setCrossref(new String(ch, start, length));
    	}
    	else if(currentDocument.getIsbn().attributeDetected())
    	{
    		currentDocument.setIsbn(new String(ch, start, length));
    	}
    	else if(currentDocument.getSeries().attributeDetected())
    	{
    		currentDocument.setSeries(new String(ch, start, length));
    	}
    	else if(currentDocument.getSchool().attributeDetected())
    	{
    		currentDocument.setSchool(new String(ch, start, length));
    	}
    	else if(currentDocument.getChapter().attributeDetected())
    	{
    		currentDocument.setChapter(new String(ch, start, length));
    	}

    	
    }
 
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException 
    {
    	// These are the tags relating to the document's genre
		
    			if(qName.equalsIgnoreCase("article"))
    			{
    				System.out.println(currentDocument.toString());
    				currentDocument.clear();
    			}
    			else if(qName.equalsIgnoreCase("inproceedings"))
    			{
    				System.out.println(currentDocument.toString());
    				currentDocument.clear();
    			}
    			else if(qName.equalsIgnoreCase("proceedings"))
    			{
    				System.out.println(currentDocument.toString());
    				currentDocument.clear();
    			}
    			else if(qName.equalsIgnoreCase("book"))
    			{
    				System.out.println(currentDocument.toString());
    				currentDocument.clear();
    			}
    			else if(qName.equalsIgnoreCase("incollection"))
    			{
    				System.out.println(currentDocument.toString());
    				currentDocument.clear();
    			}
    			else if(qName.equalsIgnoreCase("phdthesis"))
    			{
    				System.out.println(currentDocument.toString());
    				currentDocument.clear();
    			}
    			else if(qName.equalsIgnoreCase("mastersthesis"))
    			{
    				System.out.println(currentDocument.toString());
    				currentDocument.clear();
    			}
    			else if(qName.equalsIgnoreCase("www"))
    			{
    				System.out.println(currentDocument.toString());
    				currentDocument.clear();
    			}
    			else if(qName.equalsIgnoreCase("author"))
    			{
    				currentDocument.getAuthors().endElement();
    			}
    			else if(qName.equalsIgnoreCase("cite"))
    			{
    				currentDocument.getCitations().endElement();
    			}
    			else if(qName.equalsIgnoreCase("crossref"))
    			{
    				currentDocument.getCrossrefs().endElement();
    			}
    			else if(qName.equalsIgnoreCase("editor"))
    			{
    				currentDocument.getEditor().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("title"))
    			{
    				currentDocument.getTitle().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("booktitle"))
    			{
    				currentDocument.getBooktitle().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("pages"))
    			{
    				currentDocument.getPages().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("year"))
    			{
    				currentDocument.getYear().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("address"))
    			{
    				currentDocument.getAddress().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("journal"))
    			{
    				currentDocument.getJournal().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("volume"))
    			{
    				currentDocument.getVolume().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("number"))
    			{
    				currentDocument.getNumber().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("month"))
    			{
    				currentDocument.getMonth().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("url"))
    			{
    				currentDocument.getUrl().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("ee"))
    			{
    				currentDocument.getEe().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("cdrom"))
    			{
    				currentDocument.getCdrom().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("publisher"))
    			{
    				currentDocument.getPublisher().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("note"))
    			{
    				currentDocument.getNote().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("isbn"))
    			{
    				currentDocument.getIsbn().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("series"))
    			{
    				currentDocument.getSeries().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("school"))
    			{
    				currentDocument.getSchool().finishAtribute();
    			}
    			else if(qName.equalsIgnoreCase("chapter"))
    			{
    				currentDocument.getChapter().finishAtribute();
    			}
        
    }
    
    public static void main(String[] args)
    {
    	SAXParser sp = new SAXParser();
    	sp.parseDocument();
    }

}
