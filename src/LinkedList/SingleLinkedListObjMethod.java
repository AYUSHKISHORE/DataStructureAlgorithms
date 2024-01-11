package LinkedList;

class Node{
	int data;
	Node next;
	Node(int d){
		data =d;
		next =null;
	}
}
public class SingleLinkedListObjMethod {
	
	private Node head;
	private Node tail;
	
	private void insertAtHead(int d) {
		if(head == null) {
			Node curr = new Node(d);
			head = curr;
			tail=head;
		}else {
			Node curr = head;
			while(curr.next!=null) {
				curr=curr.next;
			}
			curr.next = new Node(d);
			tail = curr.next;
			curr.next.next=null;
		}
	}
	
	 private void print() {
		 Node curr=head;
		 while(curr!=null) {
			 System.out.print(curr.data+" --> ");
			 curr=curr.next;
		 }
	 }

	public static void main(String[]args) {
		SingleLinkedListObjMethod list = new SingleLinkedListObjMethod();
		list.insertAtHead(1);
		list.insertAtHead(2);
		list.insertAtHead(3);
		list.insertAtHead(4);
		list.insertAtHead(5);
		list.print();
	}
}
