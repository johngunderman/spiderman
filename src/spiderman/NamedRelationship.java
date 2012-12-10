package spiderman;

public class NamedRelationship implements Relationship {

	private String name;
	
	public NamedRelationship(String name) {
		this.name = name;
	}

	@Override
	public String identifier() {
		return name;
	}
}
