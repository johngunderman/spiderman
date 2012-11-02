spiderman
=========

A simple graph database.

How to Use
==========

Spiderman is designed to be easy to use, with query syntax following that used by neo4j.

To create a new graph:
<code>
Graph g = new Graph();
</code>

To add nodes to your graph:
<code>
Node n = new Node(String name, Object value);
g.addNode(n);
</code>

To connect two nodes together:
<code>
g.addRelation(Node a, Relation r, Direction d, Node b);
</code>

To query the graph:
<code>
QueryResult qr = g.hasRelation(Relation r, Direction d);
</code>

If you would like to limit your result set based on the contents of the nodes satisfying the relation you specified, you can provide an evaluator, which visits each node in the results set and prunes the set based on the result.
<code>
QueryResult qr = g.hasRelation(Relation r, Direction d).evaluator(Evaluatable e);
</code>


QueryResult allows for iteration over the nodes of the result set.

