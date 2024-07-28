package BinaryHeap;


/*
 * A binary heap is a binarytree with following properties
 * 1) A binaryheap is either minheap or maxheap. In a minHeap, the key at the root node
 * must be min among all keys present in binaryheap. the same property must be recursively
 * true for all nodes in binarytree
 * 
 * 2) Its a complete tree( All levels are completely filled except possibly the last level
 * has all keys as left as possible. The property of binaryheap makes them suitable to be stored in array
 * Due to point2) property we can leverage array for binary heap
 *
 *
 * In binaryheap we can always fetch root element only
 * 
 * Operation on Binary Heap
 * 1) create - Time - O(1) Space - O(N)
 * 2) Peek top - Time - O(1) Space - O(1)
 * 3) Extract Min/Extract Max Time - O(logN) Space - O(logN)
 * 4) Traversal - Time - O(N) Space - O(1)
 * 5) Size of binary Heap - Time - O(1) Space - O(1)
 * 6) Insert a value in binaryHeap - Time - O(logN) Space - O(logN)
 * 7) Delete a vlue in binary Heap - Time - O(1) Space - O(1)
 *
 *
 */

public class BinaryHeapImplementation {
	
	public static void main(String []args) {
		
		BinaryHeap bh = new BinaryHeap(10);
		bh.insert(5, "Min");
		bh.insert(10, "Min");
		bh.insert(20, "Min");
		bh.insert(30, "Min");
		bh.insert(40, "Min");
		bh.insert(50, "Min");
		bh.insert(60, "Min");
		bh.levelOrderTraversal();
		bh.peek();
		bh.insert(6, "Min");
		bh.levelOrderTraversal();
	
		
		BinaryHeap bh1 = new BinaryHeap(10);
		bh1.insert(5, "Min");
		bh1.insert(10, "Min");
		bh1.insert(20, "Min");
		bh1.insert(30, "Min");
		bh1.insert(40, "Min");
		bh1.insert(50, "Min");
		bh1.insert(60, "Min");
		bh1.insert(80, "Min");
		bh1.levelOrderTraversal();
		bh1.extractNode("Min");
		
	}

}

class BinaryHeap{
	int Bh[];
	int sizeOfBh;
	
	//Creation of BinaryHeap
	BinaryHeap(int size){
		Bh=new int[size+1];
		sizeOfBh=0;
	}
	
	public boolean isEmpty() {
		if(sizeOfBh>=0) {
			return false;
		}
		return true;
	}
	
	public Integer peek() { // Integer instead int allows returning null also
		System.out.println("\nShowing peek of Binary heap");
		if(isEmpty()) {
			return null;
		}
		return Bh[1];
	}
	
	public void levelOrderTraversal() {
		System.out.println("\nLevel Order Traversal");
		for(int i=1;i<=sizeOfBh;i++) {
			System.out.print(" "+Bh[i]);
		}
	}
	
	public void insert(int value, String heapType) {
		Bh[sizeOfBh+1]=value;
		sizeOfBh++;
		heapifyBottomToTop(sizeOfBh,heapType);
		System.out.println("Value insert "+value);
		
	}
	
	//we call heapifyBottomToTop for making heap property true based on its type
	// Time Complexity - O(logN) , Space Complexity = O(logN) {Due to stack use in recursion}
	private void heapifyBottomToTop(int index, String heapType) {
		int parentIdx = index/2;
		if(index <= 1) {
			return;
		}
		if(heapType.equals("Min")) {
			if(Bh[index]<Bh[parentIdx]) {
				int temp = Bh[index];
				Bh[index]=Bh[parentIdx];
				Bh[parentIdx]=temp;
			}
		}else if(heapType.equals("Max")) {
			if(Bh[index]>Bh[parentIdx]) {
				int temp = Bh[index];
				Bh[index]=Bh[parentIdx];
				Bh[parentIdx]=temp;
			}
		}
		heapifyBottomToTop(parentIdx,heapType);
	}
	
	
	//Like mentioned in peek in binary heap we can only fetch the root elements
	public int extractNode(String heapType) {
		if(isEmpty()) {
			return -1;
		}
		int extractedValue = Bh[1];
		Bh[1]=Bh[sizeOfBh];
		sizeOfBh--;
		heapifyTopToBottom(1,heapType);
		return extractedValue;
	}
	
	private void heapifyTopToBottom(int index, String heapType) {// O(logN)
		
		int left = index*2;
		int right = index*2 +1;
		int swapChild=0;
		
		if(sizeOfBh<left) {// if BinaryHeap size is less than the left mean no more element left
			return;
		}
		
		if(heapType == "Max") {
			if(sizeOfBh == left) {
				if(Bh[index]<Bh[left]) {
					int temp = Bh[left];
					Bh[left]=Bh[index];
					Bh[index]=temp;
					return;
				}
			}
			if(Bh[left]<Bh[right]) {
				swapChild = right;
			}else {
				swapChild = left;
			}
			int temp = Bh[swapChild];
			Bh[swapChild]=Bh[index];
			Bh[index]=temp;
		}else if(heapType == "Min") {
			if(sizeOfBh == left) {
				if(Bh[index]>Bh[left]) {
					int temp = Bh[sizeOfBh];
					Bh[sizeOfBh]=Bh[index];
					Bh[index]=temp;
					return;
				}
			}
			if(Bh[left]<Bh[right]) {
				swapChild = left;
			}else {
				swapChild = right;
			}
			int temp = Bh[swapChild];
			Bh[swapChild]=Bh[index];
			Bh[index]=temp;
		}
		heapifyTopToBottom(swapChild,heapType);
	}
	
	public void deleteBinaryHeap () {
		Bh=null;
		return;
	}
	
	
	
	
	
	
}
