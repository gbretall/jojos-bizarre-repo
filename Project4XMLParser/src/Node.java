public class Node {
	private StringBuilder content;

	public Node()
	{
		this.content = null;
	}
	
	public Node(String content) 
	{
		this.content = new StringBuilder(content);
	}
	
	public Node(StringBuilder SB)
	{
		this.content = new StringBuilder(SB);
	}
	
	public Node copy()
	{
		if (this.content!=null)	{return new Node(this.content);}
		else					{return new Node();}
	}
	
	public boolean contentSet()
	{
		return (content!=null);
	}
	
	public void set(String content)
	{
		if (this.content==null)	{this.content = new StringBuilder();}
		
		this.content.append(content);
	}
	
	public void replace(String content)
	{
		this.content = new StringBuilder(content);
	}
	
	public String getContent()
	{
		return (content==null? "":content.toString());
	}
	
	public String toString()
	{
		return getContent();
	}
	
	protected void clear()
	{
		this.content = null;
	}
}
