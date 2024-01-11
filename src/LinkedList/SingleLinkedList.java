package LinkedList;

public class SingleLinkedList {

	
	Node head;
	//A static class in Java is a class that cannot be instantiated. That is, 
	//we cannot create objects of a static class. We can only access its members using the class name itself. 
	//In other words, a static class is a class that only contains static members.
	
	
	// This inner class is made static 
	// so that main() can access it 
	static class Node{
		Node next;
		int data;
		Node(int d){
			data = d;
			next = null;
		}
	}
	
	public static SingleLinkedList insert(SingleLinkedList list, int data) {
		
		Node new_node = new Node(data);
		
		//if head is null
		if(list.head==null) {
			list.head = new_node;
			list.head.next=null;
		}
		
		//if head is not null
		Node curr = list.head;
		while(curr.next!=null) {
			curr = curr.next;
		}
		curr.next=new_node;
		curr.next.next=null;
		
		return list;
	}
	
	public static void PrintLinkedList(SingleLinkedList list) {
		
		Node curr = list.head;
		while(curr!=null) {
			System.out.print(curr.data+" -> ");
			curr=curr.next;
		}
	}
	
	
	public static void main(String []args) {
		SingleLinkedList list = new SingleLinkedList();
		list = insert(list,1);
		list = insert(list,2);
		list = insert(list,3);
		list = insert(list,4);
		list = insert(list,5);
		PrintLinkedList(list);
	}
}
