package aed.delivery;

import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.set.PositionListSet;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.graph.DirectedAdjacencyListGraph;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.set.HashTableMapSet;
import es.upm.aedlib.set.Set;
import java.util.Iterator;

public class Delivery<V> {

	private DirectedGraph<V, Integer> g;
	private Map<Integer, Vertex<V>> m;

	// Construct a graph out of a series of vertices and an adjacency matrix.
	// There are 'len' vertices. A negative number means no connection. A
	// non-negative number represents distance between nodes.
	public Delivery(V[] places, Integer[][] gmat) {
		this.g = new DirectedAdjacencyListGraph<>();
		m = new HashTableMap<>();
		int count = 0;
		for (V v : places) {
			Vertex<V> vert = g.insertVertex(v);
			m.put(count, vert);
			count++;
		}
		for (int i = 0; i < gmat.length; i++) {
			for (int j = 0; j < gmat[i].length; j++) {
				if (gmat[i][j] != null) {
					Vertex<V> start = m.get(i);
					Vertex<V> end = m.get(j);
					g.insertDirectedEdge(start, end, gmat[i][j]);
				}
			}
		}
	}

	// Just return the graph that was constructed
	public DirectedGraph<V, Integer> getGraph() {
		return this.g;
	}

	private static boolean eqNull(Object o1, Object o2) {
		return o1 == o2 || o1 != null && o1.equals(o2);
	}

	private boolean pathContainsVertex(PositionList<Vertex<V>> result, Vertex<V> v) {
		for (Vertex<V> vertex : result) {
			if (v.equals(vertex))
				return true;
		}
		return false;
	}

	private PositionList<Vertex<V>> nextVert(Vertex<V> vInicio, PositionList<Vertex<V>> result) {
		for (Edge<Integer> e : g.outgoingEdges(vInicio)) {
			if (!pathContainsVertex(result, g.endVertex(e))) {
				result.addLast(g.endVertex(e));
				nextVert(g.endVertex(e), result);
			}
		}
		if (result.size() < g.size() && !result.isEmpty()) {
			result.remove(result.last());
		}
		return result;
	}

	public PositionList<Vertex<V>> tour() {
		PositionList<Vertex<V>> result = new NodePositionList<>();
		for (Vertex<V> vInicio : g.vertices()) {
			result.addLast(vInicio);
			result = nextVert(vInicio, result);
			if (result.size() == g.size()) {
				return result;
			}
		}
		return null;
	}

	private PositionList<Edge<Integer>> ePath(PositionList<Vertex<V>> path) {
		PositionList<Edge<Integer>> result = new NodePositionList<>();
		Position<Vertex<V>> cursor = path.first();
		for (int i = 0; i < path.size() - 1; i++) {
			Vertex<V> v = cursor.element();
			Vertex<V> w = path.next(cursor).element();
			boolean found = false;
			for (Edge<Integer> n : g.outgoingEdges(v)) {
				for (Edge<Integer> m : g.incomingEdges(w)) {
					if (eqNull(n, m)) {
						result.addLast(n);
						found = true;
					}
					if (found) {
						break;
					}
				}
				if (found) {
					break;
				}
			}
			cursor = path.next(cursor);
		}
		return result;
	}

	public int length(PositionList<Vertex<V>> path) {
		int result = 0;
		PositionList<Edge<Integer>> e = ePath(path);
		for (Edge<Integer> r : e) {
			result += r.element();
		}
		return result;
	}

	public String toString() {
		return "Delivery";
	}
}
