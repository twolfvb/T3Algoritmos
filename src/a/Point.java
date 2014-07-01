package a;

public class Point {
	
	private double x;
	private double y;
	
	public Point(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	public Point(String x, String y){
		this.x = Double.parseDouble(x);
		this.y = Double.parseDouble(y);
	}
	
	public double dist(Point a){
		return Math.sqrt(Math.pow(this.x - a.x, 2) + Math.pow(this.y - a.y, 2));
	}
	
	public boolean equals(Object b){
		if(b instanceof Point)
			return this.dist((Point)b)==0;
		else return false;
	}
	
	public String toString(){
		return "("+this.x+","+this.y+")";
	}
	
	
}