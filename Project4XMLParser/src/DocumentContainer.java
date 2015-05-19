import java.util.ArrayList;


public class DocumentContainer 
{
	TagNode title;
	TagNode pages;
	TagNode year;
	TagNode volume;
	TagNode number;
	TagNode url;
	TagNode ee;
	TagNode cdrom;
	ArrayList<TagNode> citations;
	ArrayList<TagNode> crossrefs;
	TagNode isbn;
	TagNode series;
	TagNode editor;
	ArrayList<TagNode> authors;
	
	
	
	public DocumentContainer()
	{
		title = new TagNode();
		pages = new TagNode();
		year = new TagNode();
		volume = new TagNode();
		number = new TagNode();
		url = new TagNode();
		ee = new TagNode();
		cdrom = new TagNode();
		citations = new ArrayList<TagNode>();
		crossrefs = new ArrayList<TagNode>();
		isbn = new TagNode();
		series = new TagNode();
		editor = new TagNode();
		authors = new ArrayList<TagNode>();
	}

}
