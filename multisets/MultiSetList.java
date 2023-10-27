package aed.multisets;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;
import es.upm.aedlib.positionlist.NodePositionList;

/**
 * An implementation of a multiset using a positionlist.
 */
public class MultiSetList<Element> implements MultiSet<Element> {

	/**
	 * Datastructure for storing the multiset.
	 */
	private PositionList<Pair<Element, Integer>> elements;

	private int size;

	/**
	 * Constructs an empty multiset.
	 */
	public MultiSetList() {
		this.elements = new NodePositionList<Pair<Element, Integer>>();
	}

	private static boolean eqNull(Object o1, Object o2) {
		return o1 == o2 || o1 != null && o1.equals(o2);
	}

	private Position<Pair<Element, Integer>> buscarElement(Element elem) {
		Position<Pair<Element, Integer>> cursor = elements.first();
		Position<Pair<Element, Integer>> pos = null;
		while (cursor != null && pos == null) {
			Pair<Element, Integer> pair = cursor.element();
			if (eqNull(pair.getLeft(), elem)) {
				pos = cursor;
			}
			cursor = elements.next(cursor);
		}
		return pos;
	}

	@Override
	public void add(Element elem, int n) throws IllegalArgumentException {
		if (n < 0) {
			throw new IllegalArgumentException("n debe ser mayor que 0");
		} else if (n > 0) {
			Position<Pair<Element, Integer>> pos = buscarElement(elem);
			if (pos != null) {
				Pair<Element, Integer> pair = pos.element();
				pair.setRight(pair.getRight() + n);
			} else {
				Pair<Element, Integer> newPair = new Pair<Element, Integer>(elem, n);
				elements.addLast(newPair);
			}
			size += n;
		}
	}

	@Override
	public void remove(Element elem, int n) throws IllegalArgumentException {
		if (n < 0 || count(elem) < n) {
			throw new IllegalArgumentException();
		} 
		else if (n > 0) {
			Position<Pair<Element, Integer>> pos = buscarElement(elem);
			if (n < pos.element().getRight())
				pos.element().setRight(pos.element().getRight() - n);
			else
				elements.remove(pos);
			size -= n;
		}
	}

	@Override
	public int count(Element elem) {
		Position<Pair<Element, Integer>> pos = buscarElement(elem);
		int counter = 0;
		if (pos != null) {
			Pair<Element, Integer> pair = pos.element();
			counter = pair.getRight();
		}
		return counter;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public PositionList<Element> allElements() {
		PositionList<Element> newList = new NodePositionList<Element>();
		if (elements != null) {
			Position<Pair<Element, Integer>> cursor;
			for (cursor = elements.first(); cursor != null; cursor = elements.next(cursor)) {
				for (int i = 0; i < cursor.element().getRight(); i++) {
					newList.addFirst(cursor.element().getLeft());
				}
			}
		}
		return newList;
	}

	@Override
	public MultiSet<Element> intersection(MultiSet<Element> s) {
		MultiSet<Element> newSet = new MultiSetList<Element>();
		for (Pair<Element, Integer> p : elements) {
			newSet.add(p.getLeft(), Math.min(p.getRight(), s.count(p.getLeft())));
		}
		return newSet;
	}
	
	@Override
	public MultiSet<Element> sum(MultiSet<Element> s) {
		MultiSet<Element> newSet = new MultiSetList<Element>();
		for (Pair<Element, Integer> elemPointer : elements) {
			newSet.add(elemPointer.getLeft(), elemPointer.getRight());
		}
		for (Element sPointer : s.allElements()) {
			newSet.add(sPointer, 1);
		}
		return newSet;
	}

	@Override
	public MultiSet<Element> minus(MultiSet<Element> s) {
		MultiSet<Element> newSet = new MultiSetList<Element>();
		if (elements != null) {
			for (Pair<Element, Integer> elemPointer : elements) {
				newSet.add(elemPointer.getLeft(), elemPointer.getRight());
			}
			for (Element sPointer : newSet.intersection(s).allElements()) {
				newSet.remove(sPointer, 1);
			}
		}
		return newSet;
	}
}
