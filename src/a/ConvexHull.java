package a;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ConvexHull{
	
	public ArrayList<Point> execute(ArrayList<Point> points){
		ArrayList<Point> xSorted = (ArrayList<Point>) points.clone();
		Collections.sort(xSorted, new XCompare());

		int n = xSorted.size();

		Point[] lUpper = new Point[n];

		lUpper[0] = xSorted.get(0);
		lUpper[1] = xSorted.get(1);

		int lUpperSize = 2;

		for (int i = 2; i < n; i++)
		{
			lUpper[lUpperSize] = xSorted.get(i);
			lUpperSize++;

			while (lUpperSize > 2 && !rightTurn(lUpper[lUpperSize - 3], lUpper[lUpperSize - 2], lUpper[lUpperSize - 1]))
			{
				// Remove the middle point of the three last
				lUpper[lUpperSize - 2] = lUpper[lUpperSize - 1];
				lUpperSize--;
			}
		}

		Point[] lLower = new Point[n];

		lLower[0] = xSorted.get(n - 1);
		lLower[1] = xSorted.get(n - 2);

		int lLowerSize = 2;

		for (int i = n - 3; i >= 0; i--)
		{
			lLower[lLowerSize] = xSorted.get(i);
			lLowerSize++;

			while (lLowerSize > 2 && !rightTurn(lLower[lLowerSize - 3], lLower[lLowerSize - 2], lLower[lLowerSize - 1]))
			{
				// Remove the middle point of the three last
				lLower[lLowerSize - 2] = lLower[lLowerSize - 1];
				lLowerSize--;
			}
		}

		ArrayList<Point> result = new ArrayList<Point>();

		for (int i = 0; i < lUpperSize; i++)
		{
			result.add(lUpper[i]);
		}

		for (int i = 1; i < lLowerSize - 1; i++)
		{
			result.add(lLower[i]);
		}

		return result;
	}

	private boolean rightTurn(Point a, Point b, Point c)
	{
		return (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x) > 0;
	}

	private class XCompare implements Comparator<Point>
	{
		@Override
		public int compare(Point o1, Point o2) 
		{
			return (new Double(o1.x)).compareTo(new Double(o2.x));
		}
	}

	public Set Heuristic(Set P){
		Set C = new Set(execute(P.puntos));
		P = P.diferencia(C);
		while(P.size() > 0){
			ArrayList<Trio> minimos = new ArrayList<Trio>();
			for (Point p : P.puntos) {
				minimos.add(min1(p, C));
			}
			Trio nuevoVertice = min2(minimos,C);
			C.puntos.add(nuevoVertice.a1, nuevoVertice.p);
			P.remove(nuevoVertice.p);
		}
		return C;
		
	}
	
	public Double pesoTotal(ArrayList<Point> C){
		Double peso = C.get(C.size()-1).dist(C.get(0));
		
		for (int i = 1; i < C.size(); i++) {
			peso += C.get(i-1).dist(C.get(i));
		}
		return peso;
	}
	
	public Double Peso1(Point p, Point p1, Point p2){
		return p.dist(p1) + p.dist(p2) - p1.dist(p2);
	}
	public Double Peso2(Point p, Point p1, Point p2){
		return (p.dist(p1) + p.dist(p2))/p1.dist(p2);
	}
	public Trio min1(Point p, Set C){
		Double min = Peso1(p,C.getPoint(C.puntos.size()-1), C.getPoint(0));
		Trio minimo = new Trio(p, C.puntos.size()-1, 0);
		
		for (int i = 1; i < C.puntos.size(); i++) {
			Double potentialMin = Peso1(p, C.getPoint(i-1), C.getPoint(i));
			if(min > potentialMin){
				min = potentialMin;
				minimo = new Trio(p, i-1, i);
			}
		}
		return minimo;
	}
	public Trio min2(ArrayList<Trio> minimos, Set C){
		Double min = Peso2(	minimos.get(0).p, 
							C.getPoint(minimos.get(0).a1), 
							C.getPoint(minimos.get(0).a2));

		Trio minimo = minimos.get(0);

		for (Trio trio : minimos) {
			Double potentialNewMin = Peso2(	trio.p, 
					C.getPoint(trio.a1),
					C.getPoint(trio.a2));
			if(min > potentialNewMin){
				min = potentialNewMin;
				minimo = trio;
			}
		}
		return minimo;
	}
	
	public Trio min(Point p, Set C){ //NO USADO! :D
		Double min =  Peso1(p,C.getPoint(0), C.getPoint(1));
		Double newMin;
		ArrayList<Trio> minimums = new ArrayList<Trio>();
		minimums.add(new Trio(p, 0, 1));
		
		for (int i = 1; i < C.puntos.size(); i++) {
			Double potentialMin = Peso1(p, C.getPoint(i-1), C.getPoint(i));
			if(min > potentialMin){
				min = potentialMin;
				minimums.clear();
				minimums.add(new Trio(p, i-1, i));
			}
			else if(min == potentialMin){
				minimums.add(new Trio(p, i-1, i));
			}
		}
		Double potentialMin = Peso1(p, C.getPoint(0), C.getPoint(C.puntos.size()));
		if(min > potentialMin){
			min = potentialMin;
			minimums.clear();
			minimums.add(new Trio(p, C.puntos.size(), 0));
		}
		else if(min == potentialMin){
			minimums.add(new Trio(p, C.puntos.size(), 0));
		}
		
		if (minimums.size() == 1)
			return minimums.get(0);
		else{
			newMin = Peso2(	minimums.get(0).p, 
							C.getPoint(minimums.get(0).a1), 
							C.getPoint(minimums.get(0).a2));
			
			Trio newMinimum = minimums.get(0);
			
			for (Trio trio : minimums) {
				Double potentialNewMin = Peso2(	trio.p, 
												C.getPoint(trio.a1),
												C.getPoint(trio.a2));
				if(newMin > potentialNewMin){
					newMin = potentialNewMin;
					newMinimum = trio;
				}
			}
			return newMinimum;			
		}	
	}

	public static void main(String[] args) {
		ArrayList<String> datos = new ArrayList<String>();
		ArrayList<String> nombres = new ArrayList<String>();
		String path = "/home/tw/git/T3Algoritmos/src/a/data/";
		File file = new File("/home/tw/git/T3Algoritmos/src/a/DataLoc.txt");
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				nombres.add(line);
				line=path.concat(line);
				datos.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try{
		for (int i = 0; i < datos.size(); i++) {
			PrintWriter writer = new PrintWriter("/home/tw/git/T3Algoritmos/src/a/resultados/"+nombres.get(i));
			writer.println("path"+"\t"+"dataSize"+"\t"+"microseconds"+"\t"+"Solutionsize");
			Set A = new Set();
	        A.parse(datos.get(i));
	        ConvexHull ASD = new ConvexHull();
	        long start = System.nanoTime();
	        Set C = ASD.Heuristic(A);
	        long end = System.nanoTime();
	        Double peso = ASD.pesoTotal(C.puntos);
	        long microseconds = (end - start) / 1000;
	        String line = (datos.get(i)+"\t"+A.size()+"\t"+microseconds+"\t"+peso);
	        System.out.println(line);
	        writer.println(line);
			writer.close();
		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}