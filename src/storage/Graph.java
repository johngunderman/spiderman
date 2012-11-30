package storage;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

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
	private final Map<Relationship, Set<RelationshipHolder>> relationshipIndex = new ConcurrentHashMap<Relationship, Set<RelationshipHolder>>();
	
	/**
	 * 
	 * @param unique
	 */
	public Graph(boolean unique) {
		if(unique) {
			this.nodes = new ConcurrentSkipListSet<Node<?>>();
			
		}
		else {
			this.nodes = new CopyOnWriteArrayList<Node<?>>();
		}
		this.relationships = new ConcurrentSkipListSet<RelationshipHolder>();
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
	public final <T> Node<T> addNode(final T value) {
		Node<T> newNode = new Node<T>(value);
		this.nodes.add(newNode);
		return newNode;
	}
	
	/**
	 * 
	 * @param r
	 * @param dir
	 * @param origin
	 * @param destination
	 */
	public final void addRelationship(final Relationship r, final Direction dir, final Node<?> origin, final Node<?> destination) {
		if(!this.nodes.contains(origin)) {
			this.nodes.add(origin);
		}
		if(!this.nodes.contains(destination)) {
			this.nodes.add(destination);
		}
		RelationshipHolder holder = new RelationshipHolder(r, dir, origin, destination);
		this.relationships.add(holder);
		Set<RelationshipHolder> relops = this.relationshipIndex.get(r);
		if(relops == null) {
			relops = new ConcurrentSkipListSet<RelationshipHolder>();
		}
		relops.add(holder);
		this.relationshipIndex.put(r, relops);
	}
	
	public final <T,K> void addRelationship(final Relationship r, final Direction dir, final T originData, final K destData) {
		Node<T> origin = new Node<T>(originData);
		Node<K> dest = new Node<K>(destData);
		if(!this.nodes.contains(origin)) {
			this.nodes.add(origin);
		}
		if(!this.nodes.contains(dest)) {
			this.nodes.add(dest);
		}
		
		RelationshipHolder holder = new RelationshipHolder(r, dir, origin, dest);
		this.relationships.add(holder);
		Set<RelationshipHolder> relops = this.relationshipIndex.get(r);
		if(relops == null) {
			relops = new ConcurrentSkipListSet<RelationshipHolder>();
		}
		relops.add(holder);
		this.relationshipIndex.put(r, relops);
	}
}
