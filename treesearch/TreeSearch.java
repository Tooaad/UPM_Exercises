package aed.treesearch;

import es.upm.aedlib.Position;
import es.upm.aedlib.set.*;
import es.upm.aedlib.positionlist.*;
import es.upm.aedlib.tree.*;

public class TreeSearch {
	public static Set<Position<String>> search(Tree<String> t, PositionList<String> searchExprs) {
		if (t == null || searchExprs == null || t.isEmpty() || searchExprs.isEmpty())
			return null;
		else {
			System.out.println(t);
			Set<Position<String>> set = new PositionListSet<Position<String>>();
			searchRec(t, searchExprs, searchExprs.first(), t.root(), set);
			return set;
		}
	}

	private static void searchRec(Tree<String> t, PositionList<String> searchExprs, Position<String> exprCursor,
			Position<String> treeCursor, Set<Position<String>> set) {
		if (exprCursor.element().equals(treeCursor.element()) || exprCursor.element().equals("*")) {
			exprCursor = searchExprs.next(exprCursor);
			if (exprCursor == null)
				set.add(treeCursor);
			else {
				for (Position<String> element : t.children(treeCursor))
					searchRec(t, searchExprs, exprCursor, element, set);
			}
		}
	}

	public static Tree<String> constructDeterministicTree(Set<PositionList<String>> paths) {
		GeneralTree<String> newTree = new LinkedGeneralTree<String>();
		if (paths == null)
			return null;
		for (PositionList<String> pathGroup : paths) {
			if (!pathGroup.isEmpty()) {
				if (newTree.isEmpty())
					newTree.addRoot(pathGroup.first().element());
			}
			addPathRec(newTree, newTree.root(), pathGroup, pathGroup.next(pathGroup.first()));
		}
		return newTree;
	}

	private static void addPathRec(GeneralTree<String> newTree, Position<String> treeCursor,
			PositionList<String> pathGroup, Position<String> pathGroupCursor) {
		if (pathGroupCursor != null) {
			for (Position<String> nextPos : newTree.children(treeCursor)) {
				if (pathGroupCursor.element().equals(nextPos.element())) {
					pathGroupCursor = pathGroup.next(pathGroupCursor); // Avanzo cursor del PositionList para saber que elementos anadir en la proxima recursion
					addPathRec(newTree, nextPos, pathGroup, pathGroupCursor); // Llamada a la funcion con el siguiente elemento ya que el elemento actual ya esta
					return;
				}
			}
			treeCursor = newTree.addChildLast(treeCursor, pathGroupCursor.element()); // Anade nuevo elemento en la n+1 posicion como hijo
			pathGroupCursor = pathGroup.next(pathGroupCursor); // Avanzo cursor del PositionList para saber que elementos anadir en la proxima recursion
			addPathRec(newTree, treeCursor, pathGroup, pathGroupCursor); // Llamada a la funcion con el siguiente elemento el cual se ha anadido al newTree
		}
	}
}