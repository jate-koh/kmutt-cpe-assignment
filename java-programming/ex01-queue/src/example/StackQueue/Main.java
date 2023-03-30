package example.StackQueue;

public class Main {
	
	// Global for Queue
	public static int queue[];
	public static int queueCounter = 0;
	
	// Global for Stack
	public static int stack[];
	public static int stackCounter = 0;
	
	// Global for limiting and sample
	public static int limit = 20;
	public static int sample = 5;

	public static void main(String[] args) {
		
		int output[];
		output = new int[sample];
		
		// Define and Initialize Stack Array
		stack = new int[limit];
		
		// Define and Initialize Queue Array
		queue = new int[limit];
		
		// Test Stack
		System.out.println("Test Stack");
		output = testStack(sample);
		
		System.out.printf("\nStack Output: ");
		printArray(output, sample+1, false);
		System.out.printf("\n");
		
		// Test Queue
		System.out.println("Test Queue");
		output = testQueue(sample);
		
		System.out.printf("\nQueue Output: ");
		printArray(output, sample+1, false);
		System.out.printf("\n");
		
	}
	
	public static int[] testStack(int sample) {
		int output[];
		output = new int[limit];
		
		int i = 0;
		for(i=0; i<=sample; i++) {
			System.out.println("Pushing "+i*3);
			stackPush(i*3);
		}
		
		int j = 0;
		for(j=0; j<=sample; j++) {
			System.out.println("Popping "+output[j]);
			output[j] = stackPop();
		}
		
		return output;
	}
	
	public static void stackPush(int num) {
		if(stackCounter <= limit) { 
			stack = shiftRight(stack, stackCounter);
			stack[0] = num;
			stackCounter++; 
			printArray(stack, stackCounter, true);
		}
		else System.out.println("Stack Overflow");
	}
	
	public static int stackPop() {
		if(stackCounter > 0) { 
			int temp = stack[0];
			stack = shiftLeft(stack, stackCounter);
			stackCounter--;
			printArray(stack, stackCounter, true);
			return temp; 
		}
		else { 
			System.out.println("Stack Underflow");
			return 0;
		}
	}
	
	public static int[] testQueue(int sample) {
		
		int output[];
		output = new int[limit];

		int i = 0;
		for(i=0; i<=sample; i++) {
			System.out.println("Enqueue "+i*2);
			enqueue(i*2);
		}
		
		int j = 0;
		for(j=0; j<=sample; j++) {
			System.out.println("Dequeue "+output[j]);
			output[j] = dequeue();
		}
		
		return output;
	}

	public static int dequeue() {
		if(queueCounter > 0) {
			int temp = queue[0];
			queue = shiftLeft(queue, queueCounter);
			queueCounter--;
			printArray(queue, queueCounter, true);
			return temp; 
		}
		else {
			System.out.println("Empty Queue");
			return 0;
		}
	}
	
	public static void enqueue(int num) {
		if(queueCounter <= limit) { 
			queue[queueCounter] = num;
			queueCounter++;
			printArray(queue, queueCounter, true); 
		}
		else System.out.println("Queue Over Limit");
	}
	
	public static int[] shiftRight(int[] array, int counter) {
		int i = 0;
		int temp = 0;
		int tempArray[];
		
		tempArray = new int[counter+2];
		for(i=counter; i>=0; i--) {
			if(i == counter) { 
				temp = 0;
				tempArray[i+1]= 0;
			}
			else {
				temp = array[i];
				tempArray[i+1] = temp;
			}
		}
		return tempArray;
	}
	
	public static int[] shiftLeft(int[] array, int counter) {
		int i = 0;
		int temp = 0;
		int tempArray[];
		
		tempArray = new int[counter];
		for(i=0; i<counter; i++) {
			if(i != counter) { 
				temp = array[i+1];
				tempArray[i] = temp; 
			}
		}
		return tempArray;
	}
	
	public static void printArray(int[] array, int count, boolean element) {
		int i = 0;
		for(i=0; i<count; i++) {
			System.out.printf("%d ", array[i]);
		}
		if(element == true) System.out.printf("[Elements: %d]", count);
		System.out.printf("\n");
	}
}
