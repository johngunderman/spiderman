import static org.junit.Assert.*;

import org.junit.Test;

import spiderman.Direction;
import spiderman.NamedRelationship;
import storage.Graph;
import storage.Node;


public class DBTest {

	@Test
	public void test() 
	{
		Graph g = new Graph(true);
		
		//create Neo
		Person neo = new Person();
		neo.setName("Neo");
		neo.setAge(29);
		
		//create Trinity
		Person trinity = new Person();
		trinity.setName("Trinity");
		
		//create Morpheus
		Person morpheus = new Person();
		morpheus.setName("Morpheus");
		morpheus.setOccupation("Total badass");
		morpheus.setRank("Captain");
		
		//create Cypher
		Person cypher = new Person();
		cypher.setName("Cypher");
		cypher.setLastName("Reagan");
		
		//create Agent Smith
		Person smith = new Person();
		smith.setName("Agent Smith");
		
		//create Architect
		Person architect = new Person();
		architect.setName("The Architect");
		
		Node<Person> neoNode = g.addNode(neo);
		Node<Person> trinityNode = g.addNode(trinity);
		Node<Person> morpheusNode = g.addNode(morpheus);
		Node<Person> cypherNode = g.addNode(cypher);
		Node<Person> smithNode = g.addNode(smith);
		Node<Person> architectNode = g.addNode(architect);
		
		g.addRelationship(new NamedRelationship("LOVES"), Direction.Directed, trinityNode, neoNode);
		g.addRelationship(new NamedRelationship("KNOWS"), Direction.Directed, morpheusNode, trinityNode);
		g.addRelationship(new NamedRelationship("KNOWS"), Direction.Directed, neoNode, morpheusNode);
		g.addRelationship(new NamedRelationship("KNOWS"), Direction.Directed, morpheusNode, cypherNode);
		g.addRelationship(new NamedRelationship("KNOWS"), Direction.Directed, cypherNode, smithNode);
		g.addRelationship(new NamedRelationship("CODED_BY"), Direction.Directed, smithNode, architectNode);
		
		
	}

	private class Person
	{
		private String name = "";
		private String lastName = "";
		private int age = 0;
		private String occupation = "";
		private String rank = "";
		
		public String getName() {  return name;}
		public String getLastName() { return lastName;}
		public int getAge() { return age;}
		public String getOccupation() { return occupation;}
		public String getRank() { return rank;}
		
		public void setName(String name) { this.name = name;}
		public void setLastName(String lastName) { this.lastName = lastName;}
		public void setAge(int age) { this.age = age;}
		public void setOccupation(String occupation) { this.occupation = occupation;}
		public void setRank(String rank) { this.rank = rank;}
	}
	
	
}
