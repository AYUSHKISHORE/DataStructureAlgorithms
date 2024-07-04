package Tree;


public class BinaryTreeUsingArrayTraversals {
	
	public static void main(String []args) {
	BinaryTreeArrayTraversal Bt = new BinaryTreeArrayTraversal(8);
	Bt.insertNode("A");
	Bt.insertNode("B");
	Bt.insertNode("C");
	Bt.insertNode("D");
	Bt.insertNode("E");
	Bt.insertNode("F");
	Bt.insertNode("G");
	
	Bt.print();
	
	
	System.out.println("\nPREORDER TRAVERSAL IN ARRAY");
	Bt.preorder(1);
	
	
	System.out.println("\nINORDER TRAVERSAL IN ARRAY");
	Bt.inorder(1);
	
	System.out.println("\nPOSTORDER TRAVERSAL IN ARRAY");
	Bt.postorder(1);
	
	System.out.println("\nLEVEORDER TRAVERSAL IN ARRAY");
	Bt.levelorder();
	
	}

}

class BinaryTreeArrayTraversal{
	String arr[];
	int lastUsedIndex=0;
	BinaryTreeArrayTraversal(int size){
		arr = new String[size];
	}
	
	boolean isFull() {
		if(arr.length -1 ==lastUsedIndex) {
			return true;
		}
		return false;
	}
	
	void insertNode(String val) {
		if(!isFull()) {
			arr[lastUsedIndex+1]=val;
			lastUsedIndex++;
			System.out.println("Value Inserted = "+val);
		}else {
			System.out.println("Array is Inserted");
		}
	}
	
	void print() {
		System.out.println("Printing the tree elements");
		for(int i=1;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}
	
	void preorder(int index) {
		//root -> left -> right
		
		if(index>lastUsedIndex) {
			return;
		}
		System.out.print(arr[index]+ " ");
		preorder(2*index);
		preorder(2*index + 1);
	}
	
	void inorder(int index) {
		//left -> root -> right
		
		if(index>lastUsedIndex) {
			return;
		}
		inorder(2*index);
		System.out.print(arr[index]+ " ");
		inorder(2*index + 1);
	}
	
	void postorder(int index) {
		//left -> right -> root
		
		if(index > lastUsedIndex) {
			return;
		}
		postorder(2*index);
		postorder(2*index + 1);
		System.out.print(arr[index]+" ");
	}
	
	void levelorder() {
		for(int i=1;i<=lastUsedIndex;i++) {
			System.out.print(arr[i]+" ");
		}
	}
	
	
}
