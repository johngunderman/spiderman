package storage;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
	private final Map<Object, List<Node<?>>> dataIndex;
	private final Collection<Node<?>> nodes;
	private final Set<RelationshipHolder> relationships;
	private final Map<String, Set<RelationshipHolder>> relationshipIndex;
	private final boolean unique;
	
	/**
	 * 
	 * @param unique
	 */
	public Graph(boolean unique) {
		this.unique = unique;
		if(unique) {
			this.nodes = new ConcurrentSkipListSet<Node<?>>();
			
		}
		else {
			this.nodes = new CopyOnWriteArrayList<Node<?>>();
		}
		this.relationships = new ConcurrentSkipListSet<RelationshipHolder>();
		this.relationshipIndex = new ConcurrentHashMap<String, Set<RelationshipHolder>>(100);
		this.dataIndex = new ConcurrentHashMap<Object, List<Node<?>>>(100);
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
	Set<RelationshipHolder> getRelationships() {
		return this.relationships;
	}
	
	/**
	 * 
	 * @param value
	 */
	public final <T> Node<T> addNode(final T value) {
		Node<T> newNode = new Node<T>(value);
		if(this.unique && this.nodes.contains(newNode)) {
			throw new IllegalStateException(value.toString() + " is already in this unique value graph");
		}
		this.nodes.add(newNode);
		List<Node<?>> list = this.dataIndex.get(value);
		if(list == null) {
			list = new CopyOnWriteArrayList<Node<?>>();
		}
		list.add(newNode);
		this.dataIndex.put(value, list);
		return newNode;
	}
	
	public final List<Node<?>> getNodes(final Object value) {
		List<Node<?>> list = this.dataIndex.get(value);
		if(list==null) {
			return Collections.emptyList();
		}
		return list;
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
			throw new IllegalArgumentException("The graph does not contain the specified origin node " + origin.toString());
		}
		if(!this.nodes.contains(destination)) {
			throw new IllegalArgumentException("The graph does not contain the specified destination node " + destination.toString());
		}
		RelationshipHolder holder = new RelationshipHolder(r, dir, origin, destination);
		this.relationships.add(holder);
		Set<RelationshipHolder> relops = this.relationshipIndex.get(r.identifier());
		if(relops == null) {
			relops = new ConcurrentSkipListSet<RelationshipHolder>();
		}
		relops.add(holder);
		this.relationshipIndex.put(r.identifier(), relops);
	}
	
	public void removeNode(final Node<?> node) {
		//Remove relationships that contain this node
		Collection<RelationshipHolder> rels = new LinkedList<RelationshipHolder>();
		rels.addAll(node.entranceRelations);
		rels.addAll(node.exitRelations);
		for(RelationshipHolder h : rels) {
			//Remove from relationship index
			String rel = h.getRelationship().identifier();
			Set<RelationshipHolder> list = this.relationshipIndex.get(rel);
			list.remove(h);
			//Remove from both nodes
			h.getOrigin().removeRelationship(h);
			h.getDestination().removeRelationship(h);
			//Remove from set of relationships
			this.relationships.remove(h);
		}
		List<Node<?>> nodes = this.dataIndex.get(node.getData());
		for(Iterator<Node<?>> it = nodes.iterator(); it.hasNext(); ) {
			if(node == it.next()) {
				it.remove();
				break;
			}
		}
		//Remove node
		this.nodes.remove(node);
	}
	
	public void removeRelationship(final RelationshipHolder holder) {
		holder.getOrigin().removeRelationship(holder);
		holder.getDestination().removeRelationship(holder);
		this.relationshipIndex.get(holder.getRelationship().identifier()).remove(holder);
		this.relationships.remove(holder);
	}
}
