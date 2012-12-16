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
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof NamedRelationship)
		{
			if (((NamedRelationship)o).name == this.name)
			{
				return true;
			}
		}
		return false;
	}
}
