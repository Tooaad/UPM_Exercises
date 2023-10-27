package aed.iteradores;


import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;
import java.util.Iterator;
import es.upm.aedlib.indexedlist.*;

public class EjemplosIteradores {

	private static boolean eqNull(Object o1, Object o2) {
		return o1 == o2 || o1 != null && o1.equals(o2);
	}

	public static <E> void show(Iterable<E> iter) {
		Iterator<E> it = iter.iterator();
		while (it.hasNext()) {
			if (it.next() != null) {
				System.out.println(it.next());
			}
		}
	}

	public static Integer suma(Iterable<Integer> iterable) {
		Iterator<Integer> it = iterable.iterator();
		Integer result = 0;
		while (it.hasNext()) {
			Integer value = it.next();
			if (value != null) {
				result += value;
			}
		}
		return result;
	}

	public static <E> boolean member(Iterable<E> elements, E e) {
		Iterator<E> it = elements.iterator();
		boolean encontrado = false;
		while (it.hasNext() && !encontrado) {
			encontrado = !eqNull(it.next(), e);
		}
		return encontrado;
	}

	public static <E> boolean iguales(Iterable<E> iterable1, Iterable<E> iterable2) {
		Iterator<E> it1 = iterable1.iterator();
		Iterator<E> it2 = iterable2.iterator();
		boolean iguales = true;
		while (it1.hasNext() && it2.hasNext() && iguales) {
			iguales = eqNull(it1.next(), it2.next());
		}
		return it1.hasNext() == it2.hasNext() && iguales;
	}
	
	public static <E> boolean subseteq (Iterable<E> l1, Iterable<E> l2) {
		Iterator<E> it1 = l1.iterator();
		boolean esSubset = true;
		while (it1.hasNext() && esSubset) {
			esSubset = member(l2, it1.next());
		}
		return esSubset;
	}
	
	public static boolean estaOrdenado (Iterable<Integer> elements) {
		Iterator<Integer> it = elements.iterator();
		if (!it.hasNext()) {
			return true;
		}
		Integer anterior = it.next();
		boolean ordenada = true;
		while (it.hasNext() && ordenada) {
			Integer actual = it.next();
			ordenada = anterior.compareTo(actual) <= 0;
			anterior = actual;
		}
		return ordenada;
	}
	
	static Integer minimo (Iterable<Integer> iterable) {
		return 0;
	}

	public static void main(String[] args) {
		IndexedList<Integer> list = new ArrayIndexedList<>(new Integer[] {1,4,null,null,null,5});
		PositionList<Integer> list2 = new NodePositionList<>(new Integer[] {1,4,2,3,5,8});
		IndexedList<Integer> list3 = new ArrayIndexedList<>(new Integer[] {1,4,2,3,5,8});
				
		System.out.println(list);
		
		System.out.println(suma(list));
		
		System.out.println(member(list, list2.first().element()));
		
		System.out.println(iguales(list2,list3));
	}
}
