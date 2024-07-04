package Queue;

import java.util.*;

public class QueueUsingLinkedList {
	public static void main(String []args) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i=0;i<5;i++) {
			queue.add(i); // add element in queue in rear if queue is full it return exception
		}
		
		System.out.println("Queue print = "+queue);
		
		int removedElement = queue.remove(); // throws exception when queue is empty
		System.out.println("removedElement = "+removedElement);
		
		System.out.println("Queue print = "+queue);
	
		int peekedElement = queue.peek();
		System.out.println("peekedElement = "+peekedElement);
		
		System.out.println("Queue print = "+queue);

		
		int pollElement = queue.poll(); // gives null when queue is empty
		System.out.println("pollElement = "+pollElement);
		
		System.out.println("Queue print = "+queue);

		
		int size = queue.size();
		System.out.println("size of queue"+size);
		
		queue.offer(10);// add element in queue in rear if queue is full it return false
		System.out.println("Queue print = "+queue);

		
	}
}
