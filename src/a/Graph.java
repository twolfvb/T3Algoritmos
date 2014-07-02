package a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
	public Graph generarMST(){
		ArrayList<Set> componentesConexas=new ArrayList<Set>();
		ArrayList<Arista> xSorted = (ArrayList<Arista>) aristas.clone();
		ArrayList<Arista>  result= new ArrayList<Arista>();
		Collections.sort(xSorted, new XCompare());
		for(int i=0;i<puntos.size();i++){
			Set a=new Set();
			a.add(puntos.getPoint(i));
			componentesConexas.add(a);}
	
			
			
			for(int p=0;p<xSorted.size() || componentesConexas.size()!=1;p++){
			Arista ab=xSorted.get(p);
			int i=encontrarIndice(componentesConexas,ab.getFromPoint());
			int j=encontrarIndice(componentesConexas,ab.getToPoint());
			
			if(i!=j){
				if(i>j) {
					componentesConexas.get(i).Union(componentesConexas.get(j));
					componentesConexas.remove(j);
					
				}
				else 
					componentesConexas.get(j).Union(componentesConexas.get(i));
				    componentesConexas.remove(i);
				    
			}
			
			
			
		
			
		}
		return new Graph(this.puntos,result);



	}
	private int encontrarIndice(ArrayList<Set> componentesConexas,
			Point fromPoint) {
		int i=0;
		boolean encontrado=false;
		while(i<componentesConexas.size()){
			if(componentesConexas.get(i).contains(fromPoint)){
				encontrado=true;
				break;
			
			}
			i++;
		}
		if(encontrado) return i;
		else return -1;
		
	}
	public static Set getRecorrido(String loc){
		Set a=new Set();
		a.parse(loc);
		Graph g=a.crearGrafo();
		g=g.generarMST();
		
		
	
	}
	public void formarRecorrido(){
		
	}

	private class XCompare implements Comparator<Arista>
    {
            @Override
            public int compare(Arista o1, Arista o2) 
            {
                    return (new Double(o1.getPeso()).compareTo(new Double(o2.getPeso())));
            }
    }
	
	
	
	
	
	
	
	public static void main(String [] args){
    	Set C= new Set();
    	Set P=new Set();
    	Point p1=new Point(34,55);
    	Point p2=new Point(1,1);
    	Point p3=new Point(2,3);
    	Point p4=new Point(5,8);
    	Point p5=new Point(13,21);
    	Point p6=new Point(0,0);
        P.add(p1);
        P.add(p2);
        P.add(p3);
        P.add(p4);
        P.add(p5);
        P.add(p6);
        ArrayList<Arista> aristas= new ArrayList<Arista>();
        aristas.add(new Arista(p1,p2));
        aristas.add(new Arista(p1,p3));
        aristas.add(new Arista(p1,p4));
        aristas.add(new Arista(p1,p5));
        aristas.add(new Arista(p1,p6));
        aristas.add(new Arista(p2,p3));
        aristas.add(new Arista(p2,p4));
        aristas.add(new Arista(p2,p5));
        aristas.add(new Arista(p2,p6));
        aristas.add(new Arista(p3,p4));
        aristas.add(new Arista(p3,p5));
        aristas.add(new Arista(p3,p6));
        aristas.add(new Arista(p4,p5));
        aristas.add(new Arista(p4,p6));
        aristas.add(new Arista(p5,p6));
        
        Graph g=new Graph(P,aristas);
        g.generarGrafo();

    }


}