package buffer;

public class Stack extends MyBuffer {
	
	private int top;
	
	public Stack() {
		this(10);
	}
	
	public Stack(int size) {
		super(size);
		this.top = 0;
	}
	
	public void push(int x) {
		
		if(this.top < this.items.length - 1) {
			this.items[this.top] = x;
			System.out.println("Pushed "+x+" [ top: "+this.top+" ] ");
			this.top++;
		}
		else if(this.top == this.items.length - 1) {
			this.items[this.top] = x;
		}
		else System.out.println("Can't Push, Stack Overflow");
	}
	
	public int pop() {

		if(this.top > 0) {
			int temp = this.items[top-1];
			this.top--;
			return temp;
		}
		else {
			System.out.println("Stack Empty");
			return 0;
		}
	}
}
