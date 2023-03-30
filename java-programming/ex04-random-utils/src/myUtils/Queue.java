package myUtils;

public class Queue extends Buffer {
	
	private int count = 0;
	
	public Queue() {
		super();
	}
	
	public Queue(int size) {
		super(size);
	}
	
	public void add(double num) {
		
		// If counter is -1, reset back to 0
		if (this.count == -1) this.count = 0;
		
		// If limit is not surpassed
		if (this.count <= this.limit) {
			
			this.items.add(num);
			
			// Don't increment if count is at limit
			if(this.count != this.limit) this.count++;
			
		} else System.out.println("Queue Overflow");
	}
	
	public double delete() {
		
		// If it is not empty
		if (this.count > -1) {
			
			double res = this.items.get(0);
			this.items.remove(0);
			this.count--;
			
			return res;
		} else {
			System.out.println("Queue Empty");
			return 0;
		}
	}
	
}
