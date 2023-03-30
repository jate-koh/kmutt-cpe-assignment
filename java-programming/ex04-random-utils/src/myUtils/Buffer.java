package myUtils;

import java.util.ArrayList;

public class Buffer {
	
	final ArrayList<Double> items;
	protected int limit;
	
	public Buffer() {
		this(10);
	}
	
	public Buffer(int size) {
		this.limit = size;
		items = new ArrayList<Double>(size);
	}
	
	public void showBuffer() {
		System.out.printf("{ ");
		for(int i=0; i<items.size(); i++)
			System.out.printf("%.2f ", items.get(i));
		System.out.printf(" }\n");
	}
	
	
}
