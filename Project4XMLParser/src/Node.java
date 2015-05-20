public class Node {
	protected String content;

	public Node()
	{
		this.content = null;
	}
	
	public Node(String content) 
	{
		this.content = content;
	}
	public Node copy()
	{
		return new Node(this.content);
	}
	
	public boolean contentSet()
	{
		return (content!=null);
	}
	
	public void set(String content)
	{
		this.content = content;
	}
	
	public String getContent()
	{
		return content;
	}
	
	protected void clear()
	{
		this.content = null;
	}
}
