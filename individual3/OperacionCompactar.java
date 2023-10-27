package aed.individual3;

//import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import java.util.Iterator;

public class OperacionCompactar {

	private static boolean eqNull(Object o1, Object o2) {
		return o1 == o2 || o1 != null && o1.equals(o2);
	}

	/**
	 * Metodo que reduce los elementos iguales consecutivos de una lista a una unica
	 * repeticion
	 * 
	 * @param lista Lista de entrada
	 * @return Lista nueva compactada sin elementos iguales consecutivos
	 */
	public static <E> PositionList<E> compactar(Iterable<E> lista) throws IllegalArgumentException {
		PositionList<E> result = new NodePositionList<E>();
		if (lista == null) {
			throw new IllegalArgumentException();
		}
		else {
			Iterator<E> it = lista.iterator();
			E i = it.next();
			result.addLast(i);
			while (it.hasNext()) {
				E j = it.next();
				if (!eqNull(result.last().element(), j)) {
					result.addLast(j);
				}
			}
		}
		return result;
	}
}
