package evaluator;

import storage.Node;

/**
 * A class that implements the Evaluable interface is a visitor to a graph that
 * will determine if a given node should be included in a result set.
 * 
 * @author Varley
 * @since 1.0
 */
public interface Evaluable {

	/**
	 * This method will return true if the data passed in should be included in
	 * the outputs.
	 * 
	 * This method will find the appropriate {@link Node} in the graph that
	 * corresponds to the data.
	 * 
	 * @param data
	 *            The data to be considered. This method will find the
	 *            appropriate {@link Node} in the graph and evaluate it. If the
	 *            node does not exist in the graph, <code>false</code> will re
	 *            returned.
	 * @return This will return <code>true</code> if the data passed in should
	 *         be added to a list of outputs
	 * @see #evaluateNode(Node)
	 * @since 1.0
	 */
	public <T> boolean evaluateNode(T data);

	/**
	 * This method will return true if the data passed in should be included in
	 * the outputs.
	 * 
	 * @param data
	 *            The node to be considered.
	 * @return This will return <code>true</code> if the data passed in should
	 *         be added to a list of outputs
	 * @since 1.0
	 */
	public boolean evaluateNode(Node<?> data);
}
