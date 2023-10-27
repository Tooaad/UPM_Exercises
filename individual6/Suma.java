package aed.individual6;

import es.upm.aedlib.graph.Edge;
import es.upm.aedlib.graph.Vertex;
import es.upm.aedlib.graph.DirectedGraph;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.map.HashTableMap;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Suma {
	private static <E> int sum(Vertex<Integer> i, int s, DirectedGraph<Integer, E> g, Map<Vertex<Integer>, Integer> used) {
		for (Edge<E> j : g.outgoingEdges(i)) {
			Vertex<Integer> v = g.endVertex(j);
			if (!used.containsKey(v)) {
				used.put(v, v.element());
				s = v.element() + sum(v, s, g, used);
			}
		}
		return s;
	}

	public static <E> Map<Vertex<Integer>, Integer> sumVertices(DirectedGraph<Integer, E> g) {
		if (g.isEmpty()) {
			throw new IllegalArgumentException();
		}
		Map<Vertex<Integer>, Integer> result = new HashTableMap<>();
		int s = 0;
		for (Vertex<Integer> i : g.vertices()) {
			s = i.element();
			Map<Vertex<Integer>, Integer> used = new HashTableMap<>();
			used.put(i, i.element());
			result.put(i, sum(i, s, g, used));
		}
		return result;
	}
}