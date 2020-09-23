package GrafoTdP;

import java.util.Iterator;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;

public class ListaDoblementeEnlazada<E> implements PositionList<E> {
	private int cant;
	private NodoDE<E> header,trailer;
	
	public ListaDoblementeEnlazada() {
		cant=0;
		header=new NodoDE<E>(null);
		trailer=new NodoDE<E>(null);
		header.setSiguiente(trailer);
		trailer.setAnterior(header);
	}

	@Override
	public int size() {
		return cant;
	}

	@Override
	public boolean isEmpty() {
		return (cant==0);
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if (isEmpty()) throw new EmptyListException("Lista vacia");
		return header.getSiguiente();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if (isEmpty()) throw new EmptyListException("Lista vacia");
		return trailer.getAnterior();
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		NodoDE<E> n =checkposition(p);
		try {
			if (n==last()) throw new BoundaryViolationException("No hay siguiente del ultimo");
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return n.getSiguiente();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		NodoDE<E> n =checkposition(p);
		try {
			if (n==first()) throw new BoundaryViolationException("No hay anterior del primero");
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return n.getAnterior();
	}

	@Override
	public void addFirst(E element) {
		NodoDE<E> nuevo=new NodoDE<E>(element);
		nuevo.setAnterior(header);
		nuevo.setSiguiente(header.getSiguiente());
		header.getSiguiente().setAnterior(nuevo);
		header.setSiguiente(nuevo);
		cant++;
	}

	@Override
	public void addLast(E element) {
		NodoDE<E> nuevo=new NodoDE<E>(element);
		nuevo.setSiguiente(trailer);
		nuevo.setAnterior(trailer.getAnterior());
		trailer.getAnterior().setSiguiente(nuevo);
		trailer.setAnterior(nuevo);
		cant++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		NodoDE<E> n=checkposition(p);
		NodoDE<E> nuevo=new NodoDE<E>(element);
		nuevo.setAnterior(n);
		nuevo.setSiguiente(n.getSiguiente());
		n.getSiguiente().setAnterior(nuevo);
		n.setSiguiente(nuevo);
		cant++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		NodoDE<E> n=checkposition(p);
		NodoDE<E> nuevo=new NodoDE<E>(element);
		nuevo.setAnterior(n.getAnterior());
		nuevo.setSiguiente(n);
		n.getAnterior().setSiguiente(nuevo);
		n.setAnterior(nuevo);
		cant++;
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		if (isEmpty()) throw new InvalidPositionException("Error lista vacia");
		NodoDE<E> n=checkposition(p);
		NodoDE<E> siguiente = n.getSiguiente();
		NodoDE<E> anterior = n.getAnterior();
		siguiente.setAnterior(anterior);
		anterior.setSiguiente(siguiente);
		n.setAnterior(null);
		n.setSiguiente(null);
		E aux = n.element();
		n.setElemento(null);
		cant--;
		return aux;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		NodoDE<E> n=checkposition(p);
		E aux = n.element();
		n.setElemento(element);
		return aux;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> It= new ListaDoblementeEnlazada<Position<E>>();
		NodoDE<E> n=header.getSiguiente();
		while(!(n.element()==null)) {
			It.addLast(n);
			n=n.getSiguiente();
		}
		return It;
	}
	
	private NodoDE<E> checkposition(Position<E> p) throws InvalidPositionException {
		if (p==null) throw new InvalidPositionException("Pos nula");
		try {
			return (NodoDE<E>) p;
		} catch (ClassCastException e) { throw new InvalidPositionException("Error en el casteo");}
	}
}
