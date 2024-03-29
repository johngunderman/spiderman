package query;

import java.util.Iterator;

import storage.Node;
import evaluator.Evaluable;

public class QueryPlan {
	private Iterator<Node<?>> iter = null;
	
	public QueryPlan(Iterator<Node<?>> iter)
	{
		this.iter = iter;
	}
	
	public ResultSet evaluate(Evaluable eval)
	{
		ResultSet results = new ResultSet();
		
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
