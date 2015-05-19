
public class TagNode 
{
	private boolean flag;
	private String content;
	
	public TagNode()
	{
		flag = false;
		content = "";
	}
	
	public TagNode(boolean flag, String content)
	{
		this.flag = flag;
		this.content = content;
	}
	
	public boolean getFlag()
	{
		return flag;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void setFlagTrue()
	{
		flag = true;
	}
	
	public void clear()
	{
		flag = false;
		content = "";
	}
	
	public void setContent(String newContent)
	{
		content = newContent;
	}

}
