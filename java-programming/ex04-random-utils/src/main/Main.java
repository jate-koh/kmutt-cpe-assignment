package main;

import myUtils.*;

public class Main {

	public static void main(String[] args) {
		
		Stack stack = new Stack(10);
		Queue queue = new Queue(10);
		RandomClass rand = new RandomClass();
		
		System.out.println("**** STACK ****");
		rand.randomArray(0, 100, 50);
		
		for (int i = 0; i < 12; i++) {
			stack.push(rand.getArrayNumber(i));
			stack.showBuffer();
		}
		
		for (int i = 0; i < 15; i++) {
			stack.pop();
			stack.showBuffer();
		}
		
		
		System.out.println("**** QUEUE ****");
		
		for (int i = 0; i < 11; i++) {
			queue.add(rand.getArrayNumber(i));
			queue.showBuffer();
		}
		
		for (int i = 0; i < 13; i++) {
			queue.delete();
			queue.showBuffer();
		}
	
		
	}

}
