package app;

import buffer.*;

public class MyApp {

	public static void main(String[] args) {
		
		int i;
		Stack stack = new Stack(10);
		Queue queue = new Queue(10);
		
		System.out.println("*** Test 1 ***");
		
		for(i=0;i<9;i++) {
			System.out.printf("[%d] Stack: ", i); 
			stack.push(i);
			System.out.printf("[%d] Queue: ", i); 
			queue.add(i);
		}
		
		for(i=0;i<=9;i++) {
			System.out.printf("[%d] Pop: %d, Delete: %d\n", i, stack.pop(), queue.delete()); 
		}
		
		System.out.println("*** Test 2 ***");
		
		for(i=0;i<15;i++) {
			System.out.printf("[%d] Stack: ", i); 
			stack.push(i);
			System.out.printf("[%d] Queue: ", i); 
			queue.add(i);
		}
		
		for(i=0;i<16;i++) {
			System.out.printf("[%d] Pop: %d, Delete: %d\n",i ,stack.pop(), queue.delete()); 
		}
	}
}
