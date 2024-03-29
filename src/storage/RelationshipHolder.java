package storage;

import spiderman.Direction;
import spiderman.Relationship;

/**
 * A simple container object that encapsulates the components of a relationship.
 * 
 * @author Varley
 * 
 */
public class RelationshipHolder implements
		java.lang.Comparable<RelationshipHolder> {
	private Relationship relationship;
	private Direction direction;
	private Node<?> origin;
	private Node<?> destination;

	/**
	 * This constructor creates the container and it adds itself to the proper
	 * relationship in the origin and destination.
	 * 
	 * @param r
	 * @param dir
	 * @param origin
	 * @param dest
	 */
	public RelationshipHolder(Relationship r, Direction dir, Node<?> origin,
			Node<?> dest) {
		this.relationship = r;
		this.direction = dir;
		this.origin = origin;
		this.destination = dest;

		origin.exitRelations.add(this);
		dest.entranceRelations.add(this);
		
		if (dir == Direction.Undirected) {
			origin.entranceRelations.add(this);
			dest.exitRelations.add(this);
		}
		
	}

	public Relationship getRelationship() {
		return relationship;
	}

	public void setRelationship(Relationship relationship) {
		this.relationship = relationship;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Node<?> getOrigin() {
		return origin;
	}

	public void setOrigin(Node<?> origin) {
		this.origin = origin;
	}

	public Node<?> getDestination() {
		return destination;
	}

	public void setDestination(Node<?> desitination) {
		this.destination = desitination;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((destination == null) ? 0 : destination.hashCode());
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result
				+ ((relationship == null) ? 0 : relationship.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		RelationshipHolder other = (RelationshipHolder) obj;
		if (destination == null) {
			if (other.destination != null) {
				return false;
			}
		} else if (!destination.equals(other.destination)) {
			return false;
		}
		if (direction != other.direction) {
			return false;
		}
		if (origin == null) {
			if (other.origin != null) {
				return false;
			}
		} else if (!origin.equals(other.origin)) {
			return false;
		}
		if (relationship == null) {
			if (other.relationship != null) {
				return false;
			}
		} else if (!relationship.equals(other.relationship)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(RelationshipHolder o) {
		if (this.equals(o)) {
			return 0;
		} else {
			return 1;
		}
	}

}
