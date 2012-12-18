package query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import storage.Node;
import storage.RelationshipHolder;

public class BreadthFirstSearch implements Iterator<Node<?>>{
	
	Map<Node<?>, Integer> nodeDepth;
	Queue<Node<?>> queue;
	int maxDepth = 0;
	
	public BreadthFirstSearch(Node<?> root, int depth)
	{
		nodeDepth = new HashMap<Node<?>, Integer>();
		queue = new LinkedList<Node<?>>();
		
		nodeDepth.put(root, 0);
		queue.add(root);
		
		maxDepth = depth;
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
	public Node<?> next() 
	{
		if (queue.isEmpty())
		{	
			return null;
		}
		else
		{
			Node<?> nodeToReturn = queue.poll();
			int depth = nodeDepth.get(nodeToReturn);
			Iterator<RelationshipHolder> iter = nodeToReturn.exitRelations.iterator();
			while (iter.hasNext())
			{
				Node<?> next = iter.next().getDestination();
				if (!nodeDepth.containsKey(next))
				{
					nodeDepth.put(next,  depth+1);
					if (depth+1 <= maxDepth)
					{
						queue.add(next);
					}
				}
			}
			
			return nodeToReturn;
		}
	}

	@Override
	public void remove() 
	{
			
	}

}
