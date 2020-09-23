package GrafoTdP;

public class NodoDE<E> implements Position<E> {
	private E rotulo;
	private NodoDE<E> sig, ant;
	
	public NodoDE(E e) {
		rotulo=e;
		sig=null;
		ant=null;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return rotulo;
	}
	public void setElemento(E e) {
		rotulo=e;
	}
	public void setSiguiente(NodoDE<E> s) {
		sig=s;
	}
	public void setAnterior(NodoDE<E> a) {
		ant=a;
	}
	public NodoDE<E> getSiguiente(){
		return sig;
	}
	public NodoDE<E> getAnterior(){
		return ant;
	}
}
