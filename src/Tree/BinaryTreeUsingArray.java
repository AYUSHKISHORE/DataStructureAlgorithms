package Tree;

public class BinaryTreeUsingArray {
	public static void main(String[]args) {
		BinaryTreeArray Bt = new BinaryTreeArray(5);
		Bt.insert("A");
		Bt.insert("B");
		Bt.insert("C");
		Bt.insert("D");
		Bt.insert("E");
		Bt.print();
	}
}

class BinaryTreeArray{
	String []arr;
	int lastUsedIndex=0;
	BinaryTreeArray(int size){
		arr=new String[size+1];
		System.out.println("Blank tree of size = "+size+" is created");
	}
	
	boolean isFull() {
		if(arr.length-1 == lastUsedIndex) {
			return true;
		}
		return false;
	}
	
	  void insert(String val) {
		if(!isFull()) {
			arr[++lastUsedIndex]=val;
			System.out.println("value inserted = "+val); 
		}else {
			System.out.println("Array is full");
		}
	}
	 
	void print() {
		for(int i=1;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}
	
	
}
