package storage;

import java.util.HashSet;
import java.util.Set;

public class Graph {
	private final Set<Node<?>> nodes;
	private final Set<RelationshipHolder> relationships;
	
	public Graph() {
		this.nodes = new HashSet<Node<?>>();
		this.relationships = new HashSet<RelationshipHolder>();
	}
	
	Set<Node<?>> getNodes() {
		return this.nodes;
	}
	
	Set<RelationshipHolder> getRelationships() {
		return this.relationships;
	}
}
