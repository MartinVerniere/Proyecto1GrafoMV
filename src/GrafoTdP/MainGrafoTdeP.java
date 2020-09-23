package GrafoTdP;

public class MainGrafoTdeP {
	public static void main(String[] args) {
		GrafoTdeP<Integer,Integer> Migrafo= new GrafoTdeP<Integer,Integer>();
		
		Migrafo.addNode(1);
		Migrafo.addNode(2);
		Migrafo.addNode(3);
		Migrafo.addNode(4);
		
		Migrafo.addEdge(1, 2);
		Migrafo.addEdge(2, 3);
		Migrafo.addEdge(3, 4);
		
		Migrafo.remove(3, 4);
		Migrafo.remove(1, 3); //No existe
		
		Migrafo.removeNode(4);
		Migrafo.removeNode(5); //No existe
		
		
	}

}
