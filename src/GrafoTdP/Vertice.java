package GrafoTdP;

public class Vertice<V, E> implements Vertex<V> {
		//En lista de arcos y vertices
			//Atributos
			private V rotulo;
			private Position<Vertice<V,E>> PosenListaVertices;
			//Constructor
			public Vertice(V rotulo) {
				this.rotulo=rotulo;
			}
			//Metodos
			public void setRotulo(V rotulo) { this.rotulo=rotulo;}
			public void setPosenListadeVertices(Position<Vertice<V,E>> pos) { PosenListaVertices=pos;}
			public Position<Vertice<V,E>> getPosenListaVertices(){ return PosenListaVertices;}
			public V element() { return rotulo;}
}	
