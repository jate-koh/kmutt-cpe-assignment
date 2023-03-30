package buffer;

public class MyBuffer {
	
	final int items[];
	
	public MyBuffer() {
		this(10);
	}
	
	public MyBuffer(int size) {
		items = new int[size];
	}
	
	public void showBuffer() {
		System.out.printf("{ ");
		for(int i=0; i<items.length; i++)
			System.out.printf("%d ", items[i]);
		System.out.printf(" }\n");
	}
	
	public double average() {
		double sum = 0;
		for(int i=0; i<items.length; i++)
			sum += items[i];
		return sum/items.length;
	}
}
