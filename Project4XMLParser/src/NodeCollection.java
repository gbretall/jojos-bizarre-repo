import java.util.ArrayList;


public class NodeCollection {
	private ArrayList<Node> collection;
	private boolean attributeDetected;
	
	public NodeCollection()
	{
		attributeDetected	= false;
		collection		= new ArrayList<Node>();
	}
	
	public NodeCollection(boolean attributeSet, ArrayList<Node> collection)
	{
		this.attributeDetected	= attributeSet;
		this.collection 	= collection;
	}
	
	public NodeCollection copy(){
		return new NodeCollection (this.attributeDetected, copyArrayList(this.collection));		
	}
	
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
	
	public void detectAttribute()
	{
		this.attributeDetected = true;
	}

	public boolean attributeDetected()
	{
		return attributeDetected;
	}
	
	public void set(String content)
	{
		this.collection.add(new Node(content));
	}
	
}
