package spiderman;

/**
 * 
 * @author varley
 * 
 */
public interface Relationship {

	/**
	 * This should return a unique identifier for the specific relationship.
	 * 
	 * For instance in {@link NamedRelationship}, each different type of
	 * NamedRelationship should return a different string.
	 * 
	 * @return
	 */
	public String identifier();
	
	public boolean equals(Object o);
}
