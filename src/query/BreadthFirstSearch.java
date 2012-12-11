package query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import storage.Node;

public class BreadthFirstSearch implements Search {
	
	Map<Node, Integer> nodeDepth;
	Queue<Node> queue;
	
	public BreadthFirstSearch(Node root, int depth)
	{
		nodeDepth = new HashMap<Node, Integer>();
		queue = new LinkedList<Node>();
		
		nodeDepth.put(root, 0);
		queue.add(root);
	}

	@Override
	public boolean hasNext() 
	{
		if (queue.isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public Node getNext() 
	{
		if (queue.isEmpty())
		{	
			return null;
		}
		else
		{
			Node nodeToReturn = queue.poll();
			int depth = nodeDepth.get(nodeToReturn);
			Iterator iter = nodeToReturn.exitRelations.iterator();
			while (iter.hasNext())
			{
				Node next = (Node) iter.next();
				if (!nodeDepth.containsKey(next))
				{
					nodeDepth.put(next,  depth+1);
					queue.add(next);
				}
			}
			
			return nodeToReturn;
		}
	}

}
