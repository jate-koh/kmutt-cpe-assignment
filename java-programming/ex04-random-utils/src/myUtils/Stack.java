package myUtils;

public class Stack extends Buffer {
	
	private int top = 0;
	
	public Stack() {
		super();
	}
	
	public Stack(int size) {
		super(size);
	}
	
	public void push(double num) {
		if (this.top < this.limit) {
			this.items.add(num);
			this.top++;
		}
		else System.out.println("Stack Overflow");
		
	}
	
	public double pop() {
		if (this.top > 0) {			
			this.top--;
			double ans = this.items.get(top);
			this.items.remove(top);
			return ans;
		}
		else {
			System.out.println("Stack Underflow");
			return 0;
		}
	}
	
}
