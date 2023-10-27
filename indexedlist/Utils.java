package aed.indexedlist;

import es.upm.aedlib.indexedlist.*;

public class Utils {
	public static <E> IndexedList<E> deleteRepeated(IndexedList<E> l) {
		IndexedList<E> newlist = new ArrayIndexedList<E>();
		for (int j = 0; j < l.size(); j++) {
			newlist.add(j, l.get(j)); // crea una nueva lista con los elementos de l
		}
		int count = 0;
		int i = 0;
		while (count < newlist.size() && i < newlist.size()) {
			if (count != i && newlist.get(count).equals(newlist.get(i))) { // el indice es distinto, pero el objeto es
																			// el mismo
				newlist.removeElementAt(count);
				count--; // se usa para contrarrestar el proximo aumento y ver el siguiente objeto que
							// cambiara de posicion
			}
			count++;
			if (count == newlist.size()) { // el recorrido del contador termino, se pasa al siguiente objeto de la lista
				count = 0;
				i++;
			}
		}
		return newlist;
	}
}
