package a;

import java.util.ArrayList;

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
    		System.out.println(this);
    	
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
    	double [][] aristas=new double[N][N];
    	for(int i=0;i<N;i++){
    		for(int j=0;j<N;j++){
    			aristas[i][j]=this.getPoint(i).dist(this.getPoint(j));
    		}
    	}
    	return new Graph(this,aristas);
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
        
    	
    	
    	
    }
    
    
}