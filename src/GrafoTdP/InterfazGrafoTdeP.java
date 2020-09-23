package GrafoTdP;

public interface InterfazGrafoTdeP<V,E> {
	//Agrega el nodo �node� al grafo, si a�n no pertenec�a a la estructura
	public void addNode(int node);
	//Agrega un arco entre el nodo �node1� y el nodo �node2�, si a�n no exist�a el arco y ambos par�metros son nodos pertenecientes a la estructura
	public void addEdge(int node1, int node2);
	//Remueve el nodo �node� del grafo, si el par�metro es un nodo de la estructura.
	public void removeNode(int node);
	//Remueve el arco entre el nodo �node1� y el nodo �node2�, si el arco formado por los par�metros pertenecen a la estructura
	public void remove(int node1, int node2);
}
