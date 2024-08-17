package Sorting;

/*
 * 
 * In heapSort uses basic heap logic -> heapifyBottomToTop during insertion of each element
 * -> heapifyTopToBottom during extraction of each element
 * 
 * We insert  a single element then heapifyBottomToTop
 * Then after insertion of all element we extract 1 element at a time and heapifyTopToBottom
 * 
 */
//Time Complexity - O(nlogn)
//Space Complexity - O(1) [here it is O(n) as it we using newArr]
public class HeapSort {

	public static void main(String []args) {
		int a[] = {10,4,2,1,3,5,8,9,7,6};
		HeapSortImp hs = new HeapSortImp(a);
		hs.sort("min");
		hs.printArray();
	}
}

class HeapSortImp{
	int a[]=null;
	int newArr[];
	int sizeOfHeap;
	HeapSortImp(int[] a){
		this.a=a;
	}


	
	void sort(String heapType) {
		newArr=new int[a.length+1];
		sizeOfHeap=0;
		for(int i=0;i<a.length;i++) {
			newArr[sizeOfHeap+1]=a[i];
			sizeOfHeap++;
			int size=sizeOfHeap;
			heapifyBottomToTop(size,heapType);
		}
		
		for(int i=0;i<a.length;i++) {
			a[i]=extractFromHeap(heapType);
		}
	}
	
	
	void heapifyBottomToTop(int sizeOfHeap, String heapType) {
		int parent = sizeOfHeap/2;
		if(sizeOfHeap<=1) {//It has reached 1st node and for heap array we considering index from 1
			return;
		}
		
		if(heapType=="min") {
			if(newArr[parent]>newArr[sizeOfHeap]) {
				int temp = newArr[parent];
				newArr[parent]=newArr[sizeOfHeap];
				newArr[sizeOfHeap]=temp;
			}
		}
		if(heapType=="max") {
			if(newArr[parent]<newArr[sizeOfHeap]) {
				int temp = newArr[parent];
				newArr[parent]=newArr[sizeOfHeap];
				newArr[sizeOfHeap]=temp;
			}
		}
		heapifyBottomToTop(parent,heapType);
	}
	
	int extractFromHeap( String heapType) {
		int extractedNumber = newArr[1];
		//replace the top with lastElement
		newArr[1]=newArr[sizeOfHeap];
		sizeOfHeap--;
		heapifyTopToBottom(1,heapType);
		return extractedNumber;
	}
	
	void heapifyTopToBottom(int index, String heapType) {
		int left=2*index;
		int right=2*index+1;
		int swapChild=0;
		
		if(left>sizeOfHeap) {
			return;
		}
		if(heapType=="max") {
			// if only left child exist so swap if condition matches and return 
			if(sizeOfHeap==left) {
				if(newArr[index]<newArr[left]) {
					int temp=newArr[index];
					newArr[index]=newArr[left];
					newArr[left]=temp;
				}
				return;

			}
			if(newArr[left]>newArr[right]) {
				swapChild=left;
			}else {
				swapChild=right;
			}
			int temp=newArr[swapChild];
			newArr[swapChild]=newArr[index];
			newArr[index]=temp;
		}
		if(heapType=="min") {
			// if only left child exist so swap if condition matches and return 
			if(sizeOfHeap==left) {
				if(newArr[index]>newArr[left]) {
					int temp=newArr[index];
					newArr[index]=newArr[left];
					newArr[left]=temp;
					return;
				}
			}
			if(newArr[left]<newArr[right]) {
				swapChild=left;
			}else {
				swapChild=right;
			}
			int temp=newArr[swapChild];
			newArr[swapChild]=newArr[index];
			newArr[index]=temp;
		}
		heapifyTopToBottom(swapChild,heapType);
	}
	
	
	void printArray() {
		System.out.println(java.util.Arrays.toString(a));
	}
}
