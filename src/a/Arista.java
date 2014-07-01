package a;

public class Arista {
	private double peso;
	private Point x;
	private Point y;
	public Arista(Point x,Point y){
		this.x=x;
		this.y=y;
		this.peso=x.dist(y);
	}

}
