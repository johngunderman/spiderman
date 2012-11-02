package storage;

import spiderman.Direction;
import spiderman.Relationship;

public class RelationshipHolder {
	private Relationship relationship;
	private Direction direction;
	private Node<?> origin;
	private Node<?> desitination;
	
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
	
	public Node<?> getDesitination() {
		return desitination;
	}
	
	public void setDesitination(Node<?> desitination) {
		this.desitination = desitination;
	}

}
