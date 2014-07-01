package a;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Set {
	private ArrayList<Point> puntos= new ArrayList<Point>();	
	public Set(){
	}
	
	public Set(Point [] a){
		for(int i=0;i<a.length;i++)
			puntos.add(a[i]);
	}
	
	public void add(Point a){
		puntos.add(a);
	}
	
	public void parse(String loc){
		File file = new File(loc);
		try {
			Scanner scanner = new Scanner(file);
			while (!scanner.nextLine().startsWith("NODE_"))
				;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] a = line.split(" ");
				if (a[0].startsWith("</pre>"))
					break;
				System.out.println(line);
				puntos.add(new Point(a[1],a[2]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
    public void remove(Point a){
    	for(int i=0;i<puntos.size();i++){
    		if(puntos.get(i).equals(a))
    			puntos.remove(i);
    	}
    }
    public int size(){
    	return this.puntos.size();
    }
    public Point getPoint(int i){
    	return this.puntos.get(i);
    }
    
    public void puntoMasCercano(Set P){
    	while(P.size()!=0){
    		Point minimo=P.getPoint(0);
    		for(int j=0;j<this.size();j++){
    		    Point c=this.getPoint(j);	
    		     for(int i=1;i<P.size();i++){
    			     Point p=P.getPoint(i);
    		    	 if(minimo.dist(c)>p.dist(c)){
    		    		minimo=p; 
    		    	 }
    		    		 
    		      }
    	    }
    		P.remove(minimo);
    		this.add(minimo);
    	
    }
    
    }
    public String toString(){
    	String t="El conjunto es \n";
    	for(int i=0;i<this.size();i++)
    		t+=" "+this.getPoint(i).toString()+" \n";
    	return t;
    		
    }
    public Graph crearGrafo(){
    	int N=this.size();
    	ArrayList<Arista> aristas= new ArrayList<Arista>();
    
    	for(int i=0;i<N;i++){
    		for(int j=i+1;j<N;j++){
    			aristas.add(new Arista(this.getPoint(i),this.getPoint(j)));
    	
    		}
    	}
    	return new Graph(this,aristas);
    }
    
    public boolean contains(Point point) {
    	return  this.puntos.contains(point);
    	}
    
    public void Union( Set B){
        for(int i=0;i<B.size();i++)
        	this.add(B.getPoint(i));
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
        C.add(p1);
        P.add(p2);
        P.add(p3);
        P.add(p4);
        P.add(p5);
        P.add(p6);
        C.puntoMasCercano(P);
        System.out.println(C);
        System.out.print(C.contains(new Point(34,55)));
        
        Set A = new Set();
        A.parse("/home/tw/git/T3Algoritmos/src/a/dj38.tsp");
        System.out.println(A.size());
    }

    
    
}