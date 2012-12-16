package query;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import storage.Node;

import evaluator.Evaluable;

public class QueryPlan {
	private Iterator<Node<?>> iter = null;
	
	public QueryPlan(Iterator<Node<?>> iter)
	{
		this.iter = iter;
	}
	
	public List<Node<?>> evaluate(Evaluable eval)
	{
		List<Node<?>> results = new LinkedList<Node<?>>();
		
		while(iter.hasNext())
		{
			Node<?> next = iter.next();
			if (eval.evaluateNode(next))
			{
				results.add(next);
			}
		}
		
		return results;
	}
	
}
