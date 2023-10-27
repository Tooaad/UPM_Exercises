package aed.individual4;

import java.util.Iterator;
import java.util.NoSuchElementException;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.PositionList;

public class MultiSetListIterator<E> implements Iterator<E> {
	PositionList<Pair<E, Integer>> list;

	Position<Pair<E, Integer>> cursor;
	int counter;
	Position<Pair<E, Integer>> prevCursor;

	public MultiSetListIterator(PositionList<Pair<E, Integer>> list) {
		this.list = list;
		this.prevCursor = null;
		if (!list.isEmpty()) {
			this.cursor = list.first();
			this.counter = cursor.element().getRight();
		}
	}
	
	public boolean hasNext() {
		return cursor != null;
	}
	
	public E next() throws NoSuchElementException{
		if (!hasNext())
			throw new NoSuchElementException();
		E elem = cursor.element().getLeft();
		if (counter > 0) {
			prevCursor = cursor;
			counter--;
			if (counter == 0) {
				cursor = list.next(cursor);
				if (hasNext())
					counter = cursor.element().getRight();
			}
		}
		return elem;
	}

	public void remove() throws IllegalStateException{
		if (prevCursor == null)
			throw new IllegalStateException();
		if (prevCursor.element().getRight() > 1)
			prevCursor.element().setRight(prevCursor.element().getRight() - 1);
		else
			list.remove(prevCursor);
		prevCursor = null;
	}
}