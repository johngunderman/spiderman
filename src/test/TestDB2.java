package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import evaluator.Evaluable;

import spiderman.Direction;
import spiderman.NamedRelationship;
import storage.BasicGraph;
import storage.Graph;
import storage.Node;

public class TestDB2 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() {
		Graph g = new BasicGraph(true);
		
		Node<String> frank = g.addNode("Frank");
		Node<String> bill = g.addNode("Bill");
		Node<String> hank = g.addNode("Hank");
		Node<String> mike = g.addNode("Mike");
		Node<String> chicago = g.addNode("Chicago");
		Node<String> sally = g.addNode("Sally");
		Node<String> molly = g.addNode("molly");
		Node<String> sam = g.addNode("sam");
		Node<String> nyc = g.addNode("NYC");
		
		final NamedRelationship hates = new NamedRelationship("HATES");
		final NamedRelationship dating = new NamedRelationship("DATING");
		final NamedRelationship employs = new NamedRelationship("EMPLOYS");
		final NamedRelationship leases_to = new NamedRelationship("LEASES_TO");
		final NamedRelationship lives_in = new NamedRelationship("LIVES_IN");



		
		g.addRelationship(hates, Direction.Directed, frank, bill);
		g.addRelationship(dating, Direction.Undirected, bill, sally);
		g.addRelationship(dating, Direction.Undirected, sam, molly);
		g.addRelationship(employs, Direction.Directed, sally, hank);
		g.addRelationship(leases_to, Direction.Directed, hank, mike);
		g.addRelationship(lives_in, Direction.Directed, mike, chicago);
		g.addRelationship(lives_in, Direction.Directed, hank, chicago);
		g.addRelationship(lives_in, Direction.Directed, frank, chicago);
		g.addRelationship(lives_in, Direction.Directed, bill, nyc);
		
		
		
		// this query returns anyone who is dating someone who lives in chicago.
		List<Node<?>> residents = g.hasRelation(lives_in).evaluate(new Evaluable() {

			@Override
			public <T> boolean evaluateNode(T data) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean evaluateNode(Node<?> data) {
				// TODO Auto-generated method stub
				System.out.println(data.getData());
				if (data.hasOutgoingNeighbor("Chicago", lives_in)) {
					return true;
				}
				return false;
			}
			
		});

		
		System.out.println("Results:");
		
		for (Node<?> n : residents) {
			System.out.println(n.getData());
		}
		


		
	}

}
