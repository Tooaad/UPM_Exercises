package aed.individual5;

import es.upm.aedlib.tree.Tree;
import es.upm.aedlib.Position;

public class Find {
	
	private static void findInPos(Position<String> cursor, String currentPath, String fileName, Tree<String> directory) {
		if (!cursor.element().equals(fileName)) {
			if (directory.isExternal(cursor)) { //nodo es hoja, vuelve a su padre
				return;
			}
			for (Position<String> c : directory.children(cursor)) { //recorre todos los hijos de los nodos intermedios
				findInPos (c, currentPath, fileName, directory);
			}
		}
		else {
			while (!cursor.equals(directory.root())) {
				currentPath = "/" + cursor.element() + currentPath; //añade al path desde el file hasta arriba
				cursor = directory.parent(cursor);
			}
			currentPath = "/" + cursor.element() + currentPath;
			Printer.println(currentPath);
		}
	}
	
	public static void find(String fileName, Tree<String> directory) {
		if (directory.isEmpty()) {
			throw new IllegalArgumentException();
		}
		Position<String> cursor = directory.root();
		String currentPath = "";
		findInPos(cursor, currentPath, fileName, directory);
	}
}
