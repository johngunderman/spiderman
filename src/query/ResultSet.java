package query;

import java.util.LinkedList;

import spiderman.Relationship;
import storage.Node;

public class ResultSet extends LinkedList<Node<?>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -894398872550152465L;

	public ResultSet hasIncomingNeighbor(Node<?> n, Relationship r) {
		ResultSet results = new ResultSet();
		
		for (Node<?> no : this) {
			if (no.hasIncomingNeighbor(n, r))
				results.add(no);
		}
		
		return results;
	}
	
	public ResultSet getNeighborAlongRelation(Relationship r) {
		ResultSet results = new ResultSet();
		
		for (Node<?> no : this) {
			results.addAll(no.getNeighborAlongRelation(r));
		}
		
		return results;
	}
	
	public ResultSet hasOutgoingNeighbor(Node<?> n, Relationship r) {
		ResultSet results = new ResultSet();
		
		for (Node<?> no : this) {
			if (no.hasOutgoingNeighbor(n, r))
				results.add(no);
		}
		
		return results;
	}
	
	public QueryPlan queryPlan() {
		return new QueryPlan(this.iterator());
	}
}
