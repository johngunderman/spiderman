package spiderman;

import storage.*;

public class Spiderman {
	
	
	// example usage of the spiderman library
	public static void main(String[] argv) {
		
		Graph g = new Graph(true);

		Node<String> n = g.addNode("EECS 433");
		
		Node<String> n2 = g.addNode("CWRU");
		
		g.addRelationship(new NamedRelationship("TAUGHT_AT"), Direction.Directed, n, n2);
		
	}
	
}
