package storage;

import java.util.concurrent.ConcurrentSkipListSet;

public class Node<T> implements java.lang.Comparable<Node<?>>{
	private T data;
	
	// outgoing edges of relationships
	protected ConcurrentSkipListSet<RelationshipHolder> exitRelations;
	// incoming edges of relationships
	protected ConcurrentSkipListSet<RelationshipHolder> entranceRelations;
	
	public Node(T data) {
		if(data == null) {
			throw new NullPointerException("The data to be stored was null");
		}
		this.data = data;
		
		exitRelations = new ConcurrentSkipListSet<RelationshipHolder>();
		entranceRelations = new ConcurrentSkipListSet<RelationshipHolder>();

		
	}
	
	public T getData() {
		return this.data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Node<?> other = (Node<?>) obj;
		if (data == null) {
			if (other.data != null) {
				return false;
			}
		}
		else if (!data.equals(other.data)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Node<?> o) {
		if(this.getData().equals(o.getData())) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
