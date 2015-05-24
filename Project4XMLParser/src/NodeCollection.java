import java.util.ArrayList;


public class NodeCollection {
	private ArrayList<Node> collection;
	private boolean attributeDetected;
	private int offset;
	
	public NodeCollection()
	{
		attributeDetected	= false;
		collection			= new ArrayList<Node>();
		collection.add(new Node());
		offset				=0;
	}
	
	public NodeCollection(boolean attributeSet, ArrayList<Node> collection)
	{
		this.attributeDetected	= attributeSet;
		this.collection 	= collection;
		offset				= collection.size()-1;
	}
	
	public NodeCollection copy()	{return new NodeCollection (this.attributeDetected, copyArrayList(this.collection));}
	
	public void clear()
	{
		this.attributeDetected = false;
		clearArrayList(collection);
	}
	
	private static ArrayList<Node> copyArrayList(ArrayList<Node> list)
	{
		ArrayList<Node> copy  = new ArrayList<Node>();
		for (Node n: list)
		{
			copy.add(n.copy());
		}
		return copy;
	}
	
	private void clearArrayList(ArrayList<Node> nodes)
	{
		for (Node node:nodes)
		{
			node.clear();
		}
	}
	
	public void detectAttribute()		{this.attributeDetected = true;}
	public void finishAttribute()		{this.attributeDetected = false;}
	public boolean attributeDetected()	{return attributeDetected;}
	
	
	public void set(String content)		{this.collection.get(offset).set(content);}
	
	public void endElement() 
	{
		offset++;
		collection.add(new Node());
	}
	
	public boolean contentSet()
	{
		if (collection.size()>0)	{return collection.get(0).contentSet();}
		return false;
	}
	
	public ArrayList<String> getContent()
	{
		ArrayList<String> content = new ArrayList<String>();
		for (Node N : this.collection)
		{
			content.add(N.getContent());
		}
		return content;
	}
	
	public String toString()
	{
		String value = new String();
		
		for (Node n : collection)
		{
			value+=n.getContent()+", ";
		}
		return value;
	}
	
	public void limitContentLength(int limit)
	{
		for(Node n: collection)
		{
			if(n.getContent().length() > limit)		{n.set(n.getContent().substring(0,limit));}
		}
	}
}
