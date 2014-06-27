package a;

public class Point {
	
	private int x;
	private int y;
	
	public Point(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public double dist(Point a){
		return Math.sqrt(Math.pow(this.x - a.x, 2) + Math.pow(this.y - a.y, 2));
	}
}