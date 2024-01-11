package LinkedList;

public class SingleLinkedListOperation {

	//defining both head and tail so that insertion at both end can be at O(1) timecomplexity
	Node head;
	Node tail;

	//A static class in Java is a class that cannot be instantiated. That is, 
	//we cannot create objects of a static class. We can only access its members using the class name itself. 
	//In other words, a static class is a class that only contains static members.	
	
	static class Node{
		Node next;
		int data;
		Node(int d){
			data = d;
			next = null;
		}
	}

	

	//Add at head

	public static SingleLinkedListOperation insertAtHead(SingleLinkedListOperation list , int d) {
		if(list.head == null){
			list.head = new Node(d);
			list.head.next = null;
			list.tail = list.head;
		}else {
			Node curr = list.head;
			while(curr.next!=null) {
				curr=curr.next;
			}
			curr.next = new Node(d);
			list.tail = curr.next;
		}
		return list;
	}

	

	//Add at tail

	public static SingleLinkedListOperation insertAtTail(SingleLinkedListOperation list , int d) {

		if(list.tail == null) {
			list.head = new Node(d);
			list.tail = list.head;
		}else {
			Node curr = new Node(d);
			list.tail.next = curr;
			list.tail = list.tail.next;
		}

		return list;
	}

	//Add at index
	public static SingleLinkedListOperation insertAtIndex(SingleLinkedListOperation list, int index, int value) {
		Node curr = list.head;
		for(int i=0;i<index-1;i++) {
			curr = curr.next;
		}
		Node newValue = new Node(value);
		newValue.next = curr.next;
		curr.next =newValue;
		return list;
	}

	public static void print(SingleLinkedListOperation list) {
		Node curr = list.head;
		while(curr!=null) {
			System.out.print(curr.data+">");
			curr=curr.next;
		}
		System.out.println();
	}

	public static SingleLinkedListOperation deleteValueAtIndex(SingleLinkedListOperation list, int index) {
		Node curr = list.head;
		for(int i=0;i<index-1;i++) {
			curr=curr.next;
		}
		curr.next=curr.next.next;
		return list;
	}

	

	public static int getValueAtIndex(SingleLinkedListOperation list, int index) {
		Node curr = list.head;
		for(int i=0;i<index-1;i++) {
			curr=curr.next;
		}

		return curr.next.data;
	}
	
	public static void main(String []args) {
		SingleLinkedListOperation list = new SingleLinkedListOperation();
		list = insertAtHead(list,1);
		list = insertAtHead(list,2);
		list = insertAtHead(list,3);
		list = insertAtHead(list,4);
		list = insertAtHead(list,5);
		list = insertAtTail(list,10);
		list = insertAtTail(list,7);
		System.out.println("Head Based Insertion");

		print(list);

		SingleLinkedListOperation listTail = new SingleLinkedListOperation();
		listTail = insertAtTail(listTail,6);
		listTail = insertAtTail(listTail,7);
		listTail = insertAtTail(listTail,8);
		listTail = insertAtTail(listTail,9);
		listTail = insertAtTail(listTail,10);
		System.out.println("Tail Based Insertion");
		print(listTail);

		

		//add at index
		list = insertAtIndex(list,3,77);
		System.out.println("After Insertion 3rd index");
		print(list);

	
		//delete at index
		list = deleteValueAtIndex(list,3);
		System.out.println("After Deleting 3rd index");
		print(list);


		//get index value
		int val = getValueAtIndex(list,4);
		System.out.println("Value found at given index"+4+"value == "+val);
	}

}