package buffer;

public class Queue extends MyBuffer {
	
	private int head;
	private int tail;
	private int count;
	
	public Queue() {
		this(10);
	}
	
	public Queue(int size) {
		super(size);
		this.tail = -1; // Empty Queue, head is -1
		this.head = -1; // Empty Queue, tail is -1
		this.count = 0;
	}
	
	public void add(int x) {
		if(this.count == 0) {
			this.head = 0;
			this.tail = 0;
		}
		
		if(this.count < this.items.length) {			
			this.items[tail] = x;
			System.out.println("Added "+x+" [ head: "+this.head+" ] [ tail: "+this.tail+" ] ");
			this.count++;
			this.tail++;
		}
		else System.out.println("Can't Add, Queue Overflow");
	}
	
	public int delete() {
		if(this.count > 0) {
			int temp = this.items[head];
			this.head++;
			this.count--;
			
			if(this.count == 0) { // If Queue Empty, set head and tail to -1
				this.head = -1;
				this.tail = -1;
			}
			return temp;	
		}
		else {
			System.out.println("Queue Empty");
			return 0;
		}
	}
}
