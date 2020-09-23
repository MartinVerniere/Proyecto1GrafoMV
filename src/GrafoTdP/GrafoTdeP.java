package GrafoTdP;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.ConsoleHandler;


public class GrafoTdeP<V,E> implements InterfazGrafoTdeP<V,E> {
	private PositionList<Vertice<Integer,Integer>> Listavertices;
	private PositionList<Arco<Integer,Integer>> ListaArcos;
	private static Logger Logger;
	
	public GrafoTdeP() {
		Listavertices= new ListaDoblementeEnlazada<Vertice<Integer,Integer>>();
		ListaArcos= new ListaDoblementeEnlazada<Arco<Integer,Integer>>();
		
		if (Logger==null) {
			Logger= Logger.getLogger(GrafoTdeP.class.getName());
			
			Handler hnd= new ConsoleHandler();
			hnd.setLevel(Level.FINE);
			Logger.addHandler(hnd);
			
			Logger.setLevel(Level.FINE);
			
			Logger rootLogger= Logger.getParent();
			for (Handler h: rootLogger.getHandlers()) {
				h.setLevel(Level.OFF);
			}
		}
	}

	@Override
	public void addNode(int node) {
		boolean encontre=false;
		
		for (Position<Vertice<Integer,Integer>> p: Listavertices.positions())
			if (p.element().element()==node) encontre=true;	
		
		if (!encontre) {
			Logger.fine("Se agrego un vertice con rotulo "+ node+ " al grafo");
			
			Vertice<Integer,Integer> v=new Vertice<Integer,Integer>(node);
			
			Listavertices.addLast(v);
		
			try {
				v.setPosenListadeVertices(Listavertices.last());
			} catch (EmptyListException e) {e.getMessage();}
			Logger.info("Hay "+ Listavertices.size()+" vertices en el grafo");
		}
		else {
			Logger.warning("El vertice con rotulo "+ node+" ya se encontraba en el grafo");
		}
	}

	@Override
	public void addEdge(int node1, int node2) {
		Vertice<Integer,Integer> v1=new Vertice<Integer,Integer>(node1);
		Vertice<Integer,Integer> v2= new Vertice<Integer,Integer>(node2);
		boolean encontroN1,encontroN2;
		encontroN1=false;
		encontroN2=false;
		
		for (Position<Vertice<Integer,Integer>> p: Listavertices.positions()) {
			if (p.element().element()==node1) {
				encontroN1=true;
			}
			else if (p.element().element()==node2) {
				encontroN2=true;
			}
		}
		if (!(encontroN1 || encontroN2)) { 
			Logger.warning("Los vertices "+node1+" y "+node2+" no se encuentran en el grafo");
		}
		else {
			if (!encontroN1) {
				Logger.warning("El vertice "+node1+" no se encuentra en el grafo");
				} 
			else { 
				if (!encontroN2) {
					Logger.warning("El vertice "+node2+" no se encuentra en el grafo");
				}
				else {
					Logger.info("Los dos vertices existen en el grafo");
					boolean encontroarco=false;
					for (Position<Arco<Integer,Integer>> a:ListaArcos.positions()) {
						if (((a.element().getV1().element()==node1) && (a.element().getV2().element()==node2))||((a.element().getV1().element()==node2) && (a.element().getV2().element()==node1))) {
							encontroarco=true;
						}
					}
					if (encontroarco) Logger.warning("El arco que se quiere crear ya existe en el grafo");
					else {
						Arco<Integer,Integer> e=new Arco<Integer,Integer>(null,v1,v2);
						ListaArcos.addLast(e);
						try {
							e.setPosenListadeArcos(ListaArcos.last());
						} catch (EmptyListException e1) { e1.getMessage();}
						Logger.fine("Se creo el arco con extremos "+node1+" y "+ node2);
					}
				}
			}
		}
		Logger.info("Existen "+ListaArcos.size()+" arcos en el grafo");
	}
	@Override
	public void removeNode(int node) {
		Position<Vertice<Integer, Integer>> p=null;
		boolean encontre=false;
		try {
			p = Listavertices.first();
			while (p!=null&&!encontre) {
				if (p.element().element()==node) {
					p.element().setRotulo(null);
					Listavertices.remove(p);
					encontre=true;
				}
				else {
					if (p==Listavertices.last()) {
						p=null;
					}
					else {
						p=Listavertices.next(p);
					}
				}
			}
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) { e.getMessage();}
		if (!encontre) {
			Logger.warning("No existe un vertice con rotulo "+node+" para remover");
		}
		else {
			Logger.fine("Se removio el nodo con rotulo "+node);
		}
		Logger.info("Hay "+ Listavertices.size()+" vertices en el grafo");
	}

	@Override
	public void remove(int node1, int node2) {
		Position<Arco<Integer,Integer>> p=null;
		boolean encontro=false;
		try {
			p=ListaArcos.first();
			while (p!=null && !encontro) {
				if (((p.element().getV1().element()==node1) && (p.element().getV2().element()==node2))||((p.element().getV1().element()==node2) && (p.element().getV2().element()==node1))) {
					p.element().setElement(null);
					ListaArcos.remove(p);
					encontro=true;
					Logger.fine("Se elimino el arco que una al vertice "+node1+ " y al vertice "+node2);
				}
				if (p==ListaArcos.last()){
					p=null;
				}
				else {
					p=ListaArcos.next(p);
				}
			}
		if (!encontro) {
			Logger.warning("No existe un arco que una al vertice "+ node1+ " y al vertice "+node2);
			}
		} catch (InvalidPositionException | BoundaryViolationException | EmptyListException e) { e.getMessage();}
		Logger.info("Existen "+ListaArcos.size()+" arcos en el grafo");
	}
}

