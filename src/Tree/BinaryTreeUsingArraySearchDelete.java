package Tree;

public class BinaryTreeUsingArraySearchDelete {

	public static void main(String []args) {
		
		BinaryTreeArrayImplementation Bt = new BinaryTreeArrayImplementation(8);
		Bt.insert("A"); //TIME - O(1) SPACE - O(1)
		Bt.insert("B");
		Bt.insert("C");
		Bt.insert("D");
		Bt.insert("E");
		Bt.insert("F");
		Bt.insert("G");
		
		//Print the tree
		Bt.print();// O/P-> A B C D E F G  //TIME - O(N) SPACE - O(1)
		
		//Remove the Element C and Replace with G
		Bt.deleteTargetNode("C");// O/P-> A B G D E F  //TIME - O(N) SPACE - O(1)
		System.out.println("After Removing C and Replace with C");
		Bt.print();
		
		//Remove the Element G and replace with F 
		Bt.deleteTargetNode("G"); // O/P-> A B F D E //TIME - O(N) SPACE - O(1)
		System.out.println("After Removing C and Replace with C");
		Bt.print();
		
		
		//Delete full tree
		Bt.deleteCompleteTree(); //TIME - O(1) SPACE - O(1)
	
	}
}

class BinaryTreeArrayImplementation{
	String arr[];
	int lastUsedIndex=0;
	
	BinaryTreeArrayImplementation(int size){
		arr=new String[size];
	}
	
	boolean isFull() {
		if(arr.length-1 == lastUsedIndex) {
			return true;
		}
		return false;
	}
	
	void insert(String val) { //TIME - O(1) SPACE - O(1)
		if(!isFull()) {
			arr[lastUsedIndex+1]=val;
			lastUsedIndex++;
			System.out.println("Inserted Value "+val);
		}else {
			System.out.println("Array is full");
		}
	}
	
	void print() { //TIME - O(N) SPACE - O(1)
		for(int i=1;i<=lastUsedIndex;i++) {
			System.out.print(arr[i]+ " ");
		}
		System.out.println();
	}
	
	int findIndexOfElementDelete(String targetEle) { //TIME - O(N) SPACE - O(1)
		for(int i=1;i<=lastUsedIndex;i++) {
			if(targetEle == arr[i]) {
				return i;
			}
		}
		return -1;
	}
	
	
	void deleteTargetNode(String targetEle) { //TIME - O(1) SPACE - O(1)
		int indexToBeRemoved = findIndexOfElementDelete(targetEle);
		
		if(indexToBeRemoved != -1) {
			arr[indexToBeRemoved]=arr[lastUsedIndex];
			lastUsedIndex--;
		}
		System.out.println("Element =" +targetEle +" is Deleted");
	}
	
	//Delete Complete Tree
	void deleteCompleteTree() { //TIME - O(1) SPACE - O(1)
		try {
			arr=null;
			System.out.println("TREE IS DELETED");
		}catch(Exception e) {
			System.out.println("Error deleting the tree");
		}
	}
}
