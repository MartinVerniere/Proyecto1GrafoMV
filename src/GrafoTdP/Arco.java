package GrafoTdP;

public class Arco<V, E> implements Edge<E> {
		// En lista de arcos y de vertices
			private E rotulo;
			private Vertice<V,E> V1;
			private Vertice<V,E> V2;
			private Position<Arco<V,E>> PosenListadeArcos;
			public Arco(E rotulo, Vertice<V,E> A, Vertice<V,E> B) {
				this.rotulo=rotulo;
				V1=A;
				V2=B;
			}
			@Override
			public E element() { return rotulo;}
			public void setElement(E e) { rotulo=e;}
			public Vertice<V,E> getV1(){ return V1;}
			public Vertice<V,E> getV2(){ return V2;}
			public void setPosenListadeArcos(Position<Arco<V,E>> pos) { PosenListadeArcos=pos;}
			public Position<Arco<V,E>> getPosenListadeArcos() { return PosenListadeArcos;}
}
