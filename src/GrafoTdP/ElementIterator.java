package GrafoTdP;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Auxiliares.Position;
import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;

public class ElementIterator<E> implements Iterator<E> {
	private Position<E> cursor;
	private PositionList<E> Lista;
	
	public ElementIterator(PositionList<E> L) {
		Lista=L;
		if (Lista.isEmpty()) cursor=null;
		else
			try {
				cursor=L.first();
			} catch (EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return cursor!=null;
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		E e=null;
		if (cursor==null) throw new NoSuchElementException(); 
		else {
			try {
			e=cursor.element();
			if(cursor==Lista.last())
				cursor=null;
			else cursor=Lista.next(cursor);
			} catch(InvalidPositionException | EmptyListException | BoundaryViolationException e1) {e1.printStackTrace();}
		}
		return e;
	}

}
