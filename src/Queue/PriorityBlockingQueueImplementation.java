package Queue;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueImplementation {
	public static void main(String []args) {
		
		//Difference Between PriorityBlockingQueue and PriorityQueue is PriorityBlockingQueue is thread safe
		Queue<Integer> pQueue = new PriorityBlockingQueue<Integer>();
		pQueue.add(10);
		pQueue.add(20);
		pQueue.add(15);
		
		//Priority Queue stores data by default as ASCII but it will print the insertion order
		System.out.println("pQueue print = "+pQueue);
		
		int peekedValue = pQueue.peek();
		System.out.println("peekedValue = "+peekedValue);
		System.out.println("pQueue print = "+pQueue);
		
		int polledValue = pQueue.poll();
		System.out.println("polledValue = "+polledValue);
		System.out.println("pQueue print = "+pQueue);
		
		int peekedValueNew = pQueue.peek();
		System.out.println("peekedValueNew = "+peekedValueNew);
		System.out.println("pQueue print = "+pQueue);
		
	}
}
