package myUtils;

import java.util.ArrayList;
import java.util.Random;

public class RandomClass {
	
	private Random rand;
	private ArrayList<Double> array;
	private int limit;
	
	public RandomClass() {
		this(10);
	}
	
	public RandomClass(int limit) {
		this.limit = limit;
		rand = new Random();
		array = new ArrayList<Double>(this.limit);
	}
	
	public double randomSingle(double min, double max) {
		return rand.nextDouble(min, max);
	}
	
	public ArrayList<Double> randomArray(double min, double max, int n) {
		this.clearArray();
		int i = 0;
		for (i = 0; i < n; i++) {
			this.array.add(randomSingle(min, max));
		}
		return this.getArray();
	}
	
	public ArrayList<Double> getArray() {
		return this.array;
	}
	
	public double getArrayNumber(int index) {
		return this.array.get(index);
	}
	
	public void clearArray() {
		// Re-Instantiate ArrayList to clear ArrayList
		array = new ArrayList<Double>(this.limit);
	}
	
	
}
