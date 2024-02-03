package LinkedList;



public class ReverseLinkedList {
	
	static class Node{
		int data;
		Node next;
		Node(int d){
			data=d;
			next=null;
		}
	}
	private Node head;
	
	public static ReverseLinkedList insert(ReverseLinkedList list, int d) {
		if(list.head == null) {
			Node n = new Node(d);
			list.head=n;
		}else {
			Node curr = list.head;
			while(curr.next!=null) {
				curr=curr.next;
			}
			Node new_node=new Node(d);
			curr.next=new_node;
		}
		return list;
	}
	
	public static void printList(ReverseLinkedList list) {
		Node curr = list.head;
		while(curr!=null) {
			System.out.print(curr.data+ "->");
			curr=curr.next;
		}
	}
	
	public static ReverseLinkedList ReverseList(ReverseLinkedList list) {
		//1 2 3 4 5
		if(list.head == null || list.head.next == null) {
			return list;
		}
		Node prvs = null;
		Node curr = list.head;
		while(curr!=null) {
			Node newNode = curr.next;
			curr.next = prvs;
			prvs = curr;
			curr = newNode;
		}
		list.head = prvs;
		return list;
	}

	public static void main(String []args) {
		
		ReverseLinkedList rlist = new ReverseLinkedList();
		rlist = insert(rlist,1);
		rlist = insert(rlist,2);
		rlist = insert(rlist,3);
		rlist = insert(rlist,4);
		rlist = insert(rlist,5);
		System.out.println("Normal LinkedList");
		printList(rlist);
		
		
		//This code has space Complexity as O(1)
		rlist=ReverseList(rlist);
		System.out.println("\nReverse LinkedList");
		printList(rlist);
		
		
	}
}
