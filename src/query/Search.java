package query;

import storage.Node;

public interface Search {

	public boolean hasNext();
	public Node<?> getNext();
}
