package Queue;

import java.util.*;
public class PriorityQueueImplementation2 {

	public static void main(String []args) {
		PriorityQueue<String> pQueue = new PriorityQueue<String>();
		pQueue.add("Geeks");
		pQueue.add("For");
		pQueue.add("Geeks");
		
		System.out.println("PriorityQueue = "+pQueue);
		
		Iterator it = pQueue.iterator();
		while(it.hasNext()) {
			System.out.println("Value = "+it.next());
		}
		
		pQueue.remove("Geeks");
		System.out.println("PriorityQueue after removal of Geeks" +pQueue);
		System.out.println("poll method = " +pQueue.poll());
		System.out.println("PriorityQueue after poll" +pQueue);
		System.out.println("peek method = " +pQueue.peek());
		System.out.println("Is pQueue empty?" +pQueue.isEmpty());
		pQueue.clear();
		System.out.println("Is pQueue empty?" +pQueue.isEmpty());

		//Different PriorityQueue implementation
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(2);
		pq.add(3);
		pq.add(2);
		
		System.out.println("pq =  "+pq);
		
		PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(pq);
		
		System.out.println("pq2 = "+pq2);
		
		
	
	
	}
}
