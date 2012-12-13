package query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import storage.Node;
import storage.RelationshipHolder;

public class DepthFirstSearch implements Search 
{
	Node<?> currentNode;
	int currentDepth;
	int maxDepth;
	Map<Node<?>, List<Node<?>>> openChildren;  //children of a parent that have not been visited yet
	Map<Node<?>, Boolean> visited;  //false if parent has been visited, true if node has been visited
	Map<Node<?>, Node<?>> parentNodes;  //map from child to parent

	public DepthFirstSearch(Node<?> root, int maxDepth)
	{
		currentNode = root;
		currentDepth = 0;
		this.maxDepth = maxDepth;
		visited = new HashMap<Node<?>, Boolean>();
		parentNodes = new HashMap<Node<?>, Node<?>>();
		
		visitNewNode(root);
	}
	
	@Override
	public boolean hasNext() 
	{
		if (currentNode == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public Node<?> getNext() 
	{
		if (currentNode != null)
		{
			visited.put(currentNode, true);
			if (currentDepth < maxDepth)
			{
				List<Node<?>> children = openChildren.get(currentNode);
				if (children != null && children.size() != 0)
				{
					currentNode = children.remove(0);
					currentDepth++;
					if (currentDepth < maxDepth)
					{
						visitNewNode(currentNode);
					}
					return currentNode;
				}
			}
			
			while(currentNode != null && (openChildren.get(currentNode) == null || openChildren.get(currentNode).size() == 0))
			{
				currentNode = parentNodes.get(currentNode);
				currentDepth--;
			}
			
			if (currentNode == null)
			{
				return null;
			}
			else
			{
				currentNode = openChildren.get(currentNode).remove(0);
				currentDepth++;
				if(currentDepth < maxDepth)
				{
					visitNewNode(currentNode);
				}
				return currentNode;
			}
		}
		return null;
	}

	public void visitNewNode(Node<?> parent)
	{
		List<Node<?>> children = new LinkedList<Node<?>>();
		openChildren.put(parent, children);
		Iterator<RelationshipHolder> iter = parent.exitRelations.iterator();
		while (iter.hasNext())
		{
			Node<?> next =  iter.next().getDestination();
			children.add(next);
			parentNodes.put(next, parent);
			visited.put(next, false);
		}
	}
}
