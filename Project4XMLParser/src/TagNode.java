
public class TagNode extends Node
{
	private boolean attributeDetected;
	
	public TagNode()
	{
		super();
		attributeDetected = false;
	}
	
	public TagNode(boolean attrDetected, String content)
	{
		super(content);
		attributeDetected = attrDetected;
	}
	
	public TagNode copy()
	{
		return new TagNode(this.attributeDetected, this.getContent());
	}
	
	public boolean attributeDetected()
	{
		return attributeDetected;
	}
	
	public void clear()
	{
		attributeDetected = false;
		super.clear();
	}
	
	public void detectAttribute()
	{
		attributeDetected = true;
	}
	
	public void finishAtribute()
	{
		attributeDetected = false;
	}
	
	public void set(String newContent)
	{
		super.set(newContent);
	}

}
