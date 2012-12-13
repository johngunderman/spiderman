package query;

import java.util.LinkedList;
import java.util.List;

import storage.Node;

import evaluator.Evaluable;

public class QueryPlan {
	private Search iter = null;
	
	public QueryPlan(Search iter)
	{
		this.iter = iter;
	}
	
	public List<Node> evaluate(Evaluable eval)
	{
		List<Node> results = new LinkedList<Node>();
		
		while(iter.hasNext())
		{
			Node next = iter.getNext();
			if (eval.evaluateNode(next))
			{
				results.add(next);
			}
		}
		
		return results;
	}
	
}
