package storage;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentSkipListSet;

import spiderman.Direction;
import spiderman.Relationship;

/**
 * 
 * @author varley
 *
 */
public class Graph {
	private final Collection<Node<?>> nodes;
	private final Set<RelationshipHolder> relationships;
	
	/**
	 * 
	 * @param unique
	 */
	public Graph(boolean unique) {
		if(unique) {
			this.nodes = new ConcurrentSkipListSet<Node<?>>();
			this.relationships = new ConcurrentSkipListSet<RelationshipHolder>();
		}
		else {
			this.nodes = new CopyOnWriteArrayList<Node<?>>();
			this.relationships = new ConcurrentSkipListSet<RelationshipHolder>();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	Collection<Node<?>> getNodes() {
		return this.nodes;
	}
	
	/**
	 * 
	 * @return
	 */
	Collection<RelationshipHolder> getRelationships() {
		return this.relationships;
	}
	
	/**
	 * 
	 * @param value
	 */
	public final <T> void addNode(final T value) {
		Node<T> newNode = new Node<T>(value);
		this.nodes.add(newNode);
	}
	
	/**
	 * 
	 * @param r
	 * @param dir
	 * @param origin
	 * @param destination
	 */
	public final void addRelationship(final Relationship r, final Direction dir, final Node<?> origin, final Node<?> destination) {
		RelationshipHolder holder = new RelationshipHolder(r, dir, origin, destination);
		this.relationships.add(holder);
	}
}
