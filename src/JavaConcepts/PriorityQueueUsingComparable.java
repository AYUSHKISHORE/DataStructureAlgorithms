package JavaConcepts;
import java.util.PriorityQueue;

/*
 * In java, a priorityQueue is a type of queue that order its elements based on their natural ordering or according to a compartor provided at queue construction
 * 
 * When you use priorityQueue, the element with highest priority is served first
 * 
 * PriorityQueueUsingComparable implements Comparable<PriorityQueueUsingComparable> to define natural ordering by age which ensure priority queue will order objects based on their age.
 * priorityQueue : intialized with the PriorityQueueUsingComparable class , elements are added to queue using the add methode & because PriorityQueueUsingComparable implements Comparable the queue will automatically order the element by age
 * 
 * Polling: The poll method retrieves & remove the highest priority element ( the one with the smallest age in this case from the queue . this is repeated until queue is empty.
 * 
 * In java's priority queue , heap Datastructure is used internally specially priorityQueue is implement on binaryheap
 * Explanation
 * 
 * Insertion -> When we add an element in the priority queue element is added at the end and heapify is done
 * Removal: When you remove the highest priority element (Smallest in min-heap case) the root element is removed. The last element is moved to root element and heapify is done
 * 
 * We can use comparator also in priority queue
 * 
 */
public class PriorityQueueUsingComparable implements Comparable<PriorityQueueUsingComparable> {
	
	private String name;
	private int age;
	public PriorityQueueUsingComparable(String name, int age) {
		this.name=name;
		this.age=age;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	@Override
	public String toString() {
		return name +" ( "+age+" ); ";
	}
	
	@Override
	public int compareTo(PriorityQueueUsingComparable other) {
		return Integer.compare(this.age, other.age);
	}
	
	public static void main(String []args) {
		PriorityQueue<PriorityQueueUsingComparable> p = new PriorityQueue<>();
		p.add(new PriorityQueueUsingComparable("A",1));
		p.add(new PriorityQueueUsingComparable("B",3));
		p.add(new PriorityQueueUsingComparable("C",2));
		
		System.out.println("Printing queue before sorting");
		for(PriorityQueueUsingComparable p1 : p) {
			System.out.print(p1);
		}
		
		
		
		System.out.println("\n\nFetching queue"); // Note only during the time of retrieval queue return the element with highest priority more explanation above
		while(!p.isEmpty()) {
			System.out.println("peeked element = "+ p.peek()+" polled element = "+p.poll());
		}
		
	}
}
