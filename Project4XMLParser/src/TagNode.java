
public class TagNode 
{
	private boolean attributeDetected;
	private boolean contentSet;
	private String content;
	
	public TagNode()
	{
		attributeDetected = false;
		contentSet		  = false;
		content			  = "";
	}
	
	public TagNode(boolean contentSet, boolean attrDetected, String content)
	{
		attributeDetected = attrDetected;
		this.contentSet = contentSet;
		this.content = content;
	}
	
	public TagNode copy()
	{
		return new TagNode(this.contentSet, this.attributeDetected, this.content);
	}
	public boolean contentSet()
	{
		return contentSet;
	}
	
	public boolean attributeDetected()
	{
		return attributeDetected;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void clear()
	{
		contentSet		  = false;
		attributeDetected = false;
		content			  = "";
	}
	
	public void detectAttribute()
	{
		attributeDetected = true;
	}
	
	public void set(String newContent)
	{
		contentSet = true;
		content = newContent;
	}

}
