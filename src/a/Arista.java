package a;

public class Arista {
	private double peso;
	private Point x;
	private Point y;
	public boolean usado=false;
	public Arista(Point x,Point y){
		this.x=x;
		this.y=y;
		this.peso=x.dist(y);
	}
	public double getPeso(){
		return this.peso;
	}
	public String toString(){
		return "Arista desde " +x.toString()+ " a " +y.toString()+" con peso " +peso;
	}
	public Point getFromPoint() {
		// TODO Auto-generated method stub
		return x;
	}
	public Point getToPoint() {
		// TODO Auto-generated method stub
		return y;
	}
	public void usar(){
		usado=true;
	}

}
