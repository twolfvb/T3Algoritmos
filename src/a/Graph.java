package a;

import java.util.ArrayList;

public class Graph {
	ArrayList<Arista> aristas= new ArrayList<Arista>();
	private Set puntos;
	public Graph(Set set, ArrayList<Arista> aristas) {
		this.puntos=set;
		this.aristas=aristas;

	}


	public Graph(Set set){
		this.puntos=set;
	}
	public Graph generarGrafo(){
		ArrayList<Set> componentesConexas=new ArrayList<Set>();
		for(int i=0;i<puntos.size();i++){
			Set a=new Set();
			a.add(puntos.getPoint(i));
			componentesConexas.add(a);
		}
		return null;



	}

}